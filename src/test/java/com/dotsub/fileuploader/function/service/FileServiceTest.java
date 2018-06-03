/*
 * Copyright 2018 Mondia Media Group GmbH. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Mondia Media Group GmbH ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Mondia Media Group GmbH.
 */
package com.dotsub.fileuploader.function.service;

import com.dotsub.fileuploader.model.FileDetails;
import com.dotsub.fileuploader.repository.FileRepository;
import com.dotsub.fileuploader.service.FileService;
import com.dotsub.fileuploader.service.storage.StorageStrategyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import static org.mockito.Mockito.*;

/**
 * The type File service test.
 *
 * @author Youans Ezzat
 */
@RunWith(SpringRunner.class) @SpringBootTest public class FileServiceTest {

    @Mock private StorageStrategyService storageService;

    @Mock private FileRepository fileRepository;

    @Autowired @InjectMocks private FileService fileService;

    private MultipartFile validFile = validFile = new MockMultipartFile("validFile", "filename.txt", "text/plain", "hello".getBytes(StandardCharsets.UTF_8));

    private MultipartFile invalidFile = new MockMultipartFile("invalidFile", new byte[0]);

    private final String title = "Title";

    private final String description = "Descritpion";

    private final Date creationDate = new Date();

    /**
     * Sets up.
     */
    @Before public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Test with invalid data.
     *
     * @throws IOException the io exception
     */
    @Test(expected = IllegalArgumentException.class) public void testWithInvalidData() throws IOException {
        fileService.uploadAndSave(validFile, null, description, creationDate);
    }

    /**
     * Test upload and save with valid multipart file.
     *
     * @throws IOException the io exception
     */
    @Test public void testUploadAndSaveWithValidMultipartFile() throws IOException {
        when(storageService.store(validFile)).thenReturn("/uploads/any");
        fileService.uploadAndSave(validFile, title, description, creationDate);
        verify(fileRepository).save(any(FileDetails.class));
    }

    /**
     * Test upload and save with in valid multipart file.
     *
     * @throws IOException the io exception
     */
    @Test(expected = IOException.class) public void testUploadAndSaveWithInValidMultipartFile() throws IOException {
        when(storageService.store(invalidFile)).thenThrow(IOException.class);
        fileService.uploadAndSave(invalidFile, title, description, creationDate);

    }
}
