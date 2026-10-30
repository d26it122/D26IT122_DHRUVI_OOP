import java.util.Scanner;
public class toolboth{
    record vehical(String type,String number){}
        public static void main(String[] args){
            Scanner sc=new Scanner(System.in);
            int tolltotal=0;
            int carcount=0;
            int bikecount=0;
            int truckcount=0;

            System.out.println("Enter the vehical type and type 'done'when you stop :");
            while (true){
                System.out.println("Enter the vehical num or (done)");
                String number = sc.nextLine();

                if (number.equalsIgnoreCase("done")) {
                break;
            }
                System.out.println("enter the vehical type: ");
                String type=sc.nextLine().toLowerCase();

                vehical Vehical=new vehical(type, number);


            int toll = switch (Vehical.type()) {
                case "bike" -> 20;
                case "car" -> 50;
                case "truck" -> 150;
                default -> 0;
            };
             tolltotal += toll;

            switch (Vehical.type()) {
                case "bike" -> bikecount++;
                case "car" -> carcount++;
                case "truck" -> truckcount++;
            }
             String mostFrequent;

        if (carcount >= bikecount && carcount >= truckcount) {
            mostFrequent = "car";
        } else if (bikecount >= carcount && bikecount >= truckcount) {
            mostFrequent = "bike";
        } else {
            mostFrequent = "truck";
        }

        System.out.println("Total toll: " + tolltotal);
        System.out.println("Most frequent: " + mostFrequent);
        }
        sc.close();

            }
        }
        
    