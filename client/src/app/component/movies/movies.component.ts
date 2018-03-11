import { Component, OnInit } from "@angular/core";
import { Movie } from "../../model/movie";

@Component({
  selector: "app-movies",
  templateUrl: "./movies.component.html",
  styleUrls: ["./movies.component.less"]
})
export class MoviesComponent implements OnInit {
  private movies: Array<Movie>;
  
  constructor() {
    this.movies = [];
    this.movies.push({
      id: 0,
      title: "Черная Пантера",
      description:
        "С первого взгляда можно решить, что Ваканда — обычная территория дикой Африки, но это не так. Здесь, в недрах пустынных земель, скрываются залежи уникального металла, способного поглощать вибрацию. Многие пытались добраться до него, разоряя всё на своём пути и принося смерть аборигенам, но каждый раз таинственный дух саванны — Чёрная Пантера — вставал на защиту угнетённых. Спустя много лет беда снова приходит в Ваканду, и в этот раз враг заручился поддержкой современных технологий. Когда шансов почти не остаётся, Т`Чалла, молодой принц Ваканды, узнаёт, что именно ему предстоит возродить легенду и продолжить вечную борьбу, надев маску Чёрной Пантеры.",
      year: "2018",
      genre: "фантастика, боевик, приключения",
      country: "США",
      duration: "134 мин. / 02:14",
      image: "https://st.kp.yandex.net/images/film_iphone/iphone360_623250.jpg"
    });
    this.movies.push({
      id: 0,
      title: "Тор 3: Рагнарек",
      description:
        "Вернувшись в Асгард в поисках таинственного врага, ведущего охоту на Камни Бесконечности, Тор обнаруживает, что действия его брата Локи, захватившего трон Асгарда, привели к приближению наиболее страшного события — Рагнарёка. По легенде это ознаменует последнюю битву Асгарда, последствием которой станет его полное уничтожение. В попытке предотвратить это событие Тору предстоит прибегнуть к помощи своего товарища из Мстителей — Халка. Вместе им предстоит столкнуться лицом к лицу со злейшим врагом всех девяти миров — огненным демоном Суртуром, целью которого является исполнение пророчества о Рагнарёке и уничтожение девяти миров.",
      year: "2017",
      genre: "фантастика, фэнтези, боевик, комедия, приключения",
      country: "США",
      duration: "130 мин. / 02:10",
      image: "https://st.kp.yandex.net/images/film_iphone/iphone360_822709.jpg"
    });
  }

  ngOnInit() {}
}