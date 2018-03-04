import { Component, OnInit } from "@angular/core";
import { Input } from "@angular/core";
import { Movie } from "../../model/movie";

@Component({
  selector: "app-movie-card",
  templateUrl: "./movie-card.component.html",
  styleUrls: ["./movie-card.component.less"]
})
export class MovieCardComponent implements OnInit {
  @Input("movie") movie: Movie;

  constructor() {}

  ngOnInit() {}
}
