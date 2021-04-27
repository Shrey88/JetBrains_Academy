package encryptdecrypt;

import java.io.FileNotFoundException;

public class Main {

    static String operation = "enc";
    static String algorithm = "shift";
    static String data = null;
    static String inFile = null;
    static String outFile = null;
    static int key = 0;

    private static void readCommandArg(String[] args) {
        int i = 0;

        try{
            do{
                switch(args[i]) {
                    case "-mode":
                        if (i + 1 != args.length && "enc".equals(args[i + 1])){
                            operation = args[i + 1];
                            i = i + 2;
                        } else if (i + 1 != args.length && "dec".equals(args[i + 1])){
                            operation = args[i + 1];
                            i = i + 2;
                        } else {
                            ++i;
                        }
                        break;

                    case "-alg":
                        if (i + 1 != args.length && "shift".equals(args[i + 1])){
                            algorithm = args[i + 1];
                            i = i + 2;
                        } else if (i + 1 != args.length && "unicode".equals(args[i + 1])){
                            algorithm = args[i + 1];
                            i = i + 2;
                        } else {
                            ++i;
                        }
                        break;

                    case "-key":
                        if (i + 1 != args.length && args[i + 1].matches("\\d+?")) {
                            key = Integer.parseInt(args[i + 1]);
                            i = i + 2;
                        } else {
                            ++i;
                        }
                        break;

                    case "-data":
                        data = i + 1 != args.length ? args[i + 1] : null;
                        i = i + 2;
                        break;

                    case "-in":
                        inFile = i + 1 != args.length ? args[i + 1] : null;
                        i = i + 2;
                        break;

                    case "-out":
                        outFile = i + 1 != args.length ? args[i + 1] : null;
                        i = i + 2;
                        break;

                    default:
                        break;
                }
            } while (i < args.length);

        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Array out of Bound :: Value of i : " + i);
        }
    }


    public static void main(String[] args) {

        readCommandArg(args);
        EncryptDecryptAbstraction factory;
        switch (operation) {
            case "enc":
                factory = new EncryptFactory(key, algorithm, operation, data, inFile, outFile);
                factory.execute();
                break;
            case "dec":
                factory = new DecryptFactory(key, algorithm, operation, data, inFile, outFile);
                factory.execute();
                break;
            default:
                System.out.println("Invalid Option");
                break;
        }
    }
}