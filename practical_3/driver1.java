import javax.smartcardio.Card;

public class driver1 {

    public static void main(String[] args) {

        card[] cards = {
            new card("Ace", "Spades"),
            new card("King", "Hearts"),
            new card("Ace", "Spades"),
            new card("Queen", "Hearts")
        };

        card[] addedcards = new card[cards.length];

        int count = 0;

        for (int i = 0; i < cards.length; i++) {

            boolean found = false;

            for (int j = 0; j < count; j++) {

                if (cards[i].equals(addedcards[j])) {
                    found = true;
                    break;
                }
            }

            if (found) {
                System.out.println("Duplicate found: " + cards[i]);
                break;
            }

            addedcards[count] = cards[i];
            count++;
        }
    }
}
    

