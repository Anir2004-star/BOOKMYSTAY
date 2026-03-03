package BookMyStay;

import java.util.HashMap;
import java.util.Map;

abstract class Room {
    private String roomType;
    private int numberOfBeds;
    private double sizeInSqFt;
    private double pricePerNight;

    public Room(String roomType, int numberOfBeds, double sizeInSqFt, double pricePerNight) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.sizeInSqFt = sizeInSqFt;
        this.pricePerNight = pricePerNight;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public double getSizeInSqFt() {
        return sizeInSqFt;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }
}

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 200, 1500);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 350, 2500);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 600, 5000);
    }
}

class RoomInventory {
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int newCount) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, newCount);
        }
    }

    public void displayInventory() {
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

public class UseCasebookMyStay {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        inventory.displayInventory();
        inventory.updateAvailability("Single Room", 4);
        inventory.displayInventory();
    }
}abstract class Room {

    // Encapsulated common attributes
    private String roomType;
    private int numberOfBeds;
    private double sizeInSqFt;
    private double pricePerNight;

    // Constructor
    public Room(String roomType, int numberOfBeds, double sizeInSqFt, double pricePerNight) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.sizeInSqFt = sizeInSqFt;
        this.pricePerNight = pricePerNight;
    }

    // Getters (Encapsulation)
    public String getRoomType() {
        return roomType;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public double getSizeInSqFt() {
        return sizeInSqFt;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    // Common behavior
    public void displayRoomDetails() {
        System.out.println("Room Type      : " + roomType);
        System.out.println("Beds           : " + numberOfBeds);
        System.out.println("Size (Sq Ft)   : " + sizeInSqFt);
        System.out.println("Price/Night    : ₹" + pricePerNight);
    }
}

// Concrete Room Types

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 200, 1500);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 350, 2500);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 600, 5000);
    }
}

public class UseCaseBookMyStay {

    /**
     * Application Entry Point
     */
    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("      Book My Stay - Version 2.1     ");
        System.out.println("======================================\n");

        // Polymorphism: Referencing objects using abstract type
        Room single = new SingleRoom();
        Room doubleroom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static Availability Variables (Intentional Limitation)
        int singleAvailability = 5;
        int doubleAvailability = 3;
        int suiteAvailability = 2;

        // Display Details

        System.out.println("---- Single Room Details ----");
        single.displayRoomDetails();
        System.out.println("Available Rooms : " + singleAvailability + "\n");

        System.out.println("---- Double Room Details ----");
        doubleroom.displayRoomDetails();
        System.out.println("Available Rooms : " + doubleAvailability + "\n");

        System.out.println("---- Suite Room Details ----");
        suite.displayRoomDetails();
        System.out.println("Available Rooms : " + suiteAvailability + "\n");

        System.out.println("Application terminated successfully.");
    }
}