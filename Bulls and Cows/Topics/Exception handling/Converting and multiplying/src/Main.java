class Main {
    private static void convertMultiply(String input) {
        try {
            System.out.println(Integer.parseInt(input) * 10);
        } catch (Exception e) {
            System.out.println("Invalid user input: " + input);
        }
    }

    public static void main(String[] args) {
        // put your code here
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String two = "0";
        String three = "0";
        String four = "0";

        String one = scanner.next();
        if (!"0".equals(one)) {
            convertMultiply(one);
            two = scanner.next();
        }

        if (!"0".equals(two)) {
            convertMultiply(two);
            three = scanner.next();
        }

        if (!"0".equals(three)) {
            convertMultiply(three);
            four = scanner.next();
        }

        if (!"0".equals(four)) {
            convertMultiply(four);
        }
    }
}