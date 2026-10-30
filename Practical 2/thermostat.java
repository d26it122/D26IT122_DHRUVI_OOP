public class thermostat {

    private String location;
    private int temperature;

    private static final int MIN = 16;
    private static final int MAX = 30;

    private static int activeCount = 0;

    public thermostat(String location, int startTemp) {
        this.location = location;

        if (startTemp >= MIN && startTemp <= MAX) {
            this.temperature = startTemp;
        } else {
            this.temperature = 22;
        }

        activeCount++;
    }

    public thermostat(String location) {
        this(location, 22);
    }

    public void raise() {
        if (temperature < MAX) {
            temperature++;
        } else {
            System.out.println("Already at maximum (30)");
        }
    }

    public void lower() {
        if (temperature > MIN) {
            temperature--;
        } else {
            System.out.println("Already at minimum (16)");
        }
    }

    public int gettemperature() {
        return temperature;
    }

    public static int getactiveCount() {
        return activeCount;
    }

    public static void main(String[] args) {

        thermostat t1 = new thermostat("Room 1", 20);
        thermostat t2 = new thermostat("Room 2");

        for (int i = 0; i < 10; i++) {
            t1.raise();
        }


        for (int i = 0; i < 20; i++) {
            t1.lower();
        }

        System.out.println("Count = " + thermostat.getactiveCount());
    }
}