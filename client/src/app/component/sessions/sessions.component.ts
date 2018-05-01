import {Component, OnInit} from '@angular/core';
import {MovieService} from "../../service/movieService";
import {TicketService} from "../../service/ticketService";
import {Session} from "../../model/session";
import {SessionService} from "../../service/sessionService";
import * as FileSaver from "file-saver";
import {Router} from "@angular/router";
import {AuthService} from "../../service/authService";
import {Role} from "../../enum/role";
import {CinemaRoomService} from "../../service/cinemaRoomService";

@Component({
  selector: 'app-sessions',
  templateUrl: './sessions.component.html',
  styleUrls: ['./sessions.component.less'],
  providers: [MovieService, TicketService, SessionService, CinemaRoomService]
})
export class SessionsComponent implements OnInit {
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

  makeEvent(name, yearFrom, yearTo, durationFrom, durationTo) {
    let year = [];
    let duration = [];
    if (yearFrom.value !== "" && yearTo.value !== "") {
      for (let i = +yearFrom.value; i <= +yearTo.value; i++) {
        year.push(i);
      }
    } else if (yearFrom.value !== "") {
      year.push(+yearFrom.value);
    } else if (yearTo.value !== "") {
      year.push(+yearTo.value);
    }

    if (durationFrom.value !== "" && durationTo.value !== "") {
      for (let i = +durationFrom.value; i <= +durationTo.value; i++) {
        duration.push(i);
      }
    } else if (durationFrom.value !== "") {
      duration.push(+durationFrom.value);
    } else if (durationTo.value !== "") {
      duration.push(+durationTo.value);
    }
    return {
      name,
      year,
      duration
    }
  }

  filterMovies(name, yearFrom, yearTo, durationFrom, durationTo) {
    let event = this.makeEvent(name, yearFrom, yearTo, durationFrom, durationTo);

    if (!event.name.value && !event.year && !event.duration) {
      this.movieService.getAllMovie().subscribe(this.bindSessions.bind(this))
    } else {
      this.movieService.getMovieByFilter(event.name.value, event.year, event.duration).subscribe(this.bindSessions.bind(this))
    }
  }

  exportToDocument(type, name, yearFrom, yearTo, durationFrom, durationTo) {
    let event = this.makeEvent(name, yearFrom, yearTo, durationFrom, durationTo);
    const exportSessions = (items) => {
      let sessionIds = [];
      let count = 0;
      for (let item of items) {
        this.ticketService.getSessionInfo(item.id, (data) => {
          count++;
          if (data.sessionFound) {
            for (let session of data.sessions) {
              sessionIds.push(session.id);
            }
          }
          if (count === items.length) {
            this.sessionService.exportToDocument(type, sessionIds).subscribe((response: Blob) => {
              FileSaver.saveAs(response, "export" + (type === "csv" ? ".csv" : ""));
            });
          }
        });
      }
    };

    if (!event.name.value && !event.year && !event.duration) {
      this.movieService.getAllMovie().subscribe(exportSessions.bind(this))
    } else {
      this.movieService.getMovieByFilter(event.name.value, event.year, event.duration).subscribe(exportSessions.bind(this))
    }
  }

  deleteSession(session: Session) {
    this.sessionService.deleteSession(session.id).subscribe((data) => {
      this.sessions = this.sessions.filter(item => item.id !== session.id);
    })
  }

  createSession(movie, date, time, cinemaRoom, coefficient:any = 1) {

    if (movie.value === "" || date.value === "" || time.value === "" || cinemaRoom.value === "") {
      return;
    }

    let movieId;
    let showingId;
    for (let item of this.movies) {
      if (item.name === movie.value) {
        movieId = item.id;
        showingId = this.showing.filter(session => session.movieId === item.id)[0].id;
        break;
      }
    }
    let cinemaRoomId = this.cinemaRooms.filter(item => item.name === cinemaRoom.value)[0].id;
    this.sessionService.createSession(showingId, cinemaRoomId, coefficient.value, date.value, time.value+":00").subscribe(data => {
      this.movieService.getAllMovie().subscribe(this.bindSessions.bind(this));
      this.cinemaRoomService.getAllCinemaRooms().subscribe((items) => {
        this.cinemaRooms = items;
      });
    });
  }

  ngOnInit() {
  }
}
