import {Component, EventEmitter, OnInit, Output} from "@angular/core";
import {AuthService} from "../../service/authService";
import {Role} from "../../enum/role";
import {MovieService} from "../../service/movieService";

@Component({
  selector: "app-movie-filter",
  templateUrl: "./movie-filter.component.html",
  styleUrls: ["./movie-filter.component.less"],
  providers: [MovieService]
})
export class MovieFilterComponent implements OnInit {
  @Output() filterMovies = new EventEmitter<any>();
  @Output() toDocument = new EventEmitter<any>();
  @Output() updateMovies = new EventEmitter<any>();
  private authService: any = AuthService;
  private minRole: Role = Role.ANON;
  private minCreateRole: Role = Role.MOVIE_ADMIN;

  constructor(private movieService: MovieService) {
  }

  ngOnInit() {
  }

  makeEvent(name, yearFrom, yearTo, durationFrom, durationTo) {
    let year = [];
    let duration = [];
    if (yearFrom.value !== "" && yearTo.value !== "") {
      for (let i = +yearFrom.value; i <= +yearTo.value; i++) {
        year.push(i);
      }
    } else if (yearFrom.value !== "") {
      year.push(+yearFrom.value);
    } else if (yearTo.value !== "") {
      year.push(+yearTo.value);
    }

    if (durationFrom.value !== "" && durationTo.value !== "") {
      for (let i = +durationFrom.value; i <= +durationTo.value; i++) {
        duration.push(i);
      }
    } else if (durationFrom.value !== "") {
      duration.push(+durationFrom.value);
    } else if (durationTo.value !== "") {
      duration.push(+durationTo.value);
    }
    return {
      name,
      year,
      duration
    }
  }

  updateFilterMovies(name, yearFrom, yearTo, durationFrom, durationTo) {
    let event = this.makeEvent(name, yearFrom, yearTo, durationFrom, durationTo);
    this.filterMovies.emit({
      name: name.value,
      year: event.year,
      duration: event.duration
    });
  }


  exportToDocument(type, name, yearFrom, yearTo, durationFrom, durationTo) {
    let event = this.makeEvent(name, yearFrom, yearTo, durationFrom, durationTo);
    this.toDocument.emit({
      type: type,
      name: name.value,
      year: event.year,
      duration: event.duration
    });
  }

  createMovie(movieName, director, duration, ageLimit, year, description) {
    let startDate = "1970-01-01";
    let endDate = "1970-01-01";

    if (
      movieName.value === "" ||
      director.value === "" ||
      duration.value === "" ||
      ageLimit.value === "" ||
      year.value === ""
    ) {
      return;
    }

    this.movieService.createMovie(
      movieName.value,
      director.value,
      duration.value,
      ageLimit.value,
      year.value,
      description.value,
      startDate, endDate
    ).subscribe((items) => {
      this.updateMovies.emit(items);
    })
  }
}
