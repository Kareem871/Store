import { Component, OnInit } from '@angular/core';
import { interval, Subscription } from 'rxjs';
import { DataService } from '../data.service';
import { Result } from './result';

@Component({
  selector: 'app-slider',
  templateUrl: './slider.component.html',
  styleUrls: ['./slider.component.css'],
})
export class SliderComponent implements OnInit {
  sliderArray: Array<any>;
  transform: number;
  selectedIndex = 0;
  subscription: Subscription | undefined;

  constructor(private data: DataService) {
    this.sliderArray = [];
    this.selectedIndex = 0;
    this.transform = 100;
  }

  ngOnInit() {
    this.data.getData().subscribe(
      (result: Result) => {
        console.log("Result: " + JSON.stringify(result))
        this.sliderArray = result.sliderArray;
    });

    const sup = interval(2500)
      .subscribe(() => {
        this.downSelected(1)
        if (this.selectedIndex == 4) this.selectedIndex = 0
      }); 
  }

  selected(x) {
    this.downSelected(x);
    this.selectedIndex = x;
   }

  keySelected(x) {
    this.downSelected(x);
    this.selectedIndex = x;
  }

  downSelected(i) {
    this.transform =  100 - (i) * 50;
    this.selectedIndex = this.selectedIndex + 1;
    if (this.selectedIndex > 4) {
      this.selectedIndex = 0;
    }
  }
}
