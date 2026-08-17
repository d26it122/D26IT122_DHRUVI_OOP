import java.util.*;
public class point{
    private int x,y;

    public point(int x,int y){
        this.x=x;
        this.y=y;
    }
    public String toString(){
        return(x+","+y);

    }
    public boolean equals(Object objct){
        point p = (point) objct;
        return x == p.x && y == p.y; 
    }
    public int hashCode() {
        return Objects.hash(x, y);
    }
}