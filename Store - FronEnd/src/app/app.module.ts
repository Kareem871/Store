import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavigationComponent } from './navigation/navigation.component';
import { MaterialModule } from './material/material.module';
import { SliderComponent } from './slider/slider.component';
import { NgImageSliderModule } from 'ng-image-slider';
import { FooterComponent } from './footer/footer.component';
import { CardsComponent } from './cards/cards.component';
import { AddComponent } from './add/add.component';
import { MatFileUploadModule } from 'angular-material-fileupload';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    SliderComponent,
    FooterComponent,
    CardsComponent,
    AddComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    NgImageSliderModule,
    MatFileUploadModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [SliderComponent],
})
export class AppModule { }
