package unl.cse.file_io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class StateData {
    
    /* Constants */
    public final int NUM_STATES = 50;
    
    /* Name of the file to be read */
    private String fileName;
    private final String outputFile = "data/stateData.dat";

    /* Arrays to hold state name data and population data */
    private String[] states = new String[NUM_STATES];
    private int[] statesPops = new int[NUM_STATES];

    /* Track the total population count */
    private int total;

    /* Class constructor */
    public StateData( String inputFile ){
        fileName = inputFile;
        this.getInputFromFile();
    } 

    /**
     * Read the contents of the input file and store each line in a temporary
     * array as an entry.  Invoke processInput() to organize and separate
     * String from integer data.  
     */
    private void getInputFromFile(){
        
        /* Prepare to obtain the guess stateInfo from the text file */
        File stateInfo = null;
        Scanner input = null;
        try{
            /* Pass a file object to Scanner, instead of System.in */
        	stateInfo = new File( fileName );
            input = new Scanner( stateInfo );
            /* Get the data from the text file using Scanner */
            int i=0;
            while( input.hasNext() ){
                
            	String line = input.nextLine();
            	String data[] = line.split(",");
                this.states[i] = data[0].trim();
                this.statesPops[i] = Integer.parseInt(data[1].trim());
                this.total += this.statesPops[i];
                i++;
            } // End input collection while loop
               
        }catch ( FileNotFoundException notFound ) {
            System.err.println("File not found.  Please be sure that the input file stateData.txt is in the working directory." );
            System.err.println( "Program closing...\n" );
            System.exit( 1 );
        } catch (Exception e) {
        	System.err.println("A general error occurred: ");
        	e.printStackTrace();
            System.err.println( "Program closing...\n" );
            System.exit( 1 );
        } finally {
        	if(input != null) {
        		input.close();
        	}
        }

    } 


    /**
     * Prints the processed data to standard output, a state and it's
     * population on each line.
     */
    public void printData(){

        // int total = 0;
        System.out.printf( "%-15s %s", "State:", "Population:\n" );
        for( int index = 0; index < states.length; index++ ){
            
            System.out.printf( "%-15s %d\n", states[index], statesPops[index] );
        }
        System.out.printf( "\n%-15s %d\n", "TOTAL", total );
                
    }

    /**
     * Sends the output to a binary file on disk.  The output file will be
     * named whatever String outputFile is initialized to.
     */
    public void printToBinaryFile(){

    	byte space[] = {0x20};
    	
    	OutputStream output = null;
    	try {
    		output = new BufferedOutputStream(new FileOutputStream(this.outputFile));
    		for(int i=0; i<this.NUM_STATES; i++) {
    			//convet the string to raw byte data
    			byte[] bytes = this.states[i].getBytes();
    			//pad out with leading "spaces" to exactly 15 bytes for state names
    			for(int j=0; j<15-bytes.length; j++) {
    				output.write(space);
    			}
    			output.write(bytes);

    			//convert the int to 4 raw bytes
    			ByteBuffer bb = ByteBuffer.allocate(4); 
    		    bb.putInt(this.statesPops[i]); 
    		    bytes = bb.array();
    			output.write(bytes);
    		}
			output.close();
    	} catch(FileNotFoundException ex) {
            System.err.println("File not found.  Please be sure that the input file stateData.txt is in the working directory." );
            System.err.println( "Program closing...\n" );
            System.exit( 1 );
    	} catch(IOException ioex) {
            System.err.println("Input/Output exception writing to file, program closing...");
            System.exit( 1 );
    	}
    } 

    /**
     * Writes an XML file to disk using the collected state name and
     * respective populations.
     */
    public void toXMLFile(){
        
        // Implement this method to create an XML file.



    } 

    public static void main( String[] args ){
        
        /* Specify the input file that contains the data */
        String file = "data/stateData.txt";
        
        /* Instantiate the object to read the input file */
        StateData censusResults = new StateData( file );

        /* Print the data to standard output */
        censusResults.printData();

        /* Write the same data to an external .dat file */
        censusResults.printToBinaryFile();

        /* Write the XML file */
        //TODO: Invoke toXML method here to create your XML file

    } 

} 