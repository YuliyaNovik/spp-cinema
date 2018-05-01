import { Component, OnInit } from "@angular/core";
import { StoreService } from "../../service/storeService";
import { Movie } from "../../model/movie";
import { Router } from "@angular/router";
import { TicketService } from "../../service/ticketService";

@Component({
  selector: "app-buy-ticket",
  templateUrl: "./buy-ticket.component.html",
  styleUrls: ["./buy-ticket.component.less"],
  providers: [TicketService]
})
export class BuyTicketComponent implements OnInit {
  private movie: Movie;
  private displaySeats: boolean = false;
  private sessionFound: boolean = true;
  private sessions: Array<any> = [];
  private sessionId: number = -1;
  private cinemaRoomId: number = -1;
  private selectedSessionIndex: number = -1;
  private numberOfSeatsInRow: number = -1;
  private numberOfRows: number = -1;
  private selectedSeats: Array<any> = [];
  private buyedSeats: Array<any> = [];
  private seats: Array<Array<any>> = [];
  private cost: string = "";
  private estimatedCost: number = 1;
  private sessionCoefficient: number = 1;

  constructor(private router: Router, private ticketService: TicketService) {
    this.movie = StoreService.pop();
    if (this.movie === null) {
      this.router.navigate(["/"]);
      return;
    }
    this.ticketService.getSessionInfo(this.movie.id, data => {
      this.sessionFound = data.sessionFound;
      if (this.sessionFound) {
        for (let session of data.sessions) {
          this.sessions.push({
            date: session.date,
            time: session.time,
            cinemaRoomId: session.cinemaRoomId,
            sessionId: session.id,
            sessionCoefficient: session.coefficient
          });
        }
        this.estimatedCost = data.showing[0].estimatedCost;
      }
    });
  }

  buyTicket() {
    this.ticketService.getFreeSeats(this.cinemaRoomId).subscribe(seats => {
      let tickets = seats
        .filter(seat => {
          for (let item of this.selectedSeats) {
            if (item.row === seat.row && item.number === seat.number) {
              return true;
            }
          }
          return false;
        })
        .map(item => {
          return { seatId: item.id, sessionId: this.sessionId };
        });
      this.ticketService.buyTicket(tickets, () => {
        this.router.navigate(["/"]);
      });
    });
  }

  findSeatsOfCinemaRoom(
    index: number,
    cinemaRoomId: number,
    sessionId: number,
    sessionCoefficient: number
  ) {
    this.sessionId = sessionId;
    this.cinemaRoomId = cinemaRoomId;
    this.sessionCoefficient = sessionCoefficient;
    this.selectedSessionIndex = index;
    this.ticketService.getCinemaRoom(cinemaRoomId).subscribe(cinemaRoom => {
      this.ticketService.getBuyedTickets(sessionId).subscribe(buyedTickets => {
        if (buyedTickets.length === 0) {
          this.buyedSeats = [];
          this.numberOfRows = cinemaRoom.rows;
          this.numberOfSeatsInRow = cinemaRoom.numbers;
          this.initSeats();
          this.displaySeats = true;
        } else {
          this.ticketService
            .getSeats(buyedTickets.map(item => item.seatId))
            .subscribe(buyedSeats => {
              this.buyedSeats = buyedSeats;
              this.numberOfRows = cinemaRoom.rows;
              this.numberOfSeatsInRow = cinemaRoom.numbers;
              this.initSeats();
              this.displaySeats = true;
            });
        }
      });
    });
  }

  initSeats() {
    this.seats = [];
    for (let i = 0; i < this.numberOfRows; i++) {
      this.seats.push([]);
      for (let j = 0; j < this.numberOfSeatsInRow; j++) {
        let find = false;
        for (let item of this.buyedSeats) {
          if (item.row === i && item.number === j) {
            this.seats[i].push(-1);
            find = true;
            break;
          }
        }
        if (!find) {
          this.seats[i].push(0);
        }
      }
    }
  }

  selectSeat(row, number) {
    for (let buyedSeat of this.buyedSeats) {
      if (buyedSeat.row === row && buyedSeat.number === number) {
        return;
      }
    }

    this.selectedSeats.push({ row, number });
    if (this.seats[row][number] === 1) {
      this.seats[row][number] = 0;
    } else {
      this.seats[row][number] = 1;
    }
    this.cost =
      (
        this.sessionCoefficient *
        this.estimatedCost *
        this.selectedSeats.length
      ).toFixed(2) + "$";
  }

  ngOnInit() {}
}
