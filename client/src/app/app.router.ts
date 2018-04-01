import { RouterModule, Routes } from "@angular/router";
import { MoviesComponent } from "./component/movies/movies.component";
import { LoginComponent } from "./component/login/login.component";
import { RegistrationComponent } from "./component/registration/registration.component";
import { Route } from "./model/route";

export const routes: Array<Route> = [
  { paths: ["login"], component: LoginComponent, title: "Login" },
  { paths: ["registration"], component: RegistrationComponent, title: "Login" },
];

export const navs: Array<Route> = [
  { paths: ["", "movies"], component: MoviesComponent, title: "Movies" }
]

export const appRoutes = () => {
  let routeList: Routes = [];
  let items = routes.concat(navs);

  for (let route of items) {
    for (let path of route.paths) {
      routeList.push({ path: path, component: route.component });
    }
  }
  return routeList;
};
