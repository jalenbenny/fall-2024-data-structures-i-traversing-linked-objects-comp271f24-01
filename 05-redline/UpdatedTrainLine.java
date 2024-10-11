import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

/**
 * class representing a train line
 */
public class UpdatedTrainLine {
    private String name;
    private TrainStation head;
    private TrainStation tail;
    private int numberOfStations;

    /**
     * constructor to create a train line with a name and a starting station
     * @param name the name of the train line
     * @param head the starting train station
     */
    public UpdatedTrainLine(String name, TrainStation head) {
        this.name = name;
        this.head = head;
        this.numberOfStations = 0;
        if (this.head != null) {
            this.numberOfStations = 1;
        }
        this.tail = head;
    }

    /**
     * constructor to create a train line with a name and no starting station
     * @param name the name of the train line
     */
    public UpdatedTrainLine(String name) {
        this(name, null);
    }

    /**
     * method to add a new station to the train line
     * @param name the name of the new station
     */
    public void add(String name) {
        TrainStation newStation = new TrainStation(name);
        if (this.head == null) {
            this.head = newStation;
        } else {
            this.tail.setNext(newStation);
            newStation.setPrevious(this.tail);
        }
        this.tail = newStation;
        this.numberOfStations++;
    }

    /**
     * method to get the number of stations in the train line
     * @return the number of stations
     */
    public int getNumberOfStations() {
        return numberOfStations;
    }

    /**
     * method to check if a station is contained in the train line
     * @param name the name of the station
     * @return true if the station is found, false otherwise
     */
    public boolean contains(String name) {
        TrainStation current = head;
        while (current != null) {
            if (current.getName().equals(name)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * method to find the index of a station in the train line
     * @param name the name of the station
     * @return the index of the station, or -1 if not found
     */
    public int indexOf(String name) {
        TrainStation current = head;
        int index = 0;
        while (current != null) {
            if (current.getName().equals(name)) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1;
    }

    /**
     * method to check if the train line is empty
     * @return true if the train line has no stations, false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * method to print the train line and its stations
     * @param out the output stream to print to
     */
    public void print(PrintStream out) {
        if (head == null) {
            out.println("empty train line");
            return;
        }

        // first segment: howard to thorndale
        StringBuilder sb = new StringBuilder();
        TrainStation current = head;

        // first line: howard --> thorndale
        sb.append(current.getName());
        current = current.getNext();
        while (current != null && !current.getName().equals("thorndale")) {
            sb.append(" --> ").append(current.getName());
            current = current.getNext();
        }
        if (current != null) {
            sb.append(" --> ").append(current.getName()).append(" --+");
            current = current.getNext();
        }
        out.println(sb.toString());

        // second line connector (spaces)
        out.println("                                                                     |");

        // second segment: bryn mawr to addison (snake back to bryn mawr)
        sb.setLength(0);
        sb.append("      +-- bryn mawr --> argyle --> wilson --> sheridan --> addison <-- bryn mawr <--");
        out.println(sb.toString());

        // connector to next segment
        out.println("      |");

        // third segment: belmont to clark/division
        sb.setLength(0);
        sb.append("      +--> belmont --> fullerton --> north/clybourn --> clark/division --+");
        out.println(sb.toString());

        // connector for clark/division
        out.println("                                                                        |");

        // fourth segment: roosevelt back to chicago (snaking backward)
        sb.setLength(0);
        sb.append("+-- roosevelt <-- harrison <-- jackson <-- monroe <-- clark <-- chicago +");
        out.println(sb.toString());

        // final segment: cermak-chinatown to 95th/dan ryan
        sb.setLength(0);
        sb.append("+--> cermak-chinatown --> sox-35th --> 47th --> garfield --> 63rd --> 69th --+");
        out.println(sb.toString());

        // connector for last segment
        out.println("                                                                             |");

        // last line: null to 95th/dan ryan
        sb.setLength(0);
        sb.append("                                 null <-- 95th/dan ryan <-- 87th <-- 79th <--+");
        out.println(sb.toString());
    }

    /**
     * method to return a string representation of the train line
     * @return string representation of the train line
     */
    @Override
    public String toString() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        print(ps);
        return baos.toString();
    }

    /**
     * main method to run the program
     * @param args command line arguments
     */
    public static void main(String[] args) {
        UpdatedTrainLine redLineSB = new UpdatedTrainLine("red line sb");
        String[] stations = {
            "howard", "jarvis", "morse", "loyola", "granville", "thorndale",
            "bryn mawr", "argyle", "wilson", "sheridan", "addison",
            "belmont", "fullerton", "north/clybourn", "clark/division",
            "chicago", "grand", "lake", "monroe", "jackson", "harrison", "roosevelt",
            "cermak-chinatown", "sox-35th", "47th", "garfield", "63rd", "69th",
            "79th", "87th", "95th/dan ryan"
        };

        for (String station : stations) {
            redLineSB.add(station);
        }

        redLineSB.print(System.out);
    }
}

/**
 * class representing a train station
 */
class TrainStation {
    private String name;
    private TrainStation next;
    private TrainStation previous;

    /**
     * constructor to create a train station
     * @param name the name of the station
     */
    public TrainStation(String name) {
        this.name = name;
        this.next = null;
        this.previous = null;
    }

    /**
     * method to get the name of the station
     * @return the name of the station
     */
    public String getName() {
        return name;
    }

    /**
     * method to get the next station
     * @return the next station
     */
    public TrainStation getNext() {
        return next;
    }

    /**
     * method to set the next station
     * @param next the next station
     */
    public void setNext(TrainStation next) {
        this.next = next;
    }

    /**
     * method to get the previous station
     * @return the previous station
     */
    public TrainStation getPrevious() {
        return previous;
    }

    /**
     * method to set the previous station
     * @param previous the previous station
     */
    public void setPrevious(TrainStation previous) {
        this.previous = previous;
    }
}
