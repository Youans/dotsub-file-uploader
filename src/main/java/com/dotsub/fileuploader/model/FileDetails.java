/*
 * Copyright 2018 Mondia Media Group GmbH. All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Mondia Media Group GmbH ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Mondia Media Group GmbH.
 */
package com.dotsub.fileuploader.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

/**
 * The File details entity holding the data provided by user of the
 * file uploaded form.
 *
 * @author Youans Ezzat
 */
@Entity public class FileDetails {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Getter @Setter private long id;

    @Getter @Setter @Size(max=300) private String title;

    @Getter @Setter @Size(max=1000) private String description;

    @Getter @Setter private Date creationDate;

    /**
     * OneToMany with UploadedFiles because one single file can be uploaded
     * to multiple destinations eg: Cloud Amazon S3
     */
    @OneToMany(mappedBy = "fileDetails",cascade = CascadeType.PERSIST) private Set<UploadedFile> uploadedFiles;

    /**
     * Instantiates a new File details.
     *
     * @param title         the title
     * @param description   the description
     * @param creationDate  the creation date
     * @param uploadedFiles the uploaded files
     */
    public FileDetails(String title, String description, Date creationDate, UploadedFile... uploadedFiles) {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.uploadedFiles = new LinkedHashSet<>(Arrays.asList(uploadedFiles));
    }

    /**
     * Instantiates a new File details.
     */
    public FileDetails(){}
}
