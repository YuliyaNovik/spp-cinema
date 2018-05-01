import {Component, OnInit} from '@angular/core';
import {MovieService} from "../../service/movieService";
import {Router} from "@angular/router";
import {Session} from "../../model/session";
import {Role} from "../../enum/role";
import {AuthService} from "../../service/authService";
import {ShowingService} from "../../service/showingService";
import * as FileSaver from "file-saver";

@Component({
  selector: 'app-showings',
  templateUrl: './showings.component.html',
  styleUrls: ['./showings.component.less'],
  providers: [MovieService, ShowingService]
})
export class ShowingsComponent implements OnInit {
  private movies: Array<any> = [];
  private showings: Array<any> = [];

  constructor(
    private router: Router,
    private movieService: MovieService,
    private showingService: ShowingService
  ) {
    if (AuthService.getRole() >= Role.MOVIE_ADMIN) {
      this.movieService.getAllMovie().subscribe((items) => {
        this.movies = items;
        this.showingService.getAllShowings().subscribe(this.bindShowings.bind(this))
      });
    } else {
      this.router.navigate(["/"])
    }
  }

  bindShowings(showings) {
    this.showings = showings.map(item => {
      return {
        ...item,
        movieName: this.movies.filter(movie => movie.id === item.movieId)[0].name
      }
    });
  }

  filterShowings(movie, startDate, endDate) {
    let movieIds = this.movies.filter(item => item.name === movie.value).map(item => item.id);
    this.movies.filter(
      item => {
        return item.name === movie.value
      }
    );

    if (!movie.value && !startDate.value && !endDate.value) {
      this.showingService.getAllShowings().subscribe(this.bindShowings.bind(this))
    } else {
      this.showingService.getShowingByFilter(movieIds, startDate.value, endDate.value).subscribe((this.bindShowings.bind(this)));
    }
  }

  exportToDocument(type, movie, startDate, endDate) {
    let movieIds = this.movies.filter(item => item.name === movie.value).map(item => item.id);
    this.movies.filter(
      item => {
        return item.name === movie.value
      }
    );
    this.showingService.exportToDocument(type, movieIds, startDate.value, endDate.value).subscribe((response: Blob) => {
      FileSaver.saveAs(response, "export" + (type === "csv" ? ".csv" : ""));
    });
  }

  deleteShowing(showing) {
    this.showingService.deleteShowing(showing.id).subscribe(() => {
      this.showingService.getAllShowings().subscribe(this.bindShowings.bind(this))
    })
  }

  createShowing(movie, startDate, endDate, estimatedCost) {
    if (movie.value === "" || startDate.value === "" || endDate.value === "" || estimatedCost.value === "") {
      return;
    }


    let cinemaId = 0;
    let movieId = this.movies.filter(item => item.name === movie.value)[0].id;
    this.showingService.createShowing(cinemaId, movieId, startDate.value, endDate.value, estimatedCost.value).subscribe(item => {
      this.showingService.getAllShowings().subscribe(this.bindShowings.bind(this))
    })
  }

  ngOnInit() {
  }
}
