import { Component, OnInit } from "@angular/core";
import { Movie } from "../../model/movie";
import { ImageService } from "../../service/imageService"
import { MovieService } from "../../service/movieService";
import * as FileSaver from "file-saver";
import {AuthService} from "../../service/authService";

@Component({
  selector: "app-movies",
  templateUrl: "./movies.component.html",
  styleUrls: ["./movies.component.less"],
  providers: [ImageService, MovieService]
})
export class MoviesComponent implements OnInit {
  private movies: Array<Movie>;

  constructor(private imageService: ImageService, private movieService: MovieService) {
    this.filterMovies({})
  }

  ngOnInit() { }

  updateMovies() {
    this.movieService.getAllMovie().subscribe(this.mapMovies.bind(this))
  }

  filterMovies(event: any) {
    if (!event.name && !event.year && !event.duration) {
      this.movieService.getAllMovie().subscribe(this.mapMovies.bind(this))
    } else {
      this.movieService.getMovieByFilter(event.name, event.year, event.duration).subscribe(this.mapMovies.bind(this))
    }
  }

  toDocument(event) {
    this.movieService.exportToDocument(
      event.type, event.name, event.year, event.duration
    ).subscribe((response: Blob) => {
      FileSaver.saveAs(response, "export" + (event.type === "csv" ? ".csv" : ""));
    });
  }

  mapMovies(movies: any) {
    this.movies = [];
    for (let movie of movies) {
      let newMovie = new Movie();
      newMovie.title = movie.name;
      newMovie.description = movie.description;
      newMovie.ageLimit = movie.ageLimit;
      newMovie.director = movie.director;
      newMovie.duration = movie.duration + " min.";
      newMovie.id = movie.id;
      newMovie.year = movie.year;
      this.movies.push(newMovie)
    }
    this.movies.reverse();

    for (let item of this.movies) {
      this.imageService.getImage(item.title, (url) => {
        item.image = url;
      })
    }
  }

  createMovie() {}
}
