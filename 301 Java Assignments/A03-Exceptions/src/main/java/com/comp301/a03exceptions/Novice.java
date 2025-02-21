package com.comp301.a03exceptions;

public class Novice {

    /**
     * Section 1: Throwing exceptions
     * <p>
     * Upon execution, this method should throw a specific subclass of RuntimeException. Which
     * subclass to throw depends on the value of input parameter n.
     * <p>
     * If n is negative, throw the RuntimeException subclass which is used to indicate that a required
     * operation is not supported.
     * <p>
     * If n is zero, throw the RuntimeException subclass which is used to indicate that an index is
     * invalid.
     * <p>
     * If n is one, throw the RuntimeException subclass which is used to indicate that an array index
     * is invalid.
     * <p>
     * If n is two, throw the RuntimeException subclass which is used to indicate that an invalid
     * state was detected in the application.
     * <p>
     * If n is three, throw the RuntimeException subclass which is used to indicate that an invalid
     * argument value was provided to the method.
     * <p>
     * If n is four, throw the RuntimeException subclass which is used to indicate that an arithmetic
     * error occurred during execution of the method.
     * <p>
     * If n is any multiple of five, throw the RuntimeException subclass which is used to indicate
     * that an illegal operation was performed on a null reference. Pass a message string to the
     * exception's constructor, with the message "The value of n is [insert value of n here]". For
     * example, if n is 15 (which is a multiple of five), the exception message should be "The value
     * of n is 15".
     * <p>
     * If n is none of these values, simply return n.
     */
    public static int section1(int n) {
        if (n < 0) {
            throw new UnsupportedOperationException();
        } else if (n == 0) {
            throw new IndexOutOfBoundsException();
        } else if (n == 1) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (n == 2) {
            throw new IllegalStateException();
        } else if (n == 3) {
            throw new IllegalArgumentException();
        } else if (n == 4) {
            throw new ArithmeticException();
        } else if (n % 5 == 0) {
            throw new NullPointerException("The value of n is " + n);
        } else {
            return n;
        }
    }

    /**
     * Section 2: Catching exceptions
     * <p>
     * This method should call method section1(), pass in the argument n as the parameter, and return
     * the result. However, you should surround the section1() method call in a try-catch block, and
     * use it to catch the exceptions specifically associated with n values of three, four, and five.
     * Don't catch any of the other exceptions that section1() might throw.
     * <p>
     * If the exception associated with an n value of three is caught, this method should instead
     * throw the exception associated with an n value of four. If the n=4 exception is caught, you
     * should instead throw the n=5 exception. And if the n=5 exception is caught, you should return
     * -1.
     */
    public static int section2(int n) {
        try {
            int result = section1(n);
            return result;
        } catch (IllegalArgumentException e) {
            throw new ArithmeticException();
        } catch (ArithmeticException e) {
            throw new NullPointerException("The value of n is " + n);
        } catch (NullPointerException e) {
            return -1;
        }
    }

    /**
     * Section 3: Try/catch block execution tracing
     * <p>
     * This method should call method section1(), passing in n as the parameter. Surround the
     * section1() method call in a try-catch block, and use it to catch any RuntimeException that may
     * occur.
     * <p>
     * Next, use the Printer object, which is passed as a parameter to section3(), to add the
     * following "print" statements to your code. See the Printer interface for information about how
     * to use the overloaded print() method of the Printer object.
     * <p>
     * If an exception is caught, use the Printer object to print "If we get here, that means an
     * exception was caught". If no exception occurs, use the Printer object to print "If we get here,
     * that means no exception occurred". Afterwards, print "All done" whether or not an exception
     * occurred.
     */
    public static void section3(int n, Printer printer) {
        try {
            section1(n);
            printer.print("If we get here, that means no exception occurred");
        } catch (RuntimeException e) {
            printer.print("If we get here, that means an exception was caught");
        } finally {
            printer.print("All done");
        }
    }
}
