/**
 * The <code>EmptyQueueException</code> class calls an exception
 * if the <code>PerformanceQueue</code> is empty.
 *
 * @author Prangon Ghose
 *      Email: prangon.ghose@stonybrook.edu
 *      Stony Brook ID: 111623211
 *      Section: 02
 *      Instructor: Professor Esmaili
 *      TA: Jamie Kunzmann
 *      Recitation: 01 (Tuesdays 11:30am - 12:23pm)
 */

public class EmptyQueueException extends Exception {

    /**
     * Returns an instance of EmptyQueueException that calls
     * an Exception with the given error message.
     *
     * <dt><b>Postcondition:</b><db>
     *      This EmptyQueueException has been initialized and calls an Exception
     *      with the given error message.
     */
    public EmptyQueueException(String errorMessage) {super(errorMessage);}
}
