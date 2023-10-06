package org.example;

import java.io.File;
import java.util.Date;
import java.util.Timer;

@SuppressWarnings("deprecation")
public class Manage {
    Timer timer = new Timer();
    Task task = new Task();
    Date date = new Date();

    int hour = 8;
    int minute = 14;
    int second = 0;
    long period = 24 * 60 * 60 * 1000;

    public void run() {
        date.setHours(hour);
        date.setMinutes(minute);
        date.setSeconds(second);
        System.out.println("waiting to hour be : " + hour + " : " + minute + " : " + second);
        timer.scheduleAtFixedRate(task, date, period);

    }

    public String osName() {
        return System.getProperty("os.name");
    }

    public boolean exist() {
        String path = "/home/h0p3/Downloads/chromedriver-linux64/chromedriver";
        File file = new File(path);
        if (file.exists())
            return true;
        else
            return false;
    }


}
