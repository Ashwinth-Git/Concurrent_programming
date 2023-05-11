/******************************
* Author: Ragunathan Ashwinth
* IIT ID: 2019713
* UoW ID: w1790169
*******************************/

package coursework;

import java.util.Random;

public class Student implements Runnable, Utility{

    private String name;
    private ThreadGroup group;
    private Printer printer;

    public Student(String name, ThreadGroup group, Printer printer){
        super();
        this.name = name;
        this.group = group;
        this.printer = printer;
    }

    @Override
    public void run() {

        int noOfPages = 0;

        // Each student will access the printer 5 times to print the document
        for (int i = 0; i < 5; i++){
            // Creating the name of the document the students will print
            String documentName = "Document_0" + (i + 1);
            // Creating a unique ID for the document with the student number and document number
            String documentId = Thread.currentThread().getName() + "_DOC_" + (i + 1);

            // Creating a new document with the above-mentioned parameters
            Document document = new Document(documentId, documentName, generatePageCount());

            // Printing the documents
            printer.printDocument(document);

            // Keeping track of the total number of pages printed by a student
            noOfPages += document.getNumberOfPages();

            // The maximum sleep time will be 5 seconds while the minimum sleep time will be 1 second
            int sleepTime = new Random().nextInt(SLEEP_MAX_TIME) + SLEEP_MIN_TIME;
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // Prints the output of the number of documents and pages printed by a thread
        System.out.println(GREEN_BOLD_UNDERLINED + Thread.currentThread().getName() + " Finished Printing: 5 Documents, with a page count of " + noOfPages  + " Pages." + RESET);

    }

    // Generating a random number of pages for the printed documents
    private int generatePageCount() {
        return new Random().nextInt(20) + 1;
    }

}
