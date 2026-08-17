public class driver {
    public static void main(String[] args) {
        point[] points = {
            new point(2, 3),
            new point(5, 6),
            new point(2, 3),
            new point(8, 1),
            new point(5, 6)
        };
        int distinct = 0;
        for (int i = 0; i < points.length; i++) {
            boolean found = false;
            for (int j = 0; j < i; j++) {
                if (points[i].equals(points[j])) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                distinct++;
            }}
        System.out.println("Distinct: " + distinct);
    }
}