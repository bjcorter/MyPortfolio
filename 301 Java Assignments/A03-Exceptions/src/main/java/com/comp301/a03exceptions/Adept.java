package com.comp301.a03exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import java.io.BufferedReader;

public class Adept {

    /**
     * Section 4: Try/catch/finally block execution tracing
     * <p>
     * This method should call class method section1() from the Novice class, passing in n as the
     * parameter. Surround the section1() method call in a try-catch-finally block, and use it to
     * catch only the exception that is associated with an n value of five.
     * <p>
     * Next, use the Printer object, which is passed as a parameter to section4(), to add the
     * following "print" statements to your code. See the Printer interface for information about how
     * to use the overloaded print() method of the Printer object.
     * <p>
     * If an exception is caught, use the Printer object to print "If we get here, that means an
     * exception was caught". If no exception occurs, use the Printer object to print "If we get here,
     * that means no exception occurred". Regardless of whether an exception occurs or not, use the
     * Printer object to print "If we get here, the try/catch block is done, but an exception may not
     * have been caught". If the try/catch block finishes successfully (i.e. either an exception
     * occurred and was caught or no exception occurred at all), then use the Printer object to print
     * "If we get here, we made it through the try/catch block and caught any exceptions that may have
     * occurred".
     */
    public static void section4(int n, Printer printer) {
        try {
            Novice.section1(n);
            if (n != 5) {
                printer.print("If we get here, that means no exception occurred");
            }
        } catch (RuntimeException e) {
            if (n == 5) {
                printer.print("If we get here, that means an exception was caught");
            }
        } finally {
            printer.print("If we get here, the try/catch block is done, but an exception may not have been caught");
        }
        printer.print("If we get here, we made it through the try/catch block and caught any exceptions that may have occurred");
    }

    /**
     * Section 5: Throwing a checked exception
     * <p>
     * This method takes a URL string as input, and opens an HTTP stream that can be used to download
     * the webpage associated with the URL. The method should not catch any exceptions associated with
     * the process of downloading the webpage; any associated exceptions should be thrown to the
     * caller.
     */
    public static BufferedReader section5(String urlText) throws IOException {
        URL url = new URL(urlText);
        InputStream inputStream = url.openStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        return new BufferedReader(inputStreamReader);
    }

    /**
     * Section 6: Catching a checked exception
     * <p>
     * This method takes a URL string as input, and calls class method section5() to obtain an open
     * BufferedReader object to the webpage. Surround the call to section5() in a try/catch/finally
     * block. Use it to catch the IOException that may be thrown if something goes wrong while
     * downloading the webpage from the internet.
     * <p>
     * In the finally block, make sure you close the BufferedReader file stream! Use the close()
     * method to accomplish this.
     * <p>
     * In the catch block, return the string "An error occurred and the page could not be downloaded".
     * See: https://docs.oracle.com/javase/7/docs/api/java/io/BufferedReader.html#close()
     * <p>
     * If no exception is thrown, that means we have an active stream. Check out the readLine() method
     * in the BufferedReader class; it reads the website contents one line at a time. Once readLine()
     * returns null, that means there are no lines left to read. Use a while() loop to read each line
     * of the website, and accumulate all lines in a String. Once a "null" line is encountered, return
     * the accumulated contents as a String. See: https://docs.oracle.com/javase/7/docs/api/java/io/BufferedReader.html#readLine()
     */
    public static String section6(String urlText) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = section5(urlText);
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        } catch (IOException e) {
            return "An error occurred and the page could not be downloaded";
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
