package assignment1arrayimplementation;

/**
 * ******************************************************************
 * Programmer:	sveinson Class: CS30S
 *
 * Assignment: simple static method example Program Name:
 * simpleStaticMethodExample
 *
 * Description: use static methods to perform some simple operations.
 *
 * Input: some integers from the keyboard
 *
 * Output: banners and closing messages
 **********************************************************************
 */
// import java libraries here as needed
import javax.swing.*;
//import java.text.DecimalFormat;
import java.io.*; 					// import file io libraries
import java.util.Random;

public class questionA {  // begin class

    public static  void main(String[] args)throws IOException {  // begin main

        // ********* declaration of constants **********
        // ********** declaration of variables **********
        String strin;				// string data input from keyboard
        String strout;				// processed info string to be output

        String banner;                          //string for printing banners
        String prompt;				// prompt for use in input dialogs
        
        String tokens[];
        String delim = " ";		// delimiter string for splitting input string
        String tabSpace = "      ";	// six spaces

        int max;
        int count;
        int countdracula = 0;
        int mastercount;
        
        // create instances of objects for i/o and formatting
        //ConsoleReader console = new ConsoleReader(System.in);
        //DecimalFormat df1 = new DecimalFormat("$##.00");
        BufferedReader fin = new BufferedReader(new FileReader("testOut.txt"));
        PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("testOut.txt")));

        // ********** Print output Banner **********
        // below are the mathod calls that tell the program to run the sub-program.
        printBanner();                      // print a banner to the output windowl
        banner = windowBanner();         // make a string to print in joption pane

        JOptionPane.showMessageDialog(null, banner);

        // ************************ get input **********************
        prompt = "Enter the number whose \nfrequency you would like to know.\n To skip enter 0 \n\n";

        // get input from the keyboard by calling the getNumber method
        // note the argument that is passed into the method
        // also note that since getNumber has a return type (int) you must use it
        // in a context where the returning value has someplace to go
        // ************************ processing ***************************
        try{
        Random rand = new Random();
        max = rand.nextInt(50) + 951;
        //  sum = add(op1, op2);            // add operands 1 and 2
        int[] arreh = new int[max];
        int[] echo = new int[max];
        int[]freq = new int[100];
        for (count = 0; count < max; count++) {
            arreh[count] = rand.nextInt(100) + 1;
        }//end for
          
         printarray(arreh, max, fout);
        fout.close();	// close output buffer
        

          // prime loop
         count = 0;
           strin = fin.readLine();    
          
           while(strin != null){
           echo[count] = Integer.parseInt(strin);
           count ++;
           strin = fin.readLine(); 
           }//end while
           
          for(mastercount = 0; mastercount < 100; mastercount++){         
           
              count = 0;    
           while(count < max){
               
           if(echo[count] == mastercount + 1){
           freq[mastercount]++;
           }//end if
           
               count ++;
               
           }//end while
          }//end for
          
          int n = getNumber(banner, prompt);
          delim = "-";
          
        // ************************ print output ****************************
        
        if(n > 0){
       System.out.println("frequency of "+ n + " = " + freq[n-1]);
        }//end if
        
        strin = JOptionPane.showInputDialog(banner, "enter the range of numbers you want. eg: 10-20\n enter 0 to skip.");
          tokens = strin.split(delim);
          n = Integer.parseInt(tokens[0]);
          int i = Integer.parseInt(tokens[1]);
        if(n > 0 && i > 0){
            for (n = n; n<=i; n++ ){
              System.out.print("number " + n + " = " + freq[n-1]+ ", ");  
            }//end for
        }//end if
        
        
        System.out.println("\nHighest frequency " + bignumarray(freq,100));
         System.out.println("Highest frequency " + smallnumarray(freq,100));
        // printarray(echo, max);
       System.out.println("Length of array " + count);
       count = 0;
       prompt = "see all the numbers of a certain frequency. enter -1 to skip.";
      n = getNumber(banner,prompt);
       while(count < 100){
        if(freq[count] == n) {
        System.out.println(count + 1 +" has a frequency of " + n);  
        }//end if
          count ++;
       }//end while 
       
        
        // ******** closing message *********
        System.out.println("end of processing.");

        // ***** close streams *****
        fin.close();			// close input buffer
        }//end try
        catch(ArrayIndexOutOfBoundsException e){
         JOptionPane.showMessageDialog(null,"Enter the correct amount of numbers","Try again", JOptionPane.ERROR_MESSAGE);
     }//end catch
    }  // end main

