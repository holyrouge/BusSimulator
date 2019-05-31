/**
 * The <code>PassengerQueue</code> class represents the line
 * of passengers waiting for the bus at a stop. It extends
 * the LinkedList class from the Java API.
 *
 * @author Prangon Ghose
 *      Email: prangon.ghose@stonybrook.edu
 *      Stony Brook ID: 111623211
 *      Section: 02
 *      Instructor: Professor Esmaili
 *      TA: Jamie Kunzmann
 *      Recitation: 01 (Tuesdays 11:30am - 12:23pm)
 */
import java.util.LinkedList;

public class PassengerQueue extends LinkedList<Passenger> {
    private int size; // number of Passenger objects in PassengerQueue

    /**
     * Returns an instance of PassengerQueue.
     *
     * <dt><b>Postcondition:</b><db>
     *     This PassengerQueue has been initialized with its extended LinkedList parent class.
     */
    public PassengerQueue() {
        super();
        this.size = 0;
    }

    /**
     * Adds the given Passenger to the end of the queue.
     *
     * @param newPassenger
     *      A <code>Passenger</code> object that is to be added to the queue
     *
     * <dt><b>Precondition:</b><db>
     *      This PassengerQueue has been initialized and the <code>newPassenger</code> input
     *      is not a null pointer.
     *
     * @exception NullPointerException
     *      Indicates that a null pointer has been given as an input.
     */
    public void enqueue(Passenger newPassenger) throws NullPointerException {
        if (newPassenger == null)
            throw new NullPointerException("Null pointer for new passenger");
        super.add(newPassenger);
        this.size++; // increment size
    }

    /**
     * Returns the first Passenger from the queue and removes it.
     *
     * <dt><b>Precondition:</b><db>
     *      This PassengerQueue has been initialized and it is not empty.
     *
     * <dt><b>Postcondition:</b><db>
     *      The first Passenger has been removed and the second Passenger
     *      now becomes the first Passenger.
     *
     * @return
     *      Returns the first <code>Passenger</code> in the queue.
     *
     * @exception EmptyQueueException
     *      Indicates that the <code>PassengerQueue</code> is empty.
     */
    public Passenger dequeue() throws EmptyQueueException {
        if (isEmpty())
            throw new EmptyQueueException("This PassengerQueue is empty");
        this.size--; // decrement size
        return super.remove();
    }

    /**
     * Returns the Passenger at the given index and removes it.
     *
     * @param index
     *      The index of the <code>Passenger</code> to be removed
     *
     * <dt><b>Precondition:</b><db>
     *      This PassengerQueue has been initialized, it is not empty and that <code>index</code>
     *      is between <code>size</code> is between <code>size</code> - 1 and 0, inclusive.
     *
     * <dt><b>Postcondition:</b><db>
     *      Removes and returns the <code>Passenger</code> at the given index and
     *      shifts all following Passengers forward.
     *
     * @return
     *      Returns the <code>Passenger</code> at <code>index</code> in the queue.
     *
     * @exception EmptyQueueException
     *      Indicates that the <code>PassengerQueue</code> is empty.
     *
     * @exception IllegalArgumentException
     *      Indicates that <code>index</code> is not inside the valid range.
     */
    public Passenger dequeueAt(int index) throws EmptyQueueException, IllegalArgumentException {
        if (isEmpty()) // if empty, throw exception
            throw new EmptyQueueException("This PassengerQueue is empty");
        if (index >= this.size || index < 0) // if invalid range, throw exception
            throw new IllegalArgumentException("Index is not in the valid range");
        this.size--;
        return super.remove(index);
    }

    /**
     * Returns the Passenger at the head of the queue, without removing it.
     *
     * <dt><b>Precondition:</b><db>
     *      This PassengerQueue has been initialized.
     *
     * @return
     *      Returns the <code>Passenger</code> at the top of the queue.
     */
    public Passenger peek() {
        return super.peek();
    }

    /**
     * Returns the total number of passengers in the queue.
     *
     * <dt><b>Precondition:</b><db>
     *      This PassengerQueue has been initialized.
     *
     * @return
     *      Returns the total number of passengers in the queue, not the
     *      number of <code>Passenger</code> objects.
     */
    public int size() {
        int numberOfPassengers = 0;
        for (int i = 0; i < this.size; i++) { // go through each Passenger object in the queue and add their sizes
            numberOfPassengers += super.get(i).getSize();
        }
        return numberOfPassengers; // return sum
    }

    /**
     * Returns the total number of Passenger objectsin the queue.
     *
     * <dt><b>Precondition:</b><db>
     *      This PassengerQueue has been initialized.
     *
     * @return
     *      Returns the total number of Passenger objects
     *      in queue as int value of <code>size</code>.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Returns the number of passenger groups.
     *
     * <dt><b>Precondition:</b><db>
     *      This PassengerQueue has been initialized.
     *
     * @return
     *      Returns the int value of <code>size</code>, the number of Passenger objects
     *      in the LinkedList.
     */
    public int getNumberOfPassengers() {
        return this.size;
    }

    /**
     * Returns true if the queue is empty, false when it is not.
     *
     * <dt><b>Precondition:</b><db>
     *      This PassengerQueue has been initialized.
     *
     * @return
     *      Returns a boolean value of true when the queue is empty,
     *      false when it is not.
     */
    public boolean isEmpty() {
        return super.isEmpty();
    }

    /**
     * Removes groups of passengers
     *
     * <dt><b>Precondition:</b><db>
     *      This PassengerQueue has been initialized.
     *
     * @param dest
     *      The destination that the Bus is current at.
     *
     * @return
     *      Returns the number of passengers that have been removed from the queue.
     */
    public int unload(int dest) {
        int numOfPassengers = 0; // variable to store the number of passengers unloaded

        for (int i = 0; i < this.size; i++) { // go through queue
            if (this.get(i).getDestination() == dest) { // if passenger destination is current destination, remove them
                try {
                    numOfPassengers += this.get(i).getSize();
                    this.dequeueAt(i);
                }
                catch (Exception ex) {
                    System.out.println("No Passenger at given index");
                }
            }
        }

        return numOfPassengers; // return number of passengers unloaded
    }
}