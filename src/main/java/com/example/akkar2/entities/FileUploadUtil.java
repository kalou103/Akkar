package com.example.akkar2.entities;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadUtil {
    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try {
            String filePath = uploadPath + File.separator + StringUtils.cleanPath(fileName);
            Files.copy(multipartFile.getInputStream(), Paths.get(filePath));
        } catch (IOException e) {
            throw new IOException("Could not save file: " + fileName, e);
        }
    }
}
