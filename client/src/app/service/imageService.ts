
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

const NOT_FOUND_IMAGE = "http://craftandgraft.co/wp-content/uploads/2017/04/404PosterNotFound.jpg";

@Injectable()
export class ImageService {
    constructor(private http: HttpClient) { }

    public getImage(name: string, callback: Function) {
        let url = `https://api.themoviedb.org/3/search/movie?api_key=15d2ea6d0dc1d476efbca3eba2b9bbfb&query=${name}`
        this.http.get(url).subscribe((data: any) => {
            if (data.total_results === 0) {
                callback(NOT_FOUND_IMAGE)
                return;
            }
            callback("http://image.tmdb.org/t/p/w500/" + data.results[0].poster_path)
        });
    }
}
