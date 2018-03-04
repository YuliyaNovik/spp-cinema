import { RouterModule, Routes } from "@angular/router";
import { MoviesComponent } from "./component/movies/movies.component";

export const routes = [
  { path: "movies", component: MoviesComponent, title: "Movies" }
];
export const components = [MoviesComponent];

export const appRoutes: Routes = routes.map(item => {
  {
    return { path: item.path, component: item.component };
  }
});
