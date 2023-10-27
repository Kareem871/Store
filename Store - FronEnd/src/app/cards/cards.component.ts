import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDrawer } from '@angular/material/sidenav/drawer';

/**
 Author: Kareem M
 Date: 01/May/2023
 Description: View Product Details in Card

 Last Updated:
 01/May/2023 - Create Component
 09/Oct/2023 - Add Comments and refactor the code

**/

@Component({
  selector: 'app-cards',
  templateUrl: './cards.component.html',
  styleUrls: ['./cards.component.css']
})
export class CardsComponent implements OnInit {

  constructor(private http: HttpClient) { }

  // Get the list of products from BE and store them in $products
  products!: any
  ngOnInit(): void {
    this.http.get("http://localhost:8080/view").subscribe(data => {
      console.log(data)
      this.products = data
    })
  }
  @ViewChild('drawer') public drawer!: MatDrawer;

  
  toggled: Boolean = true

  index!:number
  showDetails($event,i:number){
    console.log(i)
    this.index = i
    $event.stopPropagation();
    console.log(this.toggled)
    this.toggled = !this.toggled
  }

  closeDrawer(){
    this.toggled = !this.toggled
    if(this.toggled) this.drawer.close()
  }
}
