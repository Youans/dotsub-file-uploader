/*
 * Copyright 2018 Mondia Media Group GmbH. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Mondia Media Group GmbH ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Mondia Media Group GmbH.
 */
package com.dotsub.fileuploader.dto.mappers;

import com.dotsub.fileuploader.dto.FileDetailsDto;
import com.dotsub.fileuploader.dto.UploadedFileDto;
import com.dotsub.fileuploader.model.FileDetails;
import com.dotsub.fileuploader.model.UploadedFile;
import org.mapstruct.Mapper;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author Youans Ezzat
 */
@Mapper(componentModel = "spring") public interface FileDetailsMapper {

    public List<FileDetailsDto> toFileDetailsDtos(List<FileDetails> fileDetails);
    public List<UploadedFileDto> toUploadedFilesDtos(LinkedHashSet<UploadedFile> uploadedFiles);


}
