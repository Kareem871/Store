import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddComponent } from './add/add.component';
import { CardsComponent } from './cards/cards.component';
import { SliderComponent } from './slider/slider.component';

const routes: Routes = [
  {path: 'add', component: AddComponent },
  {path: 'view', component: CardsComponent},
  {path: 'home', component: SliderComponent},
  {path: '', redirectTo: '/view', pathMatch: "full"}
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
