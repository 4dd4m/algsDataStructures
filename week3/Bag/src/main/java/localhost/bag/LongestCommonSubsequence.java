package localhost.bag;
import localhost.bag.ArrayBag;
import localhost.bag.BagInterface;

/**
 * Lab Session 2
 * LongestCommonSubsequence class is a program that will determine the longest string
 * that is a subsequence of two stings input by the user (in the command line).
 * The program applies a brute force solution to finding the subsequence.
 *  Uses the ArrayBag class for the Bag implementation.
 */

public class LongestCommonSubsequence {


    public static void main(String args[]) {
        int String;

        ArrayBag<String> testBag = new ArrayBag<>(5);
        String strBestSubsequence = null;

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("This program determines the longest string that is a subsequence of two input strings.");
        System.out.println("Please enter the first string:");
        String strFirst = "CBACBA";//input.next();

        System.out.println("Please enter the second string:");
        String strSecond = "ABC";//input.next();
        System.out.println("\n");

        // TODO (2): Add code to create an instance of the Bag (ArrayBag) then add the initial (first) string:
        testBag.addNewEntry(strFirst);


        // Print out the current contents of the bag:
        System.out.println("The strings to check are: " + testBag.toArray());

        // Assign the empty string to the longest match subsequence:
        strBestSubsequence = new String("");


        // Loop to check the strings in the Bag:
        while (testBag.getCurrentSize() > 0) {

            // TODO (3): Remove a test string from the bag: (modify the line below this one)
            String strTest = testBag.remove();
            System.out.println("Now checking: " + strTest);

            // If longest match is shorter than test string:
            if (strTest.length() > strBestSubsequence.length()) {


                if (isSubsequence(strTest,strSecond )) {

                    // TODO (4): If it is a subsequence, then set the longest match to the test string
                    //           by adding a line of code immediately below this one
                    int i =0;
                   for (int j = 0; i < strTest.length() && j < strSecond.length(); j++) {
                    if (strTest.charAt(i) == strSecond.charAt(j)) {
                    i++;
                    }
                   }
                    if (i > strBestSubsequence.length()) {
                        strBestSubsequence =  strTest;
                    }
                    

                    System.out.println("Found a subsequence");

                } else {

                    // If the test string is at least two longer than the longest match:
                    if (strTest.length() - 1 > strBestSubsequence.length()) {

                        // TODO (5): Generate new strings from test by removing each single character and
                        // TODO (5): place each new string in the bag. Add the new code into this 'if' block
                           int  i = 0;
                           while(i < strTest.length()){
                               String newString = strTest.substring(0,i) + strTest.substring(i+1);
                               testBag.addNewEntry(newString);
                               i++;
                           }
                    }

                }
            }

            // Print the bag of strings to check:
            System.out.println("The bag of new strings to check is now: " + testBag);
            System.out.println("Size: " + testBag.getCurrentSize());
            System.out.println();

        }

        // Print the longest match:
        System.out.println("Found " + strBestSubsequence + " for the longest common subsequence");
        System.out.println("-------------------------------------------------------------------------------------");

    }

    /**
     * Method to determine if one string is a subsequence of the other.
     * @param strCheck See if this is a subsequence of the other argument.
     * @param strAgainst The string to check against.
     * @return     A boolean if check is a subsequence of other.
     */
    public static boolean isSubsequence(String strCheck, String strAgainst) {

        boolean bResult = false;

        // Check if a subsequence exists
        // Only check if it is no longer than the against string:
        if (strCheck.length() <= strAgainst.length()) {
            int i = 0;
            for (int j = 0; i < strCheck.length() && j < strAgainst.length(); j++) {
                if (strCheck.charAt(i) == strAgainst.charAt(j)) {
                    i++;
                }
            }
            bResult = (i == strCheck.length());
        }
        return bResult;
    }
}
