/*******************************
 * Author: Ragunathan Ashwinth
 * IIT ID: 2019713
 * UoW ID: w1790169
 *******************************/

package coursework;

import java.util.Random;

public class TonerTechnician implements Runnable, Utility{

    private String name;
    private ThreadGroup group;
    private ServicePrinter printer;

    public TonerTechnician(String name, ThreadGroup group, ServicePrinter printer){
        super();
        this.name = name;
        this.group = group;
        this.printer = printer;
    }

    @Override
    public void run() {
        int cartridgeCount = 0;

        /*
         The toner technician will check 3 times whether the toner cartridge needs to be replaced
         and will replace the toner cartridge if the number of toner in the printer goes
         below 10
        */
        for (int i = 0 ; i < 3; i++){
            printer.replaceTonerCartridge();

            if (((LaserPrinter)printer).isTonerReplaced()){
            // Keeping track of the number of times the toner cartridge was replaced
                cartridgeCount++;
            }

            // The maximum sleep time will be 5 seconds while the minimum sleep time will be 1 second
            int sleepTime = new Random().nextInt(SLEEP_MAX_TIME) + SLEEP_MIN_TIME;
            // The toner technician will be put to sleep if the number of toner cartridge remains greater than or equal to 10
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // Printing out the number of times the toner cartridge was replaced by the toner technician
        System.out.println(GREEN_BOLD_UNDERLINED + "Toner Technician Finished, number of toner cartridge(s) replaced: " + cartridgeCount + RESET);
    }
}
