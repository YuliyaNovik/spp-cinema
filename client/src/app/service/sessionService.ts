import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BASE_API_URL} from "../helper/urlHelper";
import {AuthService} from "./authService";

@Injectable()
export class SessionService {
  constructor(private http: HttpClient) {
  }

  public exportToDocument(type, sessionIds): Observable<Blob> {
    return this.http.get(BASE_API_URL + `/api/export/${type}?table=session&filter=id=${sessionIds.join(",")}*i`, {
      responseType: "blob",
      headers: new HttpHeaders({
        Token: AuthService.getToken()
      })
    });
  }

  public deleteSession(id: number): Observable<any> {
    return this.http.delete(BASE_API_URL + `/api/session?id=${id}`, {
      headers: new HttpHeaders({
        Token: AuthService.getToken()
      })
    })
  }

  public createSession(showingId, cinemaRoomId, coefficient, date, time): Observable<any> {
    return this.http.post(
      BASE_API_URL + `/api/session`,
      {showingId, cinemaRoomId, coefficient, date, time},
      {
        headers: new HttpHeaders({
          Token: AuthService.getToken(),
          "Content-Type": "application/json"
        })
      }
    );
  }
}
