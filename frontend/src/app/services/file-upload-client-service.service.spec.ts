import { TestBed, inject } from '@angular/core/testing';

import { FileUploadClientService} from './file-upload-client-service.service';

describe('FileUploadClientServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FileUploadClientService]
    });
  });

  it('should be created', inject([FileUploadClientService], (service: FileUploadClientService) => {
    expect(service).toBeTruthy();
  }));
});
