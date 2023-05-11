/*******************************
 * Author: Ragunathan Ashwinth
 * IIT ID: 2019713
 * UoW ID: w1790169
 *******************************/

package coursework;

import java.util.Random;

public class PaperTechnician implements Runnable, Utility{

    private String name;
    private ThreadGroup group;
    private ServicePrinter printer;

    public PaperTechnician(String name, ThreadGroup group, ServicePrinter printer){
        super();
        this.name = name;
        this.group = group;
        this.printer = printer;
    }

    @Override
    public void run() {
        int packCount = 0;

        /*
         The paper technician will check 3 times whether the paper needs to be refilled
         and will refill the paper pack if the number of paper in the printer goes
         below 200 papers
        */
        for (int i = 0 ; i < 3; i++){
            printer.refillPaper();

            if (((LaserPrinter)printer).isPaperRefilled()){
            // Keeping track of the number of paper packs used
                packCount++;
            }

            // The maximum sleep time will be 5 seconds while the minimum sleep time will be 1 second
            int sleepTime = new Random().nextInt(SLEEP_MAX_TIME) + SLEEP_MIN_TIME;
            // The paper technician will be put to sleep if the number of paper remains greater than 200
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // Printing out the number of times the packs of paper was refilled by the paper technician
        System.out.println(GREEN_BOLD_UNDERLINED + "Paper Technician Finished, pack(s) of paper used: " + packCount + RESET);
    }
}