// ******************** static methods *****************************
    
    //************************************************
    // Purpose: load an array from disk
    // Interface: IN: inputfile buffer
    // Returns: none
    // *****************************************************
    public static int loadsimplearray(int[] list, BufferedReader fin) throws IOException {
        String strin = fin.readLine();     // prime loop
        int count = 0;
        
        while (strin != null){
            
            list[count] = Integer.parseInt(strin);
            count++;               // count actual array elements

            //System.out.println(strin);
            strin = fin.readLine();
        } // end eof
        
       fin.close();
        return count;
    }//end load list

    

    //************************************************
    // Purpose: to find the smallest number in an array
    // Interface: IN: the array and length of it
    // Returns: the smallest number in an array
    // *****************************************************
    public static int smallnumarray(int[] arreh, int max) {
        int count = 0;
        int last = 0;
        last = arreh[0];
        for (count = 0; count < max; count++) {
            if (last > arreh[count]) {
                last = arreh[count];
            }//end if
        } //end for
        return last;
    }//end smallnum

    //************************************************
    // Purpose: to find the biggest number in an array
    // Interface: IN: the array and length of it
    // Returns: the biggest number in an array
    // *****************************************************
    public static int bignumarray(int[] arreh, int max) {
        int count = 0;
        int last = 0;
        last = arreh[0];
        for (count = 0; count < max; count++) {

            if (last < arreh[count]) {
                last = arreh[count];
            }//end if
        } //end for
        return last;
    }//end bignum

    //************************************************
    // Purpose: to print an array
    // Interface: IN: the array and length of it
    // Returns: array as a string
    // *****************************************************
    public static void printarray(int[] arreh, int max, PrintWriter fout) {
        for (int count = 0; count < max; count++) {
            fout.println(arreh[count]);
        } //end for     
        fout.close();
    }//end printarray

    //************************************************
    // Purpose: to print an array
    // Interface: IN: the array and length of it
    // Returns: array as a string
    // *****************************************************
    public static void printarray(int[] arreh, int max) {
        for (int count = 0; count < max; count++) {
            System.out.println(arreh[count]);
        } //end for 

    }//end printarray
    
    //************************************************
    // Purpose: to add an array
    // Interface: IN: the array and length of it
    // Returns: the sum of an array
    // *****************************************************
    public static int addarray(int[] arreh, int max) {
        int sum = 0;
        for (int count = 0; count < max; count++) {
            sum = sum + arreh[count];
        } //end for 
        return sum;
    }//end printarray

    //************************************************
    // Purpose: to average an array
    // Interface: IN: the array and length of it
    // Returns: the average of an array
    // *****************************************************
    public static double averagearray(int[] arreh, int max) {
        double average = addarray(arreh, max) / max;
        return average;
    }//end average

    //************************************************
    // Purpose: get one int value from the keyboad
    // Interface: IN: string prompt
    // Returns: int read from keyboard buffer
    // *****************************************************
    public static int getNumber(String banner, String promt) {
        int n = 0;          // local varible to hold integer from keyboard buffer
        n = Integer.parseInt(JOptionPane.showInputDialog(banner, promt));
        return (n);
    } // end getNumber

    //************************************************
    // Purpose: print an output banner to the output window
    // Interface: IN: none
    // Returns: none
    // *****************************************************
    public static void printBanner() {
        System.out.println("*******************************************\\n");
        System.out.println("Name:		Evan Chartrand");
        System.out.println("Class:		CS30S");
        System.out.println("Assignment:	Ax Qy");
        System.out.println("*******************************************");
    }  // end print banner

    //************************************************
    // Purpose: print an output banner to the output file
    // Interface: IN: PrintWriter
    // Returns: none
    // *****************************************************
    public static void fileBanner(PrintWriter fo) {
        fo.println("*******************************************");
        fo.println("Name:		Evan Chartrand");
        fo.println("Class:		CS30S");
        fo.println("Assignment:	Ax Qy");
        fo.println("*******************************************");
    } // end fileBanner

    //************************************************
    // Purpose: Make an output banner string to use with message
    //          dialogs
    // Interface: IN: none
    // Returns: output banner string
    // *****************************************************
    public static String windowBanner() {
        // *** local variabls ***

        String banner;               // output banner for windows

        // **** create banner ****
        banner = "*******************************************\n";
        banner += "Name:		Evan Chartrand\n";
        banner += "Class:		CS30S\n";
        banner += "Assignment:	Ax Qy\n";
        banner += "*******************************************\n\n";

        // *** return the banner ***
        return banner;
    } // end windowBanner
}  // end clas

