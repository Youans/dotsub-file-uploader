/*
 * Copyright 2018 Mondia Media Group GmbH. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Mondia Media Group GmbH ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Mondia Media Group GmbH.
 */
package com.dotsub.fileuploader.integeration.rest;

import com.dotsub.fileuploader.repository.FileRepository;
import com.dotsub.fileuploader.rest.FileController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The type File controller test.
 *
 * @author Youans Ezzat
 */
@RunWith(SpringRunner.class) @SpringBootTest public class FileControllerTest {

    /**
     * The Title param.
     */
    final String TITLE_PARAM = "title", /**
     * The Description param.
     */
    DESCRIPTION_PARAM = "description", /**
     * The Creation date param.
     */
    CREATION_DATE_PARAM = "creationDate", /**
     * The File param.
     */
    FILE_PARAM = "file";

    @Autowired private FileController fileController;

    @Autowired private FileRepository fileRepository;

    private final String servicePath = "/api/file";

    private MockMvc fileControllerMVC;

    private MockMultipartFile validFile = new MockMultipartFile(FILE_PARAM, "filename.txt", "text/plain", "some xml".getBytes());

    private MockMultipartFile invalidFile = new MockMultipartFile("invalidFile", new byte[0]);

    /**
     * Sets up.
     */
    @Before public void setUp() {
        fileControllerMVC = MockMvcBuilders.standaloneSetup(fileController).build();

    }

    /**
     * Test with invalid multipart file.
     *
     * @throws Exception the exception
     */
    @Test public void testWithInvalidMultipartFile() throws Exception {
        MvcResult mvcResult = fileControllerMVC.perform(
                multipart(servicePath).file(invalidFile).param(TITLE_PARAM, "title").param(DESCRIPTION_PARAM, "description")
                        .param(CREATION_DATE_PARAM, "2018-03-13T08:55:00.000Z")).andExpect(status().isBadRequest()).andReturn();

    }

    /**
     * Test with valid multipart file.
     *
     * @throws Exception the exception
     */
    @Test public void testWithValidMultipartFile() throws Exception {
        MvcResult mvcResult = fileControllerMVC.perform(
                multipart(servicePath).file(validFile).param(TITLE_PARAM, "title").param(DESCRIPTION_PARAM, "description")
                        .param(CREATION_DATE_PARAM, "2018-03-13T08:55:00.000Z")).andExpect(status().isCreated()).andReturn();

        assertEquals(fileRepository.findAll().size(), 1);

    }

    /**
     * Test with missing data.
     *
     * @throws Exception the exception
     */
    @Test public void testWithMissingData() throws Exception {
        MvcResult mvcResult = fileControllerMVC
                .perform(multipart(servicePath).file(validFile).param(DESCRIPTION_PARAM, "description").param(CREATION_DATE_PARAM, "2018-03-13T08:55:00.000Z"))
                .andExpect(status().isBadRequest()).andReturn();
    }

    /**
     * Test with invalid date format.
     *
     * @throws Exception the exception
     */
    @Test public void testWithInvalidDateFormat() throws Exception {
        MvcResult mvcResult = fileControllerMVC.perform(
                multipart(servicePath).file(validFile).param(DESCRIPTION_PARAM, "description").param(DESCRIPTION_PARAM, "title")
                        .param(CREATION_DATE_PARAM, "2018-03-13 16:12")).andExpect(status().isBadRequest()).andReturn();
    }

}
