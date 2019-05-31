/**
 * The <code>Bus</code> class represents a bus in the
 * transportation system. It contains instance
 * variables for its route, its next stop, and it
 * keeps track of all the passenger on-board.
 *
 * @author Prangon Ghose
 *      Email: prangon.ghose@stonybrook.edu
 *      Stony Brook ID: 111623211
 *      Section: 02
 *      Instructor: Professor Esmaili
 *      TA: Jamie Kunzmann
 *      Recitation: 01 (Tuesdays 11:30am - 12:23pm)
 */

public class Bus {
    private static int capacity; // static variable to store capacity of the buses
    private char route; // the type of bus
    private int nextStop; // stores the next stop
    private int toNextStop; // time until the next stop
    private int timeToRest; // signifies if the Bus is operating or not
    private PassengerQueue busPassengers; // Passengers in the bus

    /**
     * Returns an instance of Bus.
     *
     * <dt><b>Postcondition:</b><db>
     *     This Bus has been initialized with template values.
     */
    public Bus() {
        this.nextStop = 0;
        this.toNextStop = 0;
        this.timeToRest = 0;
        busPassengers = new PassengerQueue();
    }

    /**
     * Sets the capacity for the buses.
     *
     * @param capacity
     *      The capacity of the bus
     *
     * <dt><b>Precondition:</b><db>
     *     <code>capacity</code> is greater than or equal to 0.
     *
     * @exception IllegalArgumentException
     *      Indicates that <code>Bus</code> is less than 0.
     */
    public static void setCapacity(int capacity) throws IllegalArgumentException {
        if (capacity < 0)
            throw new IllegalArgumentException("Invalid input for capacity");
        Bus.capacity = capacity;
    }

    /**
     * Returns the capacity for the buses.
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>Bus</code> has been initialized.
     *
     * @return
     *      Returns the int value of the static variable <code>capacity</code>.
     */
    public static int getCapacity() {
        return capacity;
    }


    /**
     * Sets the route of the Bus.
     *
     * @param route
     *      The route identifier of the Bus
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>Bus</code> has been initialized.
     */
    public void setRoute(char route) {
        this.route = route;
    }

    /**
     * Returns the route identifier of the Bus.
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>Bus</code> has been initialized.
     *
     * @return
     *      Returns the route identifier as the char value of <code>route</code>.
     */
    public char getRoute() {
        return route;
    }

    /**
     * Returns the passengers within the Bus.
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>Bus</code> has been initialized.
     *
     * @return
     *      Returns the passengers within the Bus as a <code>PassengerQueue</code>.
     */
    public PassengerQueue getBusPassengers() {
        return busPassengers;
    }

    /**
     * Sets the Passenger objects in the Bus.
     *
     * @param busPassengers
     *      The Passenger objects in the Bus
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>Bus</code> has been initialized.
     */
    public void setBusPassengers(PassengerQueue busPassengers) {
        this.busPassengers = busPassengers;
    }

    /**
     * Sets the next stop of the Bus.
     *
     * @param nextStop
     *      The next stop of the Bus
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>Bus</code> has been initialized.
     */
    public void setNextStop(int nextStop) {
        this.nextStop = nextStop;
    }

    /**
     * Returns the next stop of the Bus.
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>Bus</code> has been initialized.
     *
     * @return
     *      Returns the next stop of the Bus as an int value of <code>nextStop</code>.
     */
    public int getNextStop() {
        return nextStop;
    }

    /**
     * Sets the time to rest of the Bus.
     *
     * @param timeToRest
     *      The next stop of the Bus
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>Bus</code> has been initialized.
     */
    public void setTimeToRest(int timeToRest) {
        this.timeToRest = timeToRest;
    }

    /**
     * Returns the time to rest value of Bus.
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>Bus</code> has been initialized.
     *
     * @return
     *      Returns the time to rest value of the Bus as int value of <oode>timeToRest</oode>.
     */
    public int getTimeToRest() {
        return timeToRest;
    }

    /**
     * Sets the time it will take to get to the next stop for the Bus.
     *
     * @param toNextStop
     *      The time it will take to get to the next stop
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>Bus</code> has been initialized.
     */
    public void setToNextStop(int toNextStop) {
        this.toNextStop = toNextStop;
    }

    /**
     * Returns the time it will take until the next stop of the Bus.
     *
     * <dt><b>Precondition:</b><db>
     *     This <code>Bus</code> has been initialized.
     *
     * @return
     *      Returns the time it will take until the next stop of the Bus as int value of <code>toNextStop</code>.
     */
    public int getToNextStop() {
        return toNextStop;
    }
}
