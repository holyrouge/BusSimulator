/**
 * The <code>Passenger</code> class represents a group of
 * passengers with instance variables for the size,
 * the destination, and the time arrived at bus stop.
 *
 * @author Prangon Ghose
 *      Email: prangon.ghose@stonybrook.edu
 *      Stony Brook ID: 111623211
 *      Section: 02
 *      Instructor: Professor Esmaili
 *      TA: Jamie Kunzmann
 *      Recitation: 01 (Tuesdays 11:30am - 12:23pm)
 */

public class Passenger {
    private int size; // size of the group
    private int destination; // passenger group's destination
    private int timeArrived; // time arrived at bus stop

    /**
     * Returns an instance of Passenger with template values.
     *
     * <dt><b>Postcondition:</b><db>
     *     This Passenger has been initialized with template values.
     */
    public Passenger() {
        this.size = 0;
        this.destination = 0;
        this.timeArrived = 0;
    }

    /**
     * Returns an instance of Passenger with given values.
     *
     * @param size
     *      The size of the group
     * @param destination
     *      The passenger group's destination
     * @param timeArrived
     *      The time at which the passenger arrived at the bus stop
     *
     * <dt><b>Precondition:</b><db>
     *      <code>size</code> is greater than 0. <code>timeArrived</code> is greater than or equal to 0.
     *
     * <dt><b>Postcondition:</b><db>
     *      This Passenger has been initialized with given values.
     *
     * @exception IllegalArgumentException
     *      Indicates that <code>size</code> is less than or equal to 0 or that <code>timeArrived</code>
     *      is less than 0.
     */
    public Passenger(int size, int destination, int timeArrived) throws IllegalArgumentException {
        if (size <= 0)
            throw new IllegalArgumentException("Invalid argument for Passenger's size");
        this.size = size;
        this.destination = destination;
        if (timeArrived < 0)
            throw new IllegalArgumentException("Invalid argument for Passenger's time arrived");
        this.timeArrived = timeArrived;
    }

    /**
     * Sets the size of the passenger group.
     *
     * @param size
     *      The size of the group
     *
     * <dt><b>Precondition:</b><db>
     *     This Passenger has been instantiated. <code>size</code> is greater than 0.
     *
     * @exception IllegalArgumentException
     *      Indicates that <code>size</code> is less than or equal to 0.
     */
    public void setSize(int size) {
        if (size <= 0) // throw exception if invalid
            throw new IllegalArgumentException("Invalid argument for Passenger's size");
        this.size = size;
    }

    /**
     * Returns the size of the passenger group.
     *
     * <dt><b>Precondition:</b><db>
     *      This Passenger has been instantiated.
     *
     * @return
     *      Returns the int value of <code>size</code>.
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the destination of the passenger group.
     *
     * @param destination
     *      The destination of the passenger group
     *
     * <dt><b>Precondition:</b><db>
     *     This Passenger has been instantiated.
     *
     */
    public void setDestination(int destination) {
        this.destination = destination;
    }

    /**
     * Returns the destination of the passener group
     *
     * <dt><b>Precondition:</b><db>
     *      This Passenger has been instantiated.
     *
     * @return
     *      Returns the int value of <code>destination</code>.
     */
    public int getDestination() {
        return destination;
    }

    /**
     * Sets the time that the passenger group arrived at the bus stop.
     *
     * @param timeArrived
     *      The time that the passenger group arrived at the bus stop
     *
     * <dt><b>Precondition:</b><db>
     *     This Passenger has been instantiated. <code>timeArrived</code> is greater than or equal to 0.
     *
     * @exception IllegalArgumentException
     *      Indicates that <code>timeArrived</code> is less than 0.
     */
    public void setTimeArrived(int timeArrived) {
        if (timeArrived < 0) // throw exception if invalid
            throw new IllegalArgumentException("Invalid argument for Passenger's time arrived");
        this.timeArrived = timeArrived;
    }

    /**
     * Returns the time that the passenger group arrived at the bus stop.
     *
     * <dt><b>Precondition:</b><db>
     *      This Passenger has been instantiated.
     *
     * @return
     *      Returns the int value of <code>timeArrived</code>.
     */
    public int getTimeArrived() {
        return timeArrived;
    }
}
