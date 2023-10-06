package org.example;

public class Main {

    public static void main(String[] args) {

        Manage manage = new Manage();
        if (manage.osName().equals("Linux")) {
            if (manage.exist()) {
                System.out.println("Starting application ... ");
                manage.run();
            } else {
                System.out.println("No such a file or diredtory\n");
                System.out.println("You should download webdriver and put it on\n /home/h0p3/Downloads/chromedriver-linux64/chromedriver\n" +
                        "And then try again !! ");
                System.out.println("And if your username is not h0p3 create one or change source code ! ");
            }
        } else
            System.out.println("Unfortunately this program only support on linux yet" +
                    "\nPlease try again later");


    }
}