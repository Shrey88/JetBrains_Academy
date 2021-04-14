public class Main {

    public static void main(String[] args) {
    // write your program here
        int match = 0;
        for (Secret secret : Secret.values()) {
            if (String.valueOf(secret).contains("STAR")) {
                ++match;
            }
        }
        System.out.println(match);
    }
}

/* sample enum for inspiration
   enum Secret {
    STAR, CRASH, START, // ...
}
*/