package com.autoclaim.api.shared;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    public static void saveFile(String filePath, MultipartFile file) throws IOException {
        File myFile = new File(filePath);
        myFile.createNewFile();
        FileOutputStream fos =new FileOutputStream(myFile);
        fos.write(file.getBytes());
        fos.close();
    }

    public Resource getFileAsResource(String filePath) throws IOException {
        Path file = Paths.get(filePath);

        if (file != null) {
            return new UrlResource(file.toUri());
        }

        return null;
    }

    public static boolean deleteFile(String filePath) throws IOException {
        try {
            Path file = Paths.get(filePath);
            return Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}
