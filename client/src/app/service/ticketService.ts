import { Injectable } from "@angular/core";
import { Observable } from "rxjs/Observable";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { BASE_API_URL } from "../helper/urlHelper";
import { AuthService } from "./authService";
import {makeUserFilterUrl} from "../helper/filterHelper";

@Injectable()
export class TicketService {
  constructor(private http: HttpClient) {}

  public getSessionInfo(movieId: number, callback: Function) {
    this.http
      .get(BASE_API_URL + `/api/showing/filter?filter=movie_id=${movieId}*i`, {
        headers: new HttpHeaders({
          Token: AuthService.getToken()
        })
      })
      .subscribe((showing: any) => {
        if (showing.length) {
          this.http
            .get(
              BASE_API_URL +
                `/api/session/filter?filter=showing_id=${showing[0].id}*i`,
              {
                headers: new HttpHeaders({
                  Token: AuthService.getToken()
                })
              }
            )
            .subscribe(sessions => {
              callback({
                showing,
                sessions,
                sessionFound: true
              });
            });
        } else {
          callback({
            sessionFound: false
          });
        }
      });

    return null;
  }

  public getCinemaRoom(cinemaRoomId: number): Observable<any> {
    return this.http.get(BASE_API_URL + `/api/cinemaRoom?id=${cinemaRoomId}`, {
      headers: new HttpHeaders({
        Token: AuthService.getToken()
      })
    });
  }

  public getBuyedTickets(sessionId: number): Observable<any> {
    return this.http.get(
      BASE_API_URL + `/api/ticket/filter?filter=session_id=${sessionId}*i`,
      {
        headers: new HttpHeaders({
          Token: AuthService.getToken()
        })
      }
    );
  }

  public getSeats(seatIds: Array<number>): Observable<any> {
    return this.http.get(
      BASE_API_URL + `/api/seat/filter?filter=id=${seatIds.join(",")}*i`,
      {
        headers: new HttpHeaders({
          Token: AuthService.getToken()
        })
      }
    );
  }

  public getFreeSeats(cinemaRoomId: number): Observable<any> {
    return this.http.get(
      BASE_API_URL + `/api/seat/filter?filter=cinema_room_id=${cinemaRoomId}*i`,
      {
        headers: new HttpHeaders({
          Token: AuthService.getToken()
        })
      }
    );
  }

  public buyTicket(tickets: Array<any>, callback: Function) {
    this.http.post(
      BASE_API_URL + `/api/order`,
      tickets,
      {
        headers: new HttpHeaders({
          Token: AuthService.getToken(),
          "Content-Type": "application/json"
        })
      }
    ).subscribe((item) => {
      callback();
    });
  }

  public toDocumnet(type, sessionId): Observable<Blob> {
    let filterPattern = `session_id=${sessionId}*i`;
    return this.http.get(BASE_API_URL + `/api/export/${type}?table=ticket&filter=` + filterPattern, {
      responseType: "blob",
      headers: new HttpHeaders({
        Token: AuthService.getToken()
      })
    });
  }

  public reportToDocument(type): Observable<Blob> {
    return this.http.get(BASE_API_URL + `/api/export/${type}?table=report&filter=*`, {
      responseType: "blob",
      headers: new HttpHeaders({
        Token: AuthService.getToken()
      })
    });
  }
}
