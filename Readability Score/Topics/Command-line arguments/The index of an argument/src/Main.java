class Problem {
    public static void main(String[] args) {
        int i = 0;
        String matchString = "test";
        for (; i < args.length; i++) {
            if (args[i].matches(matchString)) {
                System.out.println(i);
                break;
            }
        }

        if (i >= args.length) {
            System.out.println("-1");
        }
    }
}