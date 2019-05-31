/**
 * The <code>Simulator</code> class takes in user-inputted
 * parameters to run a simulation of the transportation system.
 *
 * @author Prangon Ghose
 *      Email: prangon.ghose@stonybrook.edu
 *      Stony Brook ID: 111623211
 *      Section: 02
 *      Instructor: Professor Esmaili
 *      TA: Jamie Kunzmann
 *      Recitation: 01 (Tuesdays 11:30am - 12:23pm)
 */
import java.util.Scanner;

public class Simulator {
    private final int NUM_BUS_STOPS = 8; // constant for the number of bus stops
    private final int TIME_INBETWEEN_STOPS = 5; // constant for time in between stops
    private final int REST_TIME = 10; // constant for rest time of buses
    private int numInBuses; // number in route buses
    private int numOutBuses; // number of out route buses
    private int minGroupSize; // minimum group size for Passenger object
    private int maxGroupSize; // maximum group size for Passenger object
    private int capacity; // capacitor for the buses
    private double arrivalProb; // probability of passenger arrival at a bus stop

    public static void main(String[] args) {
        Simulator currentSimulator; // create new Simulator object
        int inBuses, outBuses, minSize, maxSize, cap, duration; // variables to hold user input
        double arrProb; // variable to hold user input
        Scanner sc = new Scanner(System.in); // scanner object to get user input

        while (true) { // while loop to run simulation consecutively without restarting program
            System.out.println("\nWelcome to the Stony Brook University Bus Transportation Simulator! Enter the required "
                    + "values to begin. ");

            while (true) { // while + try/catch to take user input and verify their validity
                try {
                    System.out.print("Enter the number of In route buses: ");
                    inBuses = sc.nextInt();
                    if (inBuses < 0) {
                        System.out.println("Input is in invalid range.");
                        continue;
                    }
                    break;
                } catch (Exception ex) {
                    System.out.println("Invalid Input! Try again.");
                }
            }

            while (true) {
                try {
                    System.out.print("Enter the number of Out route buses: ");
                    outBuses = sc.nextInt();
                    if (outBuses < 0) {
                        System.out.println("Input is in invalid range.");
                        continue;
                    }
                    break;
                } catch (Exception ex) {
                    System.out.println("Invalid Input! Try again.");
                }
            }

            while (true) {
                try {
                    System.out.print("Enter the minimum group size of passengers: ");
                    minSize = sc.nextInt();
                    if (minSize < 0) {
                        System.out.println("Input is in invalid range.");
                        continue;
                    }
                    break;
                } catch (Exception ex) {
                    System.out.println("Invalid Input! Try again.");
                }
            }

            while (true) {
                try {
                    System.out.print("Enter the maximum group size of passengers: ");
                    maxSize = sc.nextInt();
                    if (maxSize < minSize) {
                        System.out.println("Input is in invalid range.");
                        continue;
                    }
                    break;
                } catch (Exception ex) {
                    System.out.println("Invalid Input! Try again.");
                }
            }

            while (true) {
                try {
                    System.out.print("Enter the capacity of a bus: ");
                    cap = sc.nextInt();
                    if (cap < 0) {
                        System.out.println("Input is in invalid range.");
                        continue;
                    }
                    break;
                } catch (Exception ex) {
                    System.out.println("Invalid Input! Try again.");
                }
            }

            while (true) {
                try {
                    System.out.print("Enter the arrival probability: ");
                    arrProb = sc.nextDouble();
                    if (arrProb < 0.0 || arrProb > 1.0) {
                        System.out.println("Input is in invalid range.");
                        continue;
                    }
                    break;
                } catch (Exception ex) {
                    System.out.println("Invalid Input! Try again.");
                }
            }

            while (true) {
                try {
                    System.out.print("Enter the duration of the simulation: ");
                    duration = sc.nextInt();
                    if (duration < 0) {
                        System.out.println("Input is in invalid range.");
                        continue;
                    }
                    break;
                } catch (Exception ex) {
                    System.out.println("Invalid Input! Try again.");
                }
            }

            // instantiate simulator object
            currentSimulator = new Simulator(inBuses, outBuses, minSize, maxSize, cap, arrProb);
            // run simulation and store simulation data
            double[] data = currentSimulator.simulate(duration);

            // output results
            System.out.println("\n" + (int)data[0] + " group(s) of passengers served. Average wait time is " +
                    data[1] + " minutes.");

            char contd;
            while (true) { // ask user if the simulation should be run again
                try {
                    System.out.print("Perform another simulation (Y/N): ");
                    contd = sc.next().toUpperCase().charAt(0);
                    if (contd == 'Y' || contd == 'N') {
                        break;
                    }
                    System.out.println("Invalid Input! Try again.");
                    continue;
                }
                catch (Exception ex) {
                    System.out.println("Invalid Input! Try again.");
                }
            }

            if (contd == 'N') { // if no, end program
                System.out.println("Program Terminating...");
                System.exit(0);
            }
            else { // if yes, loop through while loop again
                System.out.println("Restarting simulator...");
            }
        }

    }

