import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { AppComponent } from "./app.component";

import { components, appRoutes } from "./app.router";
import { NavbarComponent } from "./component/navbar/navbar.component";
import { FooterComponent } from "./component/footer/footer.component";
import { MoviesComponent } from "./component/movies/movies.component";
import { MovieCardComponent } from './component/movie-card/movie-card.component';
@NgModule({
  declarations: [AppComponent, NavbarComponent, FooterComponent, MoviesComponent, MovieCardComponent],
  imports: [BrowserModule, RouterModule.forRoot(appRoutes())],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
