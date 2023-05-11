/*******************************
 * Author: Ragunathan Ashwinth
 * IIT ID: 2019713
 * UoW ID: w1790169
 *******************************/

package coursework;

public class LaserPrinter implements ServicePrinter, Utility{

    private String name;
    private int id;
    private int paperLevel;
    private int tonerLevel;
    private int numDocumentPrinted;

    // To keep track of call to paperRefill method is successful or not
    private boolean paperRefilled = false;
    // To keep track of call to tonerRefill method is successful or not
    private boolean tonerReplaced = false;


    public LaserPrinter(String name, int printerId, int currentPaperLevel, int currentTonerLevel){
        super();
        this.name = name;
        this.id = printerId;
        this.paperLevel = currentPaperLevel;
        this.tonerLevel = currentTonerLevel;
        this.numDocumentPrinted = 0;
    }

    @Override
    public synchronized void replaceTonerCartridge() {

        boolean check = false;
        tonerReplaced = false;

        while (tonerLevel >= Minimum_Toner_Level){
            // Checking if the printer usage has been finished
            if(check){
                break;
            } else {
                System.out.println(YELLOW_BACKGROUND_BOLD + "[ Analyzing toner level ]" + RESET + " - " + YELLOW_BOLD_UNDERLINED + "Waiting for toner cartridge to reach minimum level. ( Current Toner Level: " + tonerLevel + " )" + RESET);
                // Toner technician is going to check the level of the toner cartridge every 5 seconds
                try {
                    wait(SLEEP_MAX_TIME);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            check = true;
        }

        // Replacing toner cartridge if the toner level decreases below the minimum level
        if (tonerLevel < Minimum_Toner_Level){
            System.out.println(YELLOW_BACKGROUND_BOLD + "[ Analyzing toner level ]" + RESET + " - " + RED_BOLD_UNDERLINED + "Toner level is critical and needs to be replaced!!! ( Current Toner Level: " + tonerLevel + " )" + RESET);
            tonerLevel = Full_Toner_Level;
            tonerReplaced = true;
            System.out.println(GREEN_BOLD + "Toner cartridge replaced! ( Current Toner Level: " + tonerLevel + " )" + RESET);
        }
        // The technician announces the printer is ready to be used
        notifyAll();
    }

    @Override
    public synchronized void refillPaper() {

        boolean check = false;
        paperRefilled = false;

        while(!(paperLevel + SheetsPerPack <= Full_Paper_Tray )){
            // Checking if the printer usage has been finished by all users
            if (check){
                break;
            } else {
                System.out.println(YELLOW_BACKGROUND_BOLD + "[ Analyzing paper level ]" + RESET + " - " + YELLOW_BOLD_UNDERLINED + "Waiting for paper to reach minimum level. ( Current Paper Level: " + paperLevel + " )" + RESET);
                // Paper technician is going to check the availability of paper every 5 seconds
                try {
                    wait(SLEEP_MAX_TIME);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            check = true;
        }

        // Refilling the printer with packs of paper if the paper decreases below the minimum level
        if (paperLevel + SheetsPerPack <= Full_Paper_Tray ){
            System.out.println(YELLOW_BACKGROUND_BOLD + "[ Analyzing paper level ]" + RESET + " - " + RED_BOLD_UNDERLINED + "Paper level is critical and needs to be refilled!!! ( Current Paper Level: " + paperLevel + " )" + RESET);
            paperLevel += SheetsPerPack;
            paperRefilled = true;
            System.out.println(GREEN_BOLD + "Paper has been refilled! ( Current Paper Level: " + paperLevel + " )" + RESET);
        }
        // The technician announces the printer is ready to be used
        notifyAll();
    }

    @Override
    public synchronized void printDocument(Document document) {

        while (paperLevel < document.getNumberOfPages() || tonerLevel < document.getNumberOfPages()){
            if (tonerLevel < document.getNumberOfPages()){
                // Toner level not being enough to print the document
                System.out.println(RED_ITALIC + "Toner level is not sufficient, waiting for a toner replacement" + RESET);
            } else if (paperLevel < document.getNumberOfPages()){
                // Number of papers not being enough to print the document
                System.out.println(RED_ITALIC + "Number of paper is not sufficient, waiting for paper refill"+ RESET);
            } else {
                // Both toner and paper not being enough to print the document
                System.out.println(RED_ITALIC + "Toner level and number of paper is not sufficient, waiting for the toner and paper to be restored"+ RESET);
            }
            // Student waits till the resources are restored
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // Document will be printed if there is enough resources
        if (paperLevel >= document.getNumberOfPages() && tonerLevel >= document.getNumberOfPages()){
            paperLevel -= document.getNumberOfPages();
            tonerLevel -= document.getNumberOfPages();
            numDocumentPrinted++;
            // Outputting the document ID, name and page count to the console
            System.out.println(CYAN_BOLD + "[ " + Thread.currentThread().getName() + " ]" + RESET + " " + WHITE_BOLD_ITALIC + "- Printed a document: ( Document ID: " + document.getUserID() + " | Document Name: " + document.getDocumentName() + " | Page Count: " + document.getNumberOfPages() + " )" + RESET);
            System.out.println(PURPLE_BOLD + "[ Existing Resources ]" +  RESET + " " + WHITE_BOLD_ITALIC + "- Current Paper Level: " + paperLevel + " | Current Toner Level: " + tonerLevel + RESET);
        }
        // The student announces the printer is ready to be used
        notifyAll();
    }

    @Override
    public String toString(){

        return "( Printer: " + id
                + " | Name: " + name
                + " | Paper Level: " + paperLevel
                + " | Toner Level: " + tonerLevel
                + " | Documents Printed: " + numDocumentPrinted
                +" )";
    }

    public boolean isPaperRefilled() {
        return paperRefilled;
    }

    public boolean isTonerReplaced() {
        return tonerReplaced;
    }
}