    /**
     * Returns an instance of the Simulator class with the given values.
     *
     * @param numInBuses
     *      The number of buses in the In route
     *
     * @param numOutBuses
     *      The number of buses in the Out route
     *
     * @param minGroupSize
     *      The minimum group size of Passengers
     *
     * @param maxGroupSize
     *      The maximum group size of Passengers
     *
     * @param capacity
     *      The capacity of a Bus
     *
     * @param arrivalProb
     *      The probability of Passenger arrival at a BusStop
     *
     * <dt><b>Postcondition:</b><db>
     *     This Simulator has been initialized with the given values.
     *
     */
    public Simulator(int numInBuses, int numOutBuses, int minGroupSize, int maxGroupSize, int capacity,
                     double arrivalProb) {
        this.numInBuses = numInBuses;
        this.numOutBuses = numOutBuses;
        this.minGroupSize = minGroupSize;
        this.maxGroupSize = maxGroupSize;
        this.capacity = capacity;
        this.arrivalProb = arrivalProb;
    }

    /**
     * Runs the simulation of the bus transportation system.
     *
     * <dt><b>Precondition:</b><db>
     *     This Simulator has been initialiazed. <code>duration</code> is greater than 0.
     *
     * @param duration
     *      The duration of the simulation (in minute increments)
     *
     * @return
     *      Returns a double array containing the total number of groups served and the average wait time.
     */
    public double[] simulate(int duration) {
        int groupsServed = 0, totalTimeWaited = 0; // variables for output data
        /*
            Create the bus stop queues:
                0 - In Route South P
                1 - In Route West
                2 - In Route SAC
                3 - In Route Chapin
                4 - Out Route South P
                5 - Out Route PathMart
                6 - Out Route Walmart
                7 - Out Route Target
        */
        PassengerQueue[] busStops = new PassengerQueue[NUM_BUS_STOPS]; // create bus stops
        for (int i = 0; i < busStops.length; i++) {
            busStops[i] = new PassengerQueue(); // instantiate all bus stops
        }

        String[] busStopNames = {"South P", "West", "SAC", "Chapin", "South P", "PathMart", "Walmart", "Target",
        "South P"}; // create array of bus stop names


        Bus[] inBuses = new Bus[numInBuses]; // create array of in route buses
        Bus.setCapacity(capacity); // set capacity for all buses
        for (int i = 0; i < inBuses.length; i++) { // instantiate in route buses
            inBuses[i] = new Bus();
            inBuses[i].setRoute('i');
            inBuses[i].setNextStop(0);
            if (i == 0) {
                inBuses[i].setToNextStop(1);
            }
            else {
                inBuses[i].setToNextStop(i * REST_TIME);
            }
            inBuses[i].setTimeToRest(i * REST_TIME);
        }

        Bus[] outBuses = new Bus[numOutBuses]; // create array of out route buses
        for (int i = 0; i < outBuses.length; i++) { // instantiate out route buses
            outBuses[i] = new Bus();
            outBuses[i].setRoute('o');
            outBuses[i].setNextStop(4);
            if (i == 0) {
                outBuses[i].setToNextStop(1);
            }
            else {
                outBuses[i].setToNextStop(i * REST_TIME);
            }
            outBuses[i].setTimeToRest(i * REST_TIME);
        }


        for (int i = 1; i <= duration; i++) { // start simulation (go through each minute)
            System.out.println("\nMinute " + i);

            // bus stops
            for (int b = 0; b < busStops.length; b++) {
                if (probOfArrival(arrivalProb)) { // using arrivalProb, determine if a new
                    // passenger group has arrived and if so, instantiate and add them to
                    // the bus stop queue
                    int dest;
                    while (true) {
                        if (b < 4) {
                            dest = randInt(0, 3);
                        }
                        else {
                            dest = randInt(4, 7);
                        }

                        if (dest == b) {
                            continue;
                        }
                        break;
                    }
                    // create and instantiate new passenger
                    Passenger newPassenger = new Passenger(randInt(minGroupSize, maxGroupSize), dest, i);
                    busStops[b].enqueue(newPassenger); // add passenger to bus stop queue
                    System.out.println("A group of " + newPassenger.getSize() + " passengers arrived at " +
                            busStopNames[b] + " heading to " + busStopNames[dest] + ".");
                }
            }

            // in route buses
            for (int j = 0; j < inBuses.length; j++) {
                inBuses[j].setToNextStop(inBuses[j].getToNextStop() - 1); // decrement time it will take to get
                // to next stop
                int stop = inBuses[j].getNextStop(); // store current stop
                if (inBuses[j].getToNextStop() == 0 && stop == 4) { // if bus has arrived back at South P
                    int numDroppedOf = 0;
                    for (int x = 0; x < inBuses[j].getBusPassengers().getSize(); x++) {
                        try { // dequeue all passengers
                            Passenger temp = inBuses[j].getBusPassengers().dequeue();
                            numDroppedOf += temp.getSize();
                            groupsServed++;
                        }
                        catch (Exception ex) {}
                    }
                    inBuses[j].setNextStop(0); // set next stop as South P
                    inBuses[j].setToNextStop(REST_TIME); // set time to next stop as rest time
                    inBuses[j].setTimeToRest(REST_TIME); // set rest time

                    System.out.println("In Route Bus " + (j + 1) + " arrives at " + busStopNames[stop] +
                            ". Drops of " + numDroppedOf + ". Starting " + REST_TIME + " minutes of rest. ");
                }
                else if (inBuses[j].getToNextStop() == 0) { // if bus has reached destination
                    int numDroppedOf = inBuses[j].getBusPassengers().unload(stop); // unload passengers

                    if (stop + 1 == 5) { // change next stop
                        inBuses[j].setNextStop(0);
                    }
                    else {
                        inBuses[j].setNextStop(stop + 1);
                    }
                    inBuses[j].setToNextStop(TIME_INBETWEEN_STOPS); // set time to next stop

                    int newPassengers = 0;
                    for (int b = 0; b < busStops[stop].getSize(); b++) { // go through bus stop
                        // and add those passengers to the bus that can fit on the bus
                        Passenger current = busStops[stop].get(b); // select current passenger
                        if ((current.getDestination() < 5) &&
                                Bus.getCapacity() >= current.getSize() + inBuses[j].getBusPassengers().size()) {
                            try {
                                busStops[stop].dequeueAt(b);
                            }
                            catch (Exception ex) {
                                System.out.println("Cannot remove from empty queue");
                            }
                            newPassengers += current.getSize();
                            inBuses[j].getBusPassengers().enqueue(current); // add passenger to bus
                            groupsServed++; // increment groups served
                            totalTimeWaited += i - current.getTimeArrived(); // add to total time waited
                        }
                    }

                    System.out.println("In Route Bus " + (j + 1) + " arrives at " + busStopNames[stop] +
                            ". Drops of " + numDroppedOf + ". Picks up " + newPassengers + ".");
                }
                else if (inBuses[j].getTimeToRest() <= 0) { // if bus is on the move
                    System.out.println("In Route Bus " + (j + 1) + " moving toward " +
                            busStopNames[stop] + ". Arrive in " + inBuses[j].getToNextStop() + " minutes.");
                }
                else if (inBuses[j].getTimeToRest() > 0) { // if bus is resting
                    inBuses[j].setTimeToRest(inBuses[j].getTimeToRest() - 1);
                    System.out.println("In Route Bus " + (j + 1) + " is resting at South P for " +
                            inBuses[j].getTimeToRest() + " minutes.");
                }
            }

            // out route buses
            // same thing as in route buses, but now for out route buses
            for (int j = 0; j < outBuses.length; j++) {
                outBuses[j].setToNextStop(outBuses[j].getToNextStop() - 1);
                int stop = outBuses[j].getNextStop();
                if (outBuses[j].getToNextStop() == 0 && stop == 8) {
                    int numDroppedOf = 0;
                    for (int x = 0; x < outBuses[j].getBusPassengers().getSize(); x++) {
                        try {
                            Passenger temp = outBuses[j].getBusPassengers().dequeue();
                            numDroppedOf += temp.getSize();
                            groupsServed++;
                        }
                        catch (Exception ex) {}
                    }
                    outBuses[j].setNextStop(4);
                    outBuses[j].setToNextStop(REST_TIME);
                    outBuses[j].setTimeToRest(REST_TIME);

                    System.out.println("Out Route Bus " + (j + 1) + " arrives at " + busStopNames[stop] +
                            ". Drops of " + numDroppedOf + ". Starting " + REST_TIME + " minutes of rest. ");
                }
                else if (outBuses[j].getToNextStop() == 0) {
                    int numDroppedOf = outBuses[j].getBusPassengers().unload(stop);

                    if (stop + 1 == 9) {
                        outBuses[j].setNextStop(4);
                    }
                    else {
                        outBuses[j].setNextStop(stop + 1);
                    }
                    outBuses[j].setToNextStop(TIME_INBETWEEN_STOPS);

                    int newPassengers = 0;
                    for (int b = 0; b < busStops[stop].getSize(); b++) {
                        Passenger current = busStops[stop].get(b);
                        if (((current.getDestination() > 4 && current.getDestination() < 9))
                                && Bus.getCapacity() >= current.getSize() + outBuses[j].getBusPassengers().size()) {
                            try {
                                busStops[stop].dequeueAt(b);
                            }
                            catch (Exception ex) {
                                System.out.println("Cannot remove from empty queue");
                            }
                            newPassengers += current.getSize();
                            outBuses[j].getBusPassengers().enqueue(current);
                            groupsServed++;
                            totalTimeWaited += i - current.getTimeArrived();
                        }
                    }

                    System.out.println("Out Route Bus " + (j + 1) + " arrives at " + busStopNames[stop] +
                            ". Drops of " + numDroppedOf + ". Picks up " + newPassengers + ".");
                }
                else if (outBuses[j].getTimeToRest() <= 0) {
                    System.out.println("Out Route Bus " + (j + 1) + " moving toward " +
                            busStopNames[stop] + ". Arrive in " + outBuses[j].getToNextStop() + " minutes.");
                }
                else if (outBuses[j].getTimeToRest() > 0) {
                    outBuses[j].setTimeToRest(outBuses[j].getTimeToRest() - 1);
                    System.out.println("Out Route Bus " + (j + 1) + " is resting at South P for " +
                            outBuses[j].getTimeToRest() + " minutes.");
                }
            }

            for (int b = 0; b < busStops.length; b++) { // create table to output the passengers
                // waiting at each bus stop
                System.out.print(b + "\t(" + busStopNames[b] + "): ");
                for (int j = 0; j < busStops[b].getSize(); j++) {
                    Passenger current = busStops[b].get(j);
                    System.out.print("[" + current.getSize() + ", " + current.getDestination() + " ("
                    + busStopNames[current.getDestination()] + "), " + current.getTimeArrived() + "] ");
                }
                System.out.println();
            }
        }

        System.out.println("\n-------END SIMULATION-------\n");
        double avgWaitTime = 0;
        if (groupsServed > 0) { // prevent division by 0 if no groups served
            avgWaitTime = totalTimeWaited / groupsServed;
        }
        double[] data = {groupsServed, avgWaitTime}; // return data
        return data;
    }

    /**
     * Returns a random integer in the given range.
     *
     * <dt><b>Precondition:</b><db>
     *      The <code>min</code> is less than or equal to the <code>max</code> value.
     *
     * @param min
     *      The minimum value the output can be
     *
     * @param max
     *      The maximum value the output can be
     *
     * @return
     *      Returns a random integer in the given range as an int value.
     */
    private static int randInt(int min, int max) {
        return (int)(min + Math.random() * (max - min + 1));
    }

    /**
     * Returns true if a passenger has arrived at a bus stop, false otherwise.
     *
     * <dt><b>Precondition:</b><db>
     *      The <code>arrivalProb</code> is in between 0 and 1.
     *
     * @param arrivalProb
     *      The probability of a passenger arriving at a bus stop.
     *
     * @return
     *      Returns true if a passenger has arrived at a bus stop, false otherwise, based
     *      on <code>arrivalProb</code>.
     */
    private static boolean probOfArrival(double arrivalProb) {
        if (Math.random() < arrivalProb) {
            return true;
        }
        return false;
    }
}
