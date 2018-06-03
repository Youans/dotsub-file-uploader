import {Injectable} from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest} from "@angular/common/http";
import {FileData} from "../file";
import {Observable} from "rxjs/index";
import {BASE_URL} from "../constants";

@Injectable()
export class FileUploadClientService {

  constructor(private http: HttpClient) {
  }

  uploadFile(fileData: FileData): Observable<HttpEvent<any>> {
    try {
      let formData = new FormData();
      formData.append("file", fileData.file, fileData.file.name);
      formData.append("title", fileData.title);
      formData.append("description", fileData.description);
      // Date been converted to ISO to take care of timezone
      formData.append("creationDate", new Date(fileData.creationDate).toISOString());
      const req = new HttpRequest('POST', (BASE_URL + '/'), formData, {
        reportProgress: true,
      });
      return this.http.request(req);
    } catch (error) {
      return Observable.create(ob=>{ob.error({error:{message:"Data either missing or invalid."}})});
    }

  }
}
