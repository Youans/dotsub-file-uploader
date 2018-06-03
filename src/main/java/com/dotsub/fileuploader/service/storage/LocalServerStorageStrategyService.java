package com.dotsub.fileuploader.service.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Implementation of Local server storage strategy
 *
 * @author Youans Ezzat
 */
@Service public class LocalServerStorageStrategyService implements StorageStrategyService {

    /**
     * The Logger.
     */
    static final Logger logger = LoggerFactory.getLogger(LocalServerStorageStrategyService.class);

    @Value("${upload.location}") private String uploadLocation;

    /**
     * Applying the real work of storing file on local server
     * uses System.nanoTime() to achieve uniqueness of file and avoid
     * overwriting
     *
     * @// TODO: 6/3/18 Enhance file naming code part
     * @param multipartFile
     * @return the uploaded file location
     * @throws IOException
     */
    public String store(MultipartFile multipartFile) throws IOException {
        final StringBuilder fileLocation = new StringBuilder(uploadLocation);
        fileLocation.append(System.nanoTime()).append("_").append(multipartFile.getOriginalFilename().replaceAll(" ", "_"));
        File file = new File(fileLocation.toString());
        file.createNewFile();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
        }
        return fileLocation.toString();
    }
}
