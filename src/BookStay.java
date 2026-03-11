import java.util.*;

class BookingRequest {
    String guestName;
    String roomType;

    BookingRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class InventoryService {
    private Map<String, Integer> inventory = new HashMap<>();

    InventoryService() {
        inventory.put("Standard", 3);
        inventory.put("Deluxe", 2);
        inventory.put("Suite", 1);
    }

    boolean isAvailable(String roomType) {
        return inventory.getOrDefault(roomType, 0) > 0;
    }

    void decrement(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }

    void showInventory() {
        System.out.println("Current Inventory: " + inventory);
    }
}

public class BookStay {

    private Queue<BookingRequest> requestQueue = new LinkedList<>();
    private Set<String> allocatedRoomIds = new HashSet<>();
    private Map<String, Set<String>> roomTypeToIds = new HashMap<>();
    private InventoryService inventoryService = new InventoryService();
    private int counter = 1;

    private String generateRoomId(String roomType) {
        String id = roomType.substring(0, 3).toUpperCase() + counter++;
        while (allocatedRoomIds.contains(id)) {
            id = roomType.substring(0, 3).toUpperCase() + counter++;
        }
        return id;
    }

    public void addRequest(String guestName, String roomType) {
        requestQueue.offer(new BookingRequest(guestName, roomType));
    }

    public void processBookings() {
        while (!requestQueue.isEmpty()) {
            BookingRequest request = requestQueue.poll();
            String type = request.roomType;

            if (inventoryService.isAvailable(type)) {
                String roomId = generateRoomId(type);
                allocatedRoomIds.add(roomId);

                roomTypeToIds.putIfAbsent(type, new HashSet<>());
                roomTypeToIds.get(type).add(roomId);

                inventoryService.decrement(type);

                System.out.println("Reservation Confirmed for " + request.guestName + " RoomID: " + roomId + " Type: " + type);
            } else {
                System.out.println("Reservation Failed for " + request.guestName + " No " + type + " rooms available");
            }
        }
    }

    public void showAllocations() {
        System.out.println("Allocated Rooms: " + roomTypeToIds);
    }

    public static void main(String[] args) {
        BookStay service = new BookStay();

        service.addRequest("Alice", "Standard");
        service.addRequest("Bob", "Deluxe");
        service.addRequest("Charlie", "Standard");
        service.addRequest("David", "Suite");
        service.addRequest("Eva", "Standard");
        service.addRequest("Frank", "Suite");

        service.processBookings();
        service.showAllocations();
        service.inventoryService.showInventory();
    }
}
