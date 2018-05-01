import {Routes} from "@angular/router";
import {MoviesComponent} from "./component/movies/movies.component";
import {LoginComponent} from "./component/login/login.component";
import {RegistrationComponent} from "./component/registration/registration.component";
import {BuyTicketComponent} from './component/buy-ticket/buy-ticket.component';
import { UsersComponent } from './component/users/users.component';
import { SessionsComponent } from './component/sessions/sessions.component';
import { ShowingsComponent } from './component/showings/showings.component';
import { SalesComponent } from './component/sales/sales.component';

import {Route} from "./model/route";
import {Role} from "./enum/role";

export const routes: Array<Route> = [
  {paths: ["login"], component: LoginComponent, title: "Login", level: Role.ANON},
  {paths: ["registration"], component: RegistrationComponent, title: "Login", level: Role.ANON},
  {paths: ["buy-ticket"], component: BuyTicketComponent, title: "Ticket", level: Role.DEFAULT}
];

export const navs: Array<Route> = [
  {paths: ["users"], component: UsersComponent, title: "Users", level: Role.ADMIN},
  {paths: ["sessions"], component: SessionsComponent, title: "Sessions", level: Role.MOVIE_ADMIN},
  {paths: ["showings"], component: ShowingsComponent, title: "Showings", level: Role.MOVIE_ADMIN},
  {paths: ["sales"], component: SalesComponent, title: "Sales", level: Role.ACCOUNTANT},
  {paths: ["", "movies"], component: MoviesComponent, title: "Movies", level: Role.ANON}
];

export const appRoutes = () => {
  let routeList: Routes = [];
  let items = routes.concat(navs);

  for (let route of items) {
    for (let path of route.paths) {
      routeList.push({path: path, component: route.component});
    }
  }
  return routeList;
};
