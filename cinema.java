import java.util.*;
public class cinema{
    private String title;
    private int seatavilable;
    private final int capacity;
    private static int totalbooked=0;

    public cinema(String title,int capacity){
        this.title=title;
        this.capacity=capacity;
        this.seatavilable=capacity;
        
    }
    public cinema(String title){
        this( title,100);
    }
    public boolean  book(int n){
        if (n<= seatavilable){
            seatavilable-=n;
            totalbooked+=n;
            return true;
        }else{
            return false;

        }}
    public void  cancle(int n){
   
    if (n > 0 && n <= capacity - seatavilable) {
        seatavilable += n;
        totalbooked -= n;

        System.out.println("Seats cancelled successfully.");
    } 
    else {
        System.out.println("Cannot cancel more seats than booked.");
    }
}
    public int getseatavailable(){
            return seatavilable;
        }
    public static int gettotalbooked(){
            return totalbooked;
        }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);        
        cinema show=new cinema("spider man",150);

        while (true) {

            System.out.println("\n--- Cinema Show ---");
            System.out.println("1. Book Seats");
            System.out.println("2. Cancel Seats");
            System.out.println("3. Show Available Seats");
            System.out.println("4. Show Total Booked");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter seats to book: ");
                int n = sc.nextInt();

                boolean result = show.book(n);

                System.out.println("Booking: " + result);
                System.out.println("Available seats: "
                        + show.getseatavailable());
                break;

                case 2:
                    System.out.print("Enter seats to cancel: ");
                    int cancelSeats = sc.nextInt();

                    show.cancle(cancelSeats);
                    break;
                case 3:
                      System.out.println("Available seats: "
                        + show.getseatavailable());
                        break;
                case 4:
                     System.out.println("Total booked seats: "
                        + cinema.gettotalbooked());
                        break;
                default:
                    System.out.println("invalid choise");
                    return;
            }
        System.out.println("Total booked: " +show.gettotalbooked());
    }
        
    }}