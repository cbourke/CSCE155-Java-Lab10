package unl.cse.file_io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MajorLeague {

    /* Constants */
    static final int NUM_TEAMS = 16;

    public static void main( String[] args ){

        /* Name of the input file */
        String fileName = "data/mlb_nl_2011.txt";

        /* Arrays to hold team names and win percentages */
        String[] teams = new String[NUM_TEAMS];
        double[] winPercentages = new double[NUM_TEAMS];

        /* Get the data */
        getData( fileName, teams, winPercentages );

        /* Sort the teams */
        sortMLB( teams, winPercentages );

        /* Print the results */
        printMLB( teams, winPercentages );

        System.out.println();

    } 

    /**
     * Reads in mlb data from an external text file, stores the team names in
     * a String array, computes the win percentage per team, and stores it in
     * a double winPercentage array.
     * @param fileName The name of the file to open for input.
     * @param teamName Array of mlb national team names.
     * @param winPercent Array of win percentages per team.
     */
    public static void getData( String fileName, String[] teamName, double[] winPercent ){

        String name;
        double wins, losses, percent;

        // Prepare the file, read the input, and tokenize it to get the 
        // team name, wins, and losses.  Store the results into the arrays
        // teamName and winPercent
        
        

    } 

    /**
     * Selection sorts a grouping of mlb teams based on win percentage.
     * @param teams String array of national league team names.
     * @param winPerc Double array of win percentages.
     */
    private static void sortMLB( String[] teams, double[] winPerc ){

        int max_index;

        String temp;
        double tempDouble;

        for( int i = 0; i < NUM_TEAMS - 1; i++ ){
            max_index = i;
            /* find the maximum element among elements i+1 thru n-2 */
            for( int j = i+1; j < NUM_TEAMS; j++ ){

                if( winPerc[max_index] < winPerc[j] )
                    max_index = j;

            } // End inner j loop

            /* swap the ith element and the maximum element */
            /* in this case, elements from both arrays need to be swapped */
            /* at the same time */
            tempDouble = winPerc[i];
            winPerc[i] = winPerc[max_index];
            winPerc[max_index] = tempDouble;
            temp = teams[i].toString();
            teams[i] = teams[max_index].toString();
            teams[max_index] = temp.toString();

        } 

    } 

    /**
     * Method to print formatted list of mlb team names along with their win
     * percentages.
     * @param teams String array of team names.
     * @param winPerc Double array of win percentages.
     */
    public static void printMLB( String[] teams, double[] winPerc ){

        System.out.printf( "%-12s %-10s\n", "TEAM", "WIN PERC" );
        for( int count = 0; count < NUM_TEAMS; count++ ){

            System.out.printf( "%-12s %.3f%%\n", teams[count], winPerc[count] );

        }
    } 


}
