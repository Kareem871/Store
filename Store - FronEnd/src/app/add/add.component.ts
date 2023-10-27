import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
// import { UploadService } from './upload.service';
import { FormGroup, FormControl, Validators, FormBuilder } from "@angular/forms";
import { Router } from '@angular/router';

/**
 Author: Kareem M
 Date: 01/May/2023
 Description: 

 Last Updated:
 01/May/2023 - Create Component
 09/Oct/2023 - Add Comments and refactor the code

**/

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {
  form!: FormGroup

  ngOnInit(): void {

    // Create Group to construct the API request
    this.form = new FormGroup({
      title: new FormControl(),
      type: new FormControl(),
      filename: new FormControl(),
      price: new FormControl(),
      quantity: new FormControl(),
      traderName: new FormControl(),
      traderShare: new FormControl('', Validators.maxLength(this['price']))
    }) 

  }

  file!: File;
  enabled: boolean = true;
  // form: FormGroup = this.createFormGroup();
  
  // Define the variables which stores the error message of each product attribute
  fileExistsError: String = '';
  titleErr: String = '';
  priceError: String ='';
  typeError: String = '';
  tradersErr: String = '';
  traderNErr: String = '';
  quantityErr: String = '';

  // This will contain the error messages
  errFields!: Array<String>

  constructor(private httpClient: HttpClient, private router: Router, private fb: FormBuilder) { }

  onFilechange(event: any) {
  
    this.file = event.target.files[0]
    
    const extension = event.target.files[0].type
    const type = extension.substring(extension.lastIndexOf('/') + 1)

    if (type == "jpeg" && type == "png"){
      this.enabled = true
    } else {
      this.enabled = false
    }
  }

  addProduct(){

    if (this.file) {
      this.form.patchValue({'filename': this.file?.name})
      
      var fd = new FormData();
      fd.append('file', this.file)
      fd.append('form', new Blob([JSON.stringify(this.form.value)], {
        type: "application/json"
      }))

      // const headers= new HttpHeaders()
      // .set('Content-Type', 'application/json');
      
      console.log(JSON.stringify(this.form.value))

      this.httpClient.post('http://localhost:8080/add', 
                            fd)
                            // ,
                            // {'headers':headers})
      .subscribe(
        (data) => {
          console.log("Data  " + data)
          // this.router.navigate(['/home']);
        },
        (error) => {
          console.log(error)
          if (error.error.message == "يوجد صورة بنفس الاسم، برجاء اختيار صورة أخرى") this.fileExistsError = error.error.message;
          if (error.error.message != "يوجد صورة بنفس الاسم، برجاء اختيار صورة أخرى") this.fileExistsError = ''
          if (error.error.message == "حصة التاجر لا يمكن أن تكون أعلى من سعر القطعة") this.tradersErr = error.error.message;
          if (error.error.message != "حصة التاجر لا يمكن أن تكون أعلى من سعر القطعة") this.tradersErr = ''
          this.errFields = []
          for (let err of error.error) {
            if (err.field == "title") this.titleErr = err.defaultMessage
            if (err.field == "price") this.priceError = err.defaultMessage
            if (err.field == "type") this.typeError = err.defaultMessage
            if (err.field == "traderName") this.traderNErr = err.defaultMessage
            if (err.field == "traderShare") this.tradersErr = err.defaultMessage
            if (err.field == "quantity") this.quantityErr = err.defaultMessage

            this.errFields.push(err.field)
          }

          let missing = ['price','type','traderName','traderShare','quantity','title']
                            .filter(item => this.errFields.indexOf(item) < 0);

          for (let i of missing) {
            if (i == "price") this.priceError = ''
            if (i == "type") this.typeError = ''
            if (i == "traderName") this.traderNErr = ''
            if (i == "traderShare") this.tradersErr = ''
            if (i == "quantity") this.quantityErr = ''
            if (i == "title") this.titleErr = ''
          }

        }
      )
    }
  }
}