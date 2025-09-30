class HotelBooking {
    void bookRoom(String roomType, int nights) {
        double price = nights * 1000; // base price
        System.out.println("Standard Booking: Room=" + roomType + ", Nights=" + nights + ", Price=" + price);
    }

    void bookRoom(String roomType, int nights, double seasonalMultiplier) {
        double price = nights * 1000 * seasonalMultiplier;
        System.out.println("Seasonal Booking: Room=" + roomType + ", Nights=" + nights + ", Price=" + price);
    }

    void bookRoom(String roomType, int nights, double corporateDiscount, boolean meal) {
        double base = nights * 1000;
        double discount = base * corporateDiscount;
        double price = base - discount + (meal ? 500 : 0);
        System.out.println("Corporate Booking: Room=" + roomType + ", Nights=" + nights + ", Price=" + price);
    }

    void bookRoom(String roomType, int nights, int guestCount, double decorationFee, double cateringFee) {
        double price = nights * 2000 + decorationFee + cateringFee * guestCount;
        System.out.println("Wedding Package: Room=" + roomType + ", Guests=" + guestCount + ", Price=" + price);
    }
}

public class Problem1_HotelBooking {
    public static void main(String[] args) {
        HotelBooking hb = new HotelBooking();
        hb.bookRoom("Deluxe", 3);
        hb.bookRoom("Deluxe", 3, 1.2);
        hb.bookRoom("Suite", 5, 0.1, true);
        hb.bookRoom("Hall", 1, 100, 5000, 200);
    }
}