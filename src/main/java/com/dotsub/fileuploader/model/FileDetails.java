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
import java.io.Serializable;
import java.util.*;

/**
 * @author Youans Ezzat
 */
@Entity public class FileDetails {

    @Id @GeneratedValue(strategy = GenerationType.AUTO) @Getter @Setter private long id;

    @Getter @Setter private String title;

    @Getter @Setter private String description;

    @Getter @Setter private Date creationDate;

    @OneToMany(mappedBy = "fileDetails",cascade = CascadeType.PERSIST) private Set<UploadedFile> uploadedFiles;

    public FileDetails(String title, String description, Date creationDate, UploadedFile... uploadedFiles) {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.uploadedFiles = new LinkedHashSet<>(Arrays.asList(uploadedFiles));
    }
    public FileDetails(){}
}
