import { Component, OnInit } from '@angular/core';
import {CinemaRoomService} from "../../service/cinemaRoomService";
import {SessionService} from "../../service/sessionService";
import {MovieService} from "../../service/movieService";
import {TicketService} from "../../service/ticketService";
import {Router} from "@angular/router";
import {Session} from "../../model/session";
import {Role} from "../../enum/role";
import * as FileSaver from "file-saver";
import {AuthService} from "../../service/authService";

@Component({
  selector: 'app-sales',
  templateUrl: './sales.component.html',
  styleUrls: ['./sales.component.less'],
  providers: [MovieService, TicketService, SessionService, CinemaRoomService]
})
export class SalesComponent implements OnInit {
  private sessions: Array<any> = [];
  private movies: Array<any> = [];
  private showing: Array<any> = [];
  private cinemaRooms: Array<any> = [];

  constructor(
    private router: Router,
    private movieService: MovieService,
    private ticketService: TicketService,
    private sessionService: SessionService,
    private cinemaRoomService: CinemaRoomService
  ) {
    if (AuthService.getRole() >= Role.MOVIE_ADMIN) {
      this.movieService.getAllMovie().subscribe(this.bindSessions.bind(this));
      this.cinemaRoomService.getAllCinemaRooms().subscribe((items) => {
        this.cinemaRooms = items;
      });
    } else {
      this.router.navigate(["/"])
    }
  }

  bindSessions(items) {
    this.sessions = [];
    for (let item of items) {
      this.ticketService.getSessionInfo(item.id, (data) => {
        if (data.sessionFound) {
          this.showing.push(data.showing[0]);
          for (let session of data.sessions) {
            let sessionObject = new Session();
            sessionObject.date = session.date;
            sessionObject.time = session.time;
            sessionObject.filmName = item.name;
            sessionObject.id = session.id;
            sessionObject.numberOfSoldTickets = session.numberOfSoldTickets;
            this.sessions.push(sessionObject);
          }
          this.movies = items.filter(movie => {
            for (let showing of this.showing) {
              if (showing.movieId === movie.id) {
                return true;
              }
            }
            return false;
          });
        }
      });
    }
  }

  exportToDocument(type) {
    this.ticketService.reportToDocument(type).subscribe((response: Blob) => {
      FileSaver.saveAs(response, "report" + (type === "csv" ? ".csv" : ""));
    });
  }

  exportTicketToDocument(type, session) {
    this.ticketService.toDocumnet(type, session.id).subscribe((response: Blob) => {
      FileSaver.saveAs(response, "exportTickets" + (type === "csv" ? ".csv" : ""));
    });
  }

  ngOnInit() {
  }
}
