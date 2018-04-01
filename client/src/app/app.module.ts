import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { AppComponent } from "./app.component";

import { appRoutes } from "./app.router";
import { NavbarComponent } from "./component/navbar/navbar.component";
import { FooterComponent } from "./component/footer/footer.component";
import { MoviesComponent } from "./component/movies/movies.component";
import { MovieCardComponent } from "./component/movie-card/movie-card.component";
import { LoginComponent } from "./component/login/login.component";
import { RegistrationComponent } from "./component/registration/registration.component";

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    MoviesComponent,
    MovieCardComponent,
    LoginComponent,
    RegistrationComponent
  ],
  imports: [BrowserModule, RouterModule.forRoot(appRoutes())],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
