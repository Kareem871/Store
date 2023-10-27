package com.store.tunnel.add;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


/**
 Author: Kareem M
 Date: 01/May/2023
 Description: Manage uploading the product image to a specific directory

 Last Updated:
 01/May/2023 - Create the service
 08/Oct/2023 - Add Comments and refactor the code
 **/


public class FileUploadUtil {

    // Save the multipart file (Contains the file name and save the image to the $uploadDir directory)
    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        // Create the $uploadPath if it is not exist
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Try saving the image to the $uploadPath directory
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        // Generate IOException in case of failure of saving the image
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }
}
