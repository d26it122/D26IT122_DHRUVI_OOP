import java.util.Scanner;
public class vendingmachine{
    enum Coin{
        ONE,TWO,FIVE,TEN
    }
    public static void main(String[] args){
        final int price=15;
        int total=0;
         Scanner sc = new Scanner(System.in);

         System.out.println("snake price" + price);
         System.out.println("insert coin(ONE,TWO,FIVE,TEN)");

          while (total < price) {

            String input = sc.nextLine();

            Coin coin = Coin.valueOf(input.toUpperCase());

            int value = switch (coin) {
                case ONE -> 1;
                case TWO -> 2;
                case FIVE -> 5;
                case TEN -> 10;
            };
            total += value;

            System.out.println("Total so far: " + total);
        }

        System.out.println("Paid. Change: " + (total - price));

        sc.close();
    }

}