package org.example;

import java.io.File;
import java.util.Date;
import java.util.Timer;

public class Manage {
    String name = findUsername();
    Timer timer = new Timer();
//    Task task = new Task();
    TaskLogin task = new TaskLogin();

    public void run() {
        long period = 24 * 60 * 60 * 1000;
        timer.scheduleAtFixedRate(task, new Date(), period);
    }

    public String osName() {
        return System.getProperty("os.name");
    }

    public boolean exist() {
        String path = "/home/"+name+"/Downloads/chromedriver-linux64/chromedriver";
        File file = new File(path);
        return file.exists();
    }

    public String findUsername(){
        return System.getProperty("user.name");
    }

}
