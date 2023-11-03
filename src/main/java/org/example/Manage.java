package org.example;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manage {
    String name = findUsername();
    Timer timer = new Timer();
    Task task = new Task();

    public void run() {
        long period = 24 * 60 * 60 * 1000;
        timer.scheduleAtFixedRate(task, new Date(), period);
    }

    //section check Driver linux
    public boolean existDriverLinux() {
        String path = "/home/" + findUsername() + "/Downloads/chromedriver-linux64/chromedriver";
        File file = new File(path);
        return file.exists();
    }

    //section find Username
    public String findUsername() {
        return System.getProperty("user.name");
    }


    //section Download Linux
    public boolean downloadDriverLinux(String version) throws MalformedURLException {

        String[] parts = version.split("(\\d.*)\\.");
        String regex = "(\\d.*)\\.";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(version);

        String matchedText = null;
        if (matcher.find()) {
            matchedText = matcher.group(1);
        } else {
            System.out.println("تطابقی یافت نشد.");
            return false;
        }

        String linkChromeDriver = "https://edgedl.me.gvt1.com/edgedl/chrome/chrome-for-testing/" + matchedText + ".0" + "/linux64/chromedriver-linux64.zip";

        String savePath = "/home/" + findUsername() + "/Downloads/chromedriver-linux64.zip";
        URL url = new URL(linkChromeDriver);

        try (InputStream in = url.openStream(); FileOutputStream out = new FileOutputStream(savePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            System.out.println("فایل با موفقیت دانلود شد.");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //section unzip linux
    public void unzipLinux() throws IOException {

        String zipFilePath = "/home/" + findUsername() + "/Downloads/chromedriver-linux64.zip";
        String extractPath = "/home/" + findUsername() + "/Downloads";
        UnzipUtility utility = new UnzipUtility();
        utility.unzip(zipFilePath, extractPath);

    }

    //section make directory
    public void mkdir() {
        String directoryPath = "/home/" + findUsername() + "/Downloads/chromedriver-linux64";

        File directory = new File(directoryPath);

        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("پوشه با موفقیت ایجاد شد.");
            } else {
                System.out.println("خطا در ایجاد پوشه.");
            }
        } else {
            System.out.println("پوشه از قبل وجود دارد.");
        }
    }

    //section change permission
    public void changePermission() {

        String filePath = "/home/" + findUsername() + "/Downloads/chromedriver-linux64/chromedriver";

        try {
            Path file = FileSystems.getDefault().getPath(filePath);

            // ست کردن مجوزهای مورد نظر
            Set<PosixFilePermission> permissions = new HashSet<>();
            permissions.add(PosixFilePermission.OWNER_READ);
            permissions.add(PosixFilePermission.OWNER_WRITE);
            permissions.add(PosixFilePermission.OWNER_EXECUTE);
            permissions.add(PosixFilePermission.GROUP_READ);
            permissions.add(PosixFilePermission.GROUP_EXECUTE);
            permissions.add(PosixFilePermission.OTHERS_READ);
            permissions.add(PosixFilePermission.OTHERS_EXECUTE);
            // تغییر مجوزها
            Files.setPosixFilePermissions(file, permissions);

            System.out.println("مجوز اجرای فایل با موفقیت تغییر داده شد.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //section chromium version
    public String chromiumVersion() throws IOException, InterruptedException {
        // Define the command to run
        try {
            String command = "chromium --version";
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            processBuilder.redirectErrorStream(true);

            // Start the process
            Process process = processBuilder.start();

            // Read the output of the process
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                final String[] version = line.split(" ");
                return version[1];
            }
        } catch (IOException e) {
            return null;
        }
        return null;


    }

    //section chrome version
    public String chromeVersion() {
        try {
            // Define the command to run
            String command = "google-chrome --version";

            // Create a ProcessBuilder
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            processBuilder.redirectErrorStream(true);

            // Start the process
            Process process = processBuilder.start();

            // Read the output of the process
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                final String[] version = line.split(" ");
                return version[1];
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }

}

