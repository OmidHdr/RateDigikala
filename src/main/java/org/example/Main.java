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
                String name = manage.findUsername();
                System.out.println("You should download webdriver and put it on\n /home/"+name+"/Downloads/chromedriver-linux64/chromedriver\n" +
                        "And then try again !! ");
            }
        } else
            System.out.println("Unfortunately this program only support on linux yet" +
                    "\nPlease try again later");
    }
}