import java.util.Scanner;

class Time {

    int hour;
    int minute;
    int second;

    public static Time noon() {
        // write your code here
        return of(12, 0, 0);
    }

    public static Time midnight() {
        // write your code here
        return of(0, 0, 0);
    }

    public static Time ofSeconds(long seconds) {
        // write your code here

        long sec = seconds % (24 * 3600);
        int hour = (int) (sec / 3600);

        sec %= 3600;
        int minutes = (int) (sec / 60);

        sec %= 60;

        return of(hour,  minutes, (int) sec);
    }

    public static Time of(int hour, int minute, int second) {
        // write your code here

        if (hour >= 0 && hour <= 23 &&
                minute >= 0 && minute <= 59 &&
                second >= 0 && second <= 59) {
            Time newTime = new Time();
            newTime.hour = hour;
            newTime.minute = minute;
            newTime.second = second;
            return newTime;
        }
        return null;
    }
}

/* Do not change code below */
public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        final String type = scanner.next();
        Time time = null;

        switch (type) {
            case "noon":
                time = Time.noon();
                break;
            case "midnight":
                time = Time.midnight();
                break;
            case "hms":
                int h = scanner.nextInt();
                int m = scanner.nextInt();
                int s = scanner.nextInt();
                time = Time.of(h, m, s);
                break;
            case "seconds":
                time = Time.ofSeconds(scanner.nextInt());
                break;
            default:
                time = null;
                break;
        }

        if (time == null) {
            System.out.println(time);
        } else {
            System.out.println(String.format("%s %s %s", time.hour, time.minute, time.second));
        }
    }
}