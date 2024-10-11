import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

public class UpdatedTrainLine {
    private String name;
    private TrainStation head;
    private TrainStation tail;
    private int numberOfStations;

    public UpdatedTrainLine(String name, TrainStation head) {
        this.name = name;
        this.head = head;
        this.numberOfStations = 0;
        if (this.head != null) {
            this.numberOfStations = 1;
        }
        this.tail = head;
    }

    public UpdatedTrainLine(String name) {
        this(name, null);
    }

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

    public int getNumberOfStations() {
        return numberOfStations;
    }

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

    public boolean isEmpty() {
        return head == null;
    }

    public void print(PrintStream out) {
        if (head == null) {
            out.println("Empty train line");
            return;
        }

        out.println("         1         2         3         4         5         6         7         8");
        out.println("12345678901234567890123456789012345678901234567890123456789012345678901234567890\n");

        TrainStation current = head;

        // Print forward stations
        while (current != null) {
            out.print(current.getName());
            if (current.getNext() != null) {
                out.print(" --> ");
            } else {
                out.print(" --+");
            }
            current = current.getNext();
        }
        out.println();

        // Print visual connectors
        current = head;
        while (current != null) {
            if (current.getNext() != null) {
                out.print(" ".repeat(current.getName().length() + 5)); // Adjust spacing
                out.print("|");
            } else {
                out.print(" ".repeat(current.getName().length() + 3));
                out.print("null");
            }
            current = current.getNext();
        }
        out.println();

        // Print backward stations
        current = tail;
        while (current != null) {
            out.print(current.getName());
            if (current.getPrevious() != null) {
                out.print(" <-- ");
            } else {
                out.print(" <-- null");
            }
            current = current.getPrevious();
        }
        out.println();
    }

    @Override
    public String toString() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        print(ps);
        return baos.toString();
    }

    public static void main(String[] args) {
        UpdatedTrainLine redLineSB = new UpdatedTrainLine("Red Line SB");
        String[] stations = {
            "Howard", "Jarvis", "Morse", "Loyola", "Granville", "Thorndale",
            "Bryn Mawr", "Argyle", "Wilson", "Sheridan", "Addison",
            "Belmont", "Fullerton", "North/Clybourn", "Clark/Division",
            "Chicago", "Grand", "Lake", "Monroe", "Jackson", "Harrison", "Roosevelt",
            "Cermak-Chinatown", "Sox-35th", "47th", "Garfield", "63rd", "69th",
            "79th", "87th", "95th/Dan Ryan"
        };

        for (String station : stations) {
            redLineSB.add(station);
        }

        redLineSB.print(System.out);
    }
}

class TrainStation {
    private String name;
    private TrainStation next;
    private TrainStation previous;

    public TrainStation(String name) {
        this.name = name;
        this.next = null;
        this.previous = null;
    }

    public String getName() {
        return name;
    }

    public TrainStation getNext() {
        return next;
    }

    public void setNext(TrainStation next) {
        this.next = next;
    }

    public TrainStation getPrevious() {
        return previous;
    }

    public void setPrevious(TrainStation previous) {
        this.previous = previous;
    }
}
