import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BASE_API_URL} from "../helper/urlHelper";
import {AuthService} from "./authService";

@Injectable()
export class ShowingService {
  constructor(private http: HttpClient) {
  }

  public getAllShowings() {
    return this.http.get(BASE_API_URL + "/api/showing/all", {
      headers: new HttpHeaders({
        Token: AuthService.getToken()
      })
    })
  }

  public getShowingByFilter(movieIds, startDate, endDate) {
    let filterPattern = "*";
    if (startDate.length > 0 && endDate.length > 0) {
      filterPattern = `start_showing_date>${startDate}*i+end_showing_date<${endDate}*i`;
    }

    if (movieIds.length > 0) {
      if (filterPattern ===  "*") {
        filterPattern = `movie_id=${movieIds.join(",")}*i`
      } else {
        filterPattern += `&movie_id=${movieIds.join(",")}*i`
      }
    }

    return this.http.get(BASE_API_URL + `/api/showing/filter?filter=${filterPattern}`, {
      headers: new HttpHeaders({
        Token: AuthService.getToken()
      })
    })
  }


  public exportToDocument(type, movieIds, startDate, endDate): Observable<Blob> {
    let filterPattern = "*";
    if (startDate.length > 0 && endDate.length > 0) {
      filterPattern = `start_showing_date>${startDate}*i+end_showing_date<${endDate}*i`;
    }

    if (movieIds.length > 0) {
      if (filterPattern ===  "*") {
        filterPattern = `movie_id=${movieIds.join(",")}*i`
      } else {
        filterPattern += `&movie_id=${movieIds.join(",")}*i`
      }
    }

    return this.http.get(BASE_API_URL + `/api/export/${type}?table=showing_movie&filter=${filterPattern}`, {
      responseType: "blob",
      headers: new HttpHeaders({
        Token: AuthService.getToken()
      })
    });
  }

  public deleteShowing(id: number): Observable<any> {
    return this.http.delete(BASE_API_URL + `/api/showing?id=${id}`, {
      headers: new HttpHeaders({
        Token: AuthService.getToken()
      })
    })
  }

  public createShowing(cinemaId, movieId, startShowingDate, endShowingDate, estimatedCost): Observable<any> {
    return this.http.post(
      BASE_API_URL + `/api/showing`,
      {cinemaId, movieId, startShowingDate, endShowingDate, estimatedCost},
      {
        headers: new HttpHeaders({
          Token: AuthService.getToken(),
          "Content-Type": "application/json"
        })
      }
    );
  }
}
