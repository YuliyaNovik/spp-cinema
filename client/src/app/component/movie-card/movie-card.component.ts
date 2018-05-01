import {Component, EventEmitter, OnInit, Output} from "@angular/core";
import { Input } from "@angular/core";
import { Movie } from "../../model/movie";
import { Router } from "@angular/router";
import { AuthService } from "../../service/authService";
import { Role } from "../../enum/role";
import { StoreService } from "../../service/storeService";
import {MovieService} from "../../service/movieService";

@Component({
  selector: "app-movie-card",
  templateUrl: "./movie-card.component.html",
  styleUrls: ["./movie-card.component.less"],
  providers: [MovieService]
})
export class MovieCardComponent implements OnInit {
  @Input("movie") movie: Movie;
  @Input("showBuyTicket") showBuyTicket: boolean;
  @Output() updateMovies = new EventEmitter<any>();
  private authService: any = AuthService;
  private minCreateRole: Role = Role.MOVIE_ADMIN;

  constructor(private router: Router, private movieService: MovieService) {}

  ngOnInit() { }

  private buyTicket(movie) {
    if (AuthService.getToken() === null || AuthService.getUser() === null || AuthService.getRole() === null || AuthService.getRole() === Role.ANON) {
      this.router.navigate(["/login"])
    } else {
      StoreService.push(movie);
      this.router.navigate(["/buy-ticket"])
    }
  }

  private deleteMovie(id) {
    this.movieService.deleteMovie(id).subscribe(item => {
      this.updateMovies.emit(item);
    });
  }
}
