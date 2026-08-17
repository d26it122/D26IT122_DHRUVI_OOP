import java.util.*;
import javax.smartcardio.Card;
public class card {
    private String rank;
    private String suit;

    public card(String rank,String suit){
        this.rank=rank;
        this.suit=suit;

    }
    public String toString(){
        return rank+" of "+suit;
    }
    public boolean equals(Object objct){
        // point p = (point) objct;
        card c = (card) objct;

        return rank.equals(c.rank) && suit.equals(c.suit);
    }
      public int hashCode() {
        return Objects.hash(rank, suit);
    }}
 

