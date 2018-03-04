import { Component, OnInit } from "@angular/core";
import { routes } from "../../app.router";
import { Route } from "../../model/route";
import { getActiveRouteIndex } from "../../helper/navsHelper";

@Component({
  selector: "app-navbar",
  templateUrl: "./navbar.component.html",
  styleUrls: ["./navbar.component.less"]
})
export class NavbarComponent implements OnInit {
  private navs: Array<Route>;
  private userName: String;
  private activeIndex: Number;

  constructor() {
    this.navs = routes;
    this.userName = "Alex Loi";
    this.activeIndex = getActiveRouteIndex(routes);
  }

  ngOnInit() {}
}
