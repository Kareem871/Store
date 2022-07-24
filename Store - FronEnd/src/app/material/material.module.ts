import { NgModule } from '@angular/core';
import { MatCardModule } from '@angular/material/card'
import { MatToolbarModule } from '@angular/material/toolbar'
import { MatNativeDateModule } from '@angular/material/core';
import { MatMenuModule } from '@angular/material/menu'; 
import { MatInputModule } from '@angular/material/input';
import { MatCheckboxModule } from '@angular/material/checkbox'; 
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field'; 
import { MatSelectModule } from '@angular/material/select'; 
import { MatDialogModule } from '@angular/material/dialog';
import { MatProgressBarModule } from '@angular/material/progress-bar'; 
import  {MatSidenavModule } from '@angular/material/sidenav'; 

const materialComponenets = [
  MatToolbarModule, 
  MatCardModule,
  MatFormFieldModule,
  MatNativeDateModule,
  MatMenuModule,
  MatInputModule,
  MatCheckboxModule,
  MatButtonModule,
  MatIconModule,
  MatSelectModule,
  MatDialogModule,
  MatProgressBarModule,
  MatSidenavModule
]

@NgModule({
  imports: [
    materialComponenets
  ],
  exports: [
    materialComponenets
  ]
})
export class MaterialModule { }
