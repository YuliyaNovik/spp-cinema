import { RouterModule, Routes } from "@angular/router";
import { MoviesComponent } from "./component/movies/movies.component";

export const routes = [
  { paths: ["", "movies"], component: MoviesComponent, title: "Movies" }
];
export const components = [MoviesComponent];
export const appRoutes = () => {
  let routeList: Routes = [];

  for (let route of routes) {
    for (let path of route.paths) {
      routeList.push({ path: path, component: route.component });
    }
  }
  return routeList;
};
