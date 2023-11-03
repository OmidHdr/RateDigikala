package org.example;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import java.io.*;

public class UnzipUtility {


    public void unzip(String zipFilePath , String extractPath){
        try {
            File extractFolder = new File(extractPath);
            extractFolder.mkdirs();

            try (ZipArchiveInputStream zipInputStream = new ZipArchiveInputStream(new FileInputStream(zipFilePath))) {
                ArchiveEntry entry;

                while ((entry = zipInputStream.getNextZipEntry()) != null) {
                    File entryFile = new File(extractPath, entry.getName());

                    if (entry.isDirectory()) {
                        entryFile.mkdirs();
                    } else {
                        try (FileOutputStream outputStream = new FileOutputStream(entryFile)) {
                            byte[] buffer = new byte[1024];
                            int bytesRead;

                            while ((bytesRead = zipInputStream.read(buffer)) != -1) {
                                outputStream.write(buffer, 0, bytesRead);
                            }
                        }
                    }
                }

                System.out.println("Zip file has been successfully extracted.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
