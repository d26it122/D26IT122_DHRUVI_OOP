class SeatBooking {

    int seatsLeft;

    SeatBooking(int seats) {
        seatsLeft = seats;
    }

    // WITHOUT SYNCHRONIZATION
    void bookWithoutSync() {
        if (seatsLeft > 0) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            seatsLeft--;

            System.out.println(Thread.currentThread().getName()
                    + " booked a seat");
        } else {
            System.out.println(Thread.currentThread().getName()
                    + " failed - no seats left");
        }
    }

    // WITH SYNCHRONIZATION
    synchronized void bookWithSync() {
        if (seatsLeft > 0) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            seatsLeft--;

            System.out.println(Thread.currentThread().getName()
                    + " booked a seat");
        } else {
            System.out.println(Thread.currentThread().getName()
                    + " failed - no seats left");
        }
    }
}

public class seatbookingrace {

    public static void main(String[] args) throws Exception {

        // -----------------------------------------
        // WITHOUT SYNCHRONIZATION
        // -----------------------------------------

        System.out.println("===== WITHOUT SYNCHRONIZATION =====");

        SeatBooking booking1 = new SeatBooking(5);

        Thread[] threads1 = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads1[i] = new Thread(
                    booking1::bookWithoutSync,
                    "Thread-" + (i + 1)
            );
            threads1[i].start();
        }

        for (Thread t : threads1) {
            t.join();
        }

        System.out.println("Seats left: " + booking1.seatsLeft);


        // -----------------------------------------
        // WITH SYNCHRONIZATION
        // -----------------------------------------

        System.out.println("\n===== WITH SYNCHRONIZATION =====");

        SeatBooking booking2 = new SeatBooking(5);

        Thread[] threads2 = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads2[i] = new Thread(
                    booking2::bookWithSync,
                    "Thread-" + (i + 1)
            );
            threads2[i].start();
        }

        for (Thread t : threads2) {
            t.join();
        }

        System.out.println("Seats left: " + booking2.seatsLeft);
    }
}