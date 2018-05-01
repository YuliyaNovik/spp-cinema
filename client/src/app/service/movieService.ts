import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {BASE_API_URL} from '../helper/urlHelper';
import {makeMovieFilterUrl} from '../helper/filterHelper';
import {AuthService} from "./authService";

@Injectable()
export class MovieService {
  constructor(private http: HttpClient) {
  }

  public getMovie(id: string): Observable<any> {
    return this.http.get(BASE_API_URL + `/api/movie?id=${id}`);
  }

  public getAllMovie(): Observable<any> {
    return this.http.get(BASE_API_URL + "/api/movie/all");
  }

  public getMovieByFilter(name?, year?, durations?): Observable<any> {
    let filterPattern = makeMovieFilterUrl(name, year, durations);
    return this.http.get(BASE_API_URL + "/api/movie/filter?filter=" + filterPattern);
  }

  public exportToDocument(type, name?, year?, durations?): Observable<Blob> {
    let filterPattern = makeMovieFilterUrl(name, year, durations);
    return this.http.get(BASE_API_URL + `/api/export/${type}?table=movie&filter=` + filterPattern, {
      responseType: "blob",
      headers: new HttpHeaders({
        Token: AuthService.getToken()
      })
    });
  }

  public deleteMovie(id: number): Observable<any> {
    return this.http.delete(BASE_API_URL + `/api/movie?id=${id}`, {
      headers: new HttpHeaders({
        Token: AuthService.getToken()
      })
    })
  }

  public createMovie(name, director, duration, ageLimit, year, description, startDate, endDate): Observable<any> {
    return this.http.post(
      BASE_API_URL + `/api/movie`,
      {name, director, duration, ageLimit, year, description, startDate, endDate},
      {
        headers: new HttpHeaders({
          Token: AuthService.getToken(),
          "Content-Type": "application/json"
        })
      }
    );
  }
}
