import java.util.*;

class Room {
    private String roomNumber;
    private String roomType;
    private double pricePerNight;
    private boolean isAvailable;
    private int maxOccupancy;

    public Room(String roomNumber, String roomType, double pricePerNight, boolean isAvailable, int maxOccupancy) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.isAvailable = isAvailable;
        this.maxOccupancy = maxOccupancy;
    }
    public String getRoomNumber() { return roomNumber; }
    public String getRoomType() { return roomType; }
    public double getPricePerNight() { return pricePerNight; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public int getMaxOccupancy() { return maxOccupancy; }
}

class Guest {
    private String guestId;
    private String guestName;
    private String phoneNumber;
    private String email;
    private ArrayList<String> bookingHistory;

    public Guest(String guestId, String guestName, String phoneNumber, String email) {
        this.guestId = guestId;
        this.guestName = guestName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bookingHistory = new ArrayList<>();
    }
    public String getGuestId() { return guestId; }
    public String getGuestName() { return guestName; }
    public void addBooking(String bookingId) { bookingHistory.add(bookingId); }
}

class Booking {
    private String bookingId;
    private Guest guest;
    private Room room;
    private String checkInDate;
    private String checkOutDate;
    private double totalAmount;
    private static int totalBookings = 0;
    private static double hotelRevenue = 0;
    private static String hotelName = "Grand Palace";

    public Booking(String bookingId, Guest guest, Room room, String checkInDate, String checkOutDate, double totalAmount) {
        this.bookingId = bookingId;
        this.guest = guest;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalAmount = totalAmount;
        totalBookings++;
        hotelRevenue += totalAmount;
        guest.addBooking(bookingId);
    }
    public String getBookingId() { return bookingId; }
    public Room getRoom() { return room; }
    public static double getTotalRevenue() { return hotelRevenue; }
    public static int getTotalBookings() { return totalBookings; }
    public static String getHotelName() { return hotelName; }
}

class HotelReservationSystem {
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Booking> bookings = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void initializeRooms() {
        rooms.add(new Room("101", "Single", 1500, true, 1));
        rooms.add(new Room("102", "Double", 2500, true, 2));
        rooms.add(new Room("103", "Suite", 5000, true, 4));
        rooms.add(new Room("104", "Double", 2500, true, 2));
        rooms.add(new Room("105", "Single", 1500, true, 1));
    }

    public static Room checkAvailability(String type) {
        for (Room room : rooms) {
            if (room.getRoomType().equalsIgnoreCase(type) && room.isAvailable()) {
                return room;
            }
        }
        return null;
    }

    public static void makeReservation() {
        System.out.print("Enter Guest ID: ");
        String guestId = sc.next();
        System.out.print("Enter Name: ");
        String guestName = sc.next();
        System.out.print("Enter Phone: ");
        String phone = sc.next();
        System.out.print("Enter Email: ");
        String email = sc.next();
        Guest guest = new Guest(guestId, guestName, phone, email);

        System.out.print("Enter Room Type (Single/Double/Suite): ");
        String roomType = sc.next();
        Room room = checkAvailability(roomType);
        if (room == null) {
            System.out.println("No available rooms of this type!");
            return;
        }

        System.out.print("Enter Check-in Date: ");
        String checkIn = sc.next();
        System.out.print("Enter Check-out Date: ");
        String checkOut = sc.next();
        System.out.print("Enter Number of Nights: ");
        int nights = sc.nextInt();

        double totalAmount = room.getPricePerNight() * nights;
        String bookingId = "B" + (bookings.size() + 1);
        Booking booking = new Booking(bookingId, guest, room, checkIn, checkOut, totalAmount);
        bookings.add(booking);
        room.setAvailable(false);

        System.out.println("Booking Successful! ID: " + bookingId + " | Total: Rs." + totalAmount);
    }

    public static void cancelReservation() {
        System.out.print("Enter Booking ID to Cancel: ");
        String bookingId = sc.next();
        Booking found = null;
        for (Booking b : bookings) {
            if (b.getBookingId().equalsIgnoreCase(bookingId)) {
                found = b;
                break;
            }
        }
        if (found == null) {
            System.out.println("Booking not found!");
            return;
        }
        found.getRoom().setAvailable(true);
        bookings.remove(found);
        System.out.println("Booking " + bookingId + " cancelled successfully!");
    }

    public static void displayReport() {
        System.out.println("Hotel: " + Booking.getHotelName());
        System.out.println("Total Bookings: " + Booking.getTotalBookings());
        System.out.println("Total Revenue: Rs." + Booking.getTotalRevenue());
    }

    public static void main(String[] args) {
        initializeRooms();
        while (true) {
            System.out.println("\n=== Hotel Reservation System ===");
            System.out.println("1. Make Reservation");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. Display Report");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: makeReservation(); break;
                case 2: cancelReservation(); break;
                case 3: displayReport(); break;
                case 4: System.exit(0);
                default: System.out.println("Invalid choice!");
            }
        }
    }
}
