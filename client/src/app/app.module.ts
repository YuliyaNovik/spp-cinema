import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from "./app.component";

import { appRoutes } from "./app.router";
import { NavbarComponent } from "./component/navbar/navbar.component";
import { FooterComponent } from "./component/footer/footer.component";
import { MoviesComponent } from "./component/movies/movies.component";
import { MovieCardComponent } from "./component/movie-card/movie-card.component";
import { LoginComponent } from "./component/login/login.component";
import { RegistrationComponent } from "./component/registration/registration.component";
import { MovieFilterComponent } from './component/movie-filter/movie-filter.component';
import { BuyTicketComponent } from './component/buy-ticket/buy-ticket.component';
import { UsersComponent } from './component/users/users.component';
import { SessionsComponent } from './component/sessions/sessions.component';
import { ShowingsComponent } from './component/showings/showings.component';
import { SalesComponent } from './component/sales/sales.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    MoviesComponent,
    MovieCardComponent,
    LoginComponent,
    RegistrationComponent,
    MovieFilterComponent,
    BuyTicketComponent,
    UsersComponent,
    SessionsComponent,
    ShowingsComponent,
    SalesComponent
  ],
  imports: [BrowserModule, HttpClientModule, RouterModule.forRoot(appRoutes())],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
