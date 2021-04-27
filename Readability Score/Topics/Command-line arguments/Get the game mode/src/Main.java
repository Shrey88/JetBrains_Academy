class Problem {
    public static void main(String[] args) {
        boolean isMode = false;
        if (args.length > 2) {
            for (String string : args) {
                if ("mode".equals(string)) {
                    isMode = true;
                    continue;
                }
                if (isMode) {
                    if (!string.matches("value.") && !string.matches("mode.") &&
                            !string.matches("parameter.")) {
                        System.out.println(string);
                        break;
                    } else {
                        isMode = false;
                    }
                }
            }
        }
        if (!isMode) {
            System.out.println("default");
        }
    }
}