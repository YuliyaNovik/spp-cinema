import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {BASE_API_URL} from '../helper/urlHelper';
import * as sha256 from "sha256"
import {User} from '../model/user';
import {Role} from '../enum/role';

const TOKEN = "e078cb80a315c1545d5396567810bf94dc360f30bfdaae14ca6aad6cf9fe768d";
const USER_TOKEN = "04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb";
const ROLE_TOKEN = "688783f5f0940e2ac246e4cced5187a8c5d700abc05d9021d061238fee7a27f4"
const ROLE = {
  "ADMIN": "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918",
  "MOVIE_ADMIN": "120231b4fa00504bda5b5ce583012a397447793213962691f3b2e3f50897ef04",
  "ACCOUNTANT": "29d8c99ec25b271007f05eace87ec00959746687aa96ed783dd7a2f3bfdb398b",
  "DEFAULT": "37a8eec1ce19687d132fe29051dca629d164e2c4958ba141d5f4133a33f0688f",
  "ANON": "5430eeed859cad61d925097ec4f532461ccf1ab6b9802b09a313be1478a4d614"
};

@Injectable()
export class AuthService {
  constructor(private http: HttpClient) {
  }

  public static getToken() {
    return sessionStorage.getItem(TOKEN);
  }

  public static getUser() {
    return sessionStorage.getItem(USER_TOKEN);
  }

  public static getRole() {
    switch (sessionStorage.getItem(ROLE_TOKEN)) {
      case ROLE["ADMIN"]:
        return Role.ADMIN;
      case ROLE["MOVIE_ADMIN"]:
        return Role.MOVIE_ADMIN;
      case ROLE["ACCOUNTANT"]:
        return Role.ACCOUNTANT;
      case ROLE["DEFAULT"]:
        return Role.DEFAULT;
      case ROLE["ANON"]:
      default:
        return Role.ANON
    }
  }

  public login(userName: string, password: string, callback: Function) {
    this.http.get(BASE_API_URL + `/api/login?userName=${userName}&password=${sha256(password)}`).subscribe((data: any) => {
      sessionStorage.setItem(TOKEN, data.token);
      sessionStorage.setItem(USER_TOKEN, JSON.stringify({
        id: data.user.id,
        email: data.user.email,
        userName: data.user.userName
      }));
      sessionStorage.setItem(ROLE_TOKEN, ROLE[data.user.role]);
      callback();
    });
  }

  public registration(user: User): Observable<any> {
    return this.http.post(
      BASE_API_URL + `/api/registration`, {
        userName: user.userName,
        password: sha256(user.password),
        email: user.email
      },
      {
        headers: new HttpHeaders({
          'Content-Type': 'application/json'
        })
      }
    );
  }
}
