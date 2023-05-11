/*******************************
 * Author: Ragunathan Ashwinth
 * IIT ID: 2019713
 * UoW ID: w1790169
 *******************************/

package coursework;

public class PrintingSystem implements Utility{

    public static void main(String[] args) {

        // Creating the thread groups for the students and technicians
        ThreadGroup studentGroup = new ThreadGroup("Student Group");
        ThreadGroup technicianGroup = new ThreadGroup("Technician Group");

        // Creating the shared resource which will be used by the students and maintained by the technicians
        ServicePrinter printer = new LaserPrinter("Ink Jet", 101, 100, 50);

        // Creating the runnable objects of the four students
        Runnable r1 = new Student("Student 1", studentGroup, printer);
        Runnable r2 = new Student("Student 2", studentGroup, printer);
        Runnable r3 = new Student("Student 3", studentGroup, printer);
        Runnable r4 = new Student("Student 4", studentGroup, printer);

        // Creating the runnable objects of the two technicians
        Runnable r5 = new PaperTechnician("Paper Technician", technicianGroup, printer);
        Runnable r6 = new TonerTechnician("Toner Technician", technicianGroup, printer);

        // Initializing the student threads
        Thread student1 = new Thread(studentGroup, r1, "Student 1");
        Thread student2 = new Thread(studentGroup, r2, "Student 2");
        Thread student3 = new Thread(studentGroup, r3, "Student 3");
        Thread student4 = new Thread(studentGroup, r4, "Student 4");

        // Initializing the technician threads
        Thread technicianP = new Thread(technicianGroup, r5, "Paper Technician");
        Thread technicianT = new Thread(technicianGroup, r6, "Toner Technician");

        // Starting all the threads
        student1.start();
        student2.start();
        student3.start();
        student4.start();
        technicianP.start();
        technicianT.start();

        // Waiting for threads to complete execution and printing the final report
        try {
            student1.join();
            student2.join();
            student3.join();
            student4.join();
            technicianP.join();
            technicianT.join();

            System.out.print("\n\n" + WHITE_BOLD_ITALIC + "All tasks completed and successfully executed!" + RESET);
            System.out.print("\n" + WHITE_BOLD_ITALIC + "Printing final report....." + RESET);
            System.out.print("\n\n"+ WHITE_BOLD_GREEN_BG +"[ Printer Report ] - Current state of printer: " + printer.toString() +  RESET + "\n\n");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
