import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { FileUploadFormComponent } from './file-upload-form/file-upload-form.component';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {FileUploadClientService} from "./services/file-upload-client-service.service";
@NgModule({
  declarations: [
    AppComponent,
    FileUploadFormComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [ FileUploadClientService],
  bootstrap: [AppComponent]
})
export class AppModule { }
