import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Observable";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BASE_API_URL} from "../helper/urlHelper";
import {AuthService} from "./authService";
import {makeUserFilterUrl} from "../helper/filterHelper";
@Injectable()
export class UserService {
  constructor(private http: HttpClient) {
  }

  public getAllUser(): Observable<any> {
    return this.http.get(BASE_API_URL + "/api/user/all", {
      headers: new HttpHeaders({
        Token: AuthService.getToken()
      })
    })
  }

  public getUserByFilter(name, role, email): Observable<any>  {
    let filterPattern = makeUserFilterUrl(name,  role.filter(item => item !== ""), email.filter(item => item !== ""));
    return this.http.get(BASE_API_URL + "/api/user/filter?filter=" + filterPattern, {
      headers: new HttpHeaders({
        Token: AuthService.getToken()
      })
    });
  }

  public exportToDocument(type, name, role, email): Observable<Blob>  {
    let filterPattern = makeUserFilterUrl(name, role.filter(item => item !== ""), email.filter(item => item !== ""));
    return this.http.get(BASE_API_URL + `/api/export/${type}?table=user&filter=` + filterPattern, {
      responseType: "blob",
      headers: new HttpHeaders({
        Token: AuthService.getToken()
      })
    });
  }

  public deleteUser(id: number): Observable<any> {
    return this.http.delete(BASE_API_URL + `/api/user?id=${id}`, {
      headers: new HttpHeaders({
        Token: AuthService.getToken()
      })
    })
  }
}
