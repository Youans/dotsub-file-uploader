import {Component, Inject, OnInit} from '@angular/core';
import {FileData} from "../file";
import {HttpClient, HttpEventType, HttpRequest, HttpResponse, HttpHeaders} from '@angular/common/http';
import {FileUploadClientService} from "../services/file-upload-client-service.service";

@Component({
  selector: 'app-file-upload-form',
  templateUrl: './file-upload-form.component.html',
  styleUrls: ['./file-upload-form.component.css']
})
export class FileUploadFormComponent implements OnInit {

  constructor(private fileUploadClientService: FileUploadClientService) {
    this.fileData = new FileData();
  }

  fileData: FileData;
  progressPercentage = 0;
  success = null;
  errorMessage: string;
  // metdata:{title:"",description:"",creationDate:Date};

  submitted = false;
  http: HttpClient;

  onSubmit() {
    this.resetFlags();
    this.fileUploadClientService.uploadFile(this.fileData).subscribe(event => {
        // Via this API, you get access to the raw event stream.
        // Look for upload progress events.
        if (event.type === HttpEventType.UploadProgress) {
          // This is an upload progress event. Compute and show the % done:
          this.progressPercentage = Math.round(100 * event.loaded / event.total);
          console.log(`File is ${this.progressPercentage}% uploaded.`);


        } else if (event instanceof HttpResponse) {
          console.log('File is completely uploaded!');
        }
      },
      error => {
        this.success = false;
        this.errorMessage = error.error.message || "Unexpected error happened make sure that you have submitted correct data.";
      },
      () => {
        this.submitted = false;
        this.success = true;
      });

  }

  onFileChange(event) {
    let reader = new FileReader();
    if (event.target.files && event.target.files.length > 0) {
      this.fileData.file = event.target.files[0];
      reader.readAsDataURL(this.fileData.file);
    }
  }

  resetFlags() {
    this.success = null;
    this.errorMessage = null;
    this.submitted = true;

  }

  ngOnInit() {
  }

}
