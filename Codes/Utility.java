/*******************************
 * Author: Ragunathan Ashwinth
 * IIT ID: 2019713
 * UoW ID: w1790169
 *******************************/

/*
  References on adding color to console outputs:
  https://www.geeksforgeeks.org/how-to-print-colored-text-in-java-console/
  https://stackoverflow.com/questions/4842424/list-of-ansi-color-escape-sequences
 */

package coursework;

public interface Utility {

    /*
      The codes created below will be used to add color to the text and background of the console output
      and to set the maximum and minimum sleep time for the threads
     */


    public static final String RESET = "\u001B[0m";     // Resetting the output colors to default

    // process messages
    public static final String PURPLE_BOLD = "\u001B[35;1m";            // Bolded purple color text font
    public static final String CYAN_BOLD = "\u001B[36;1m";              // Bolded cyan color text font
    public static final String WHITE_BOLD_ITALIC = "\u001B[39;1;3m";    // Italicised and bolded white color text font

    // warnings
    public static final String RED_ITALIC = "\u001B[31;3m";                     // Italicised red text
    public static final String YELLOW_BOLD_UNDERLINED = "\u001B[33;1;4m";       // Underlined and bolded yellow color text font
    public static final String RED_BOLD_UNDERLINED = "\u001B[31;1;4m";          // Underlined and bolded red color text font
    public static final String YELLOW_BACKGROUND_BOLD = "\u001B[43;52;1m";      // Bolded text with yellow background and encircled

    // success messages
    public static final String GREEN_BOLD = "\u001B[32;1m";                     // Bolded green color text font
    public static final String GREEN_BOLD_UNDERLINED = "\u001B[32;1;3m";        // Underlined and bolded green color text font
    public static final String WHITE_BOLD_GREEN_BG = "\u001B[39;1;42m";         // Bolded white color text font with green background


    // The maximum time a thread sleeps
    public final int SLEEP_MAX_TIME = 5000;

    // The minimum time a thread sleeps
    public final int SLEEP_MIN_TIME = 1000;

}
