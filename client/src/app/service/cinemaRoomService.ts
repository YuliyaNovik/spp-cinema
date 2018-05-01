import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BASE_API_URL} from "../helper/urlHelper";
import {AuthService} from "./authService";

@Injectable()
export class CinemaRoomService {
  constructor(private http: HttpClient) {
  }

  public getAllCinemaRooms(): Observable<any> {
    return this.http.get(BASE_API_URL + "/api/cinemaRoom/all", {
      headers: new HttpHeaders({
        Token: AuthService.getToken()
      })
    });
  }
}
