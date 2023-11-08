package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Application {

    public void run() throws IOException, InterruptedException {

        Manage manage = new Manage();
        String os = System.getProperty("os.name");

        String version = (manage.chromeVersion() != null) ? manage.chromeVersion() : (manage.chromiumVersion() != null ) ? manage.chromiumVersion() : null;

        if (version == null){
            System.out.println("I Couldn't find any chrome or chromium on your system " +
                    "Are u sure is installed it ?" +
                    "If yes Give it manually ");
            System.out.print("Enter your chrome version : ");
            Scanner scanner = new Scanner(System.in);
            version = scanner.nextLine();
        }

        if (os.toLowerCase().contains("linux")) {
            if (manage.existDriverLinux()) {
                System.out.println("Starting application ... ");
                manage.run();
            } else {
                System.out.println("No such a file or directory");

                System.out.println("Start Downloading chromeDriver for your version " +
                        "Please Do not stop application");

                manage.downloadDriverLinux(version);

                System.out.println("Download completed " +
                        "Making directory");
                manage.mkdir();
                System.out.println("Start unzipping file");
                manage.unzipLinux();
                System.out.println("Changing permission");
                manage.changePermission();
                System.out.println("Start Application for click");
                manage.run();

            }
        } else if (os.toLowerCase().contains("windows")) {
           if (manage.existDriverWindows()) {
                System.out.println("Starting application ... ");
                manage.run();
            } else {
                System.out.println("No such a file or directory");

                System.out.println("Start Downloading chromeDriver for your version " +
                        "Please Do not stop application");

                manage.downloadDriverWindows(version);

                System.out.println("Download completed " +
                        "Making directory");
                manage.mkdirWindows();
                System.out.println("Start unzipping file");
                manage.unzipWindows();
                System.out.println("Start Application for click");
                manage.run();
           }
        } else
            System.out.println("Unfortunately this program only support on linux and windows yet" +
                    "\nPlease try again later");
    }

}
