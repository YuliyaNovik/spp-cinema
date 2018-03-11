import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.less']
})
export class FooterComponent implements OnInit {
  private year: string;

  constructor() { 
    this.year = new Date().getFullYear().toString();
  }

  ngOnInit() {
  }

}
