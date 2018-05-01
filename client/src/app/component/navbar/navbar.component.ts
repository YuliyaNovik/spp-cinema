import { Component, OnInit } from "@angular/core";
import { navs } from "../../app.router";
import { User } from "../../model/user";
import { Route } from "../../model/route";
import { Router } from "@angular/router";
import { AuthService } from "../../service/authService"

@Component({
  selector: "app-navbar",
  templateUrl: "./navbar.component.html",
  styleUrls: ["./navbar.component.less"]
})
export class NavbarComponent implements OnInit {
  private navs: Array<Route>;
  private user: User = null;
  private activeIndex: number;
  private authService: any = AuthService

  constructor(private router: Router) {
    this.navs = navs;
  }

  ngOnInit() { }
}
