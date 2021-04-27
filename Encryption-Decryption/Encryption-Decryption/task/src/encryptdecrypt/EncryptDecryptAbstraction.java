package encryptdecrypt;

import javax.crypto.spec.PSource;
import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

abstract class EncryptDecryptAbstraction {

    final int key;
    private Source source;
    private Destination destination;
    private final String algorithm;
    private final String operation;
    private final EncodeDecodeFormat encodeDecodeFormat;

    EncryptDecryptAbstraction(int key, String algorithm, String operation,String data, String inFileName, String outFileName) {
        this.key = key;
        this.algorithm = algorithm;
        this.operation = operation;

        try {
            this.source = Source.getInstance(data, inFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        this.destination = Destination.getInstance(outFileName);

        if ("enc".equals(operation)) {
            encodeDecodeFormat = new EncryptFormat();
        } else {
            encodeDecodeFormat = new DecryptFormat();
        }
    }

    void execute() {
        if ("shift".equals(algorithm)) {
            shiftAlgorithm(source, destination, encodeDecodeFormat);
        } else if ("unicode".equals(algorithm)) {
            unicodeAlgorithm(source, destination, encodeDecodeFormat);
        }
    }

    abstract void shiftAlgorithm(Source src, Destination des, EncodeDecodeFormat encodeDecodeFormat);
    abstract void unicodeAlgorithm(Source src, Destination des, EncodeDecodeFormat encodeDecodeFormat);
}

class EncryptFactory extends EncryptDecryptAbstraction {

    public EncryptFactory(int key, String algorithm, String operation, String data, String inFileName, String outFileName) {
        super(key, algorithm, operation, data, inFileName, outFileName);
    }

    @Override
    void shiftAlgorithm(Source src, Destination des, EncodeDecodeFormat encodeDecodeFormat) {
        List<String> data = src.getData();
        List<String> output = new ArrayList<>();

        for(String s : data) {
            output.add(encodeDecodeFormat.shiftFormat(key, s));
        }

        des.setOutPutList(output);
        des.printData();

    }

    @Override
    void unicodeAlgorithm(Source src, Destination des, EncodeDecodeFormat encodeDecodeFormat) {
        List<String> data = src.getData();
        List<String> output = new ArrayList<>();

        for(String s : data) {
            output.add(encodeDecodeFormat.unicodeFormat(key, s));
        }

        des.setOutPutList(output);
        des.printData();
    }
}

class DecryptFactory extends EncryptDecryptAbstraction {

    public DecryptFactory(int key, String algorithm, String operation, String data, String inFileName, String outFileName) {
        super(key, algorithm, operation, data, inFileName, outFileName);
    }

    @Override
    void shiftAlgorithm(Source src, Destination des, EncodeDecodeFormat encodeDecodeFormat) {
        List<String> data = src.getData();
        List<String> output = new ArrayList<>();

        for(String s : data) {
            output.add(encodeDecodeFormat.shiftFormat(key, s));
        }

        des.setOutPutList(output);
        des.printData();
    }

    @Override
    void unicodeAlgorithm(Source src, Destination des, EncodeDecodeFormat encodeDecodeFormat) {
        List<String> data = src.getData();
        List<String> output = new ArrayList<>();

        for(String s : data) {
            output.add(encodeDecodeFormat.unicodeFormat(key, s));
        }

        des.setOutPutList(output);
        des.printData();
    }
}


abstract class Source {
    List<String> inputList = new ArrayList<>();
    abstract List<String> getData();
    static Source getInstance(String data, String inFileName) throws FileNotFoundException {

        if (data != null) {
            return new Data(data);
        }

        try {
            return new InFile(inFileName);
        } catch(FileNotFoundException e) {
            System.out.println("Source::getInstance : " + e.getMessage());
        }

        return null;
    }
}

class Data extends Source {

    public Data(String data) {
        inputList.add(data);
    }

    @Override
    List<String> getData() {
        return inputList;
    }
}

class InFile extends Source {
    private String inFileName;
    private BufferedReader bufferedReader;

    public InFile (String inFileName) throws FileNotFoundException {
        this.inFileName = inFileName;
        try {
            File inFile = new File(inFileName);
            if (inFile.exists()) {
                bufferedReader = new BufferedReader(new FileReader(inFile));
            }
        } catch(FileNotFoundException e) {
            System.out.println("File : " + inFileName + " not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    List<String> getData() {
        try {
            return readData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<String> readData() throws IOException {
        String data;

        try {
            data = bufferedReader.readLine();

            while (data != null) {
                inputList.add(data);
                data = bufferedReader.readLine();
            }

            bufferedReader.close();
        } catch(IOException e) {
            System.out.println("Read Data : " + e.getMessage());
        }
        return inputList;
    }

}

abstract class Destination {
    List<String> outputList;

    void setOutPutList(List<String> outputList) {
        this.outputList = outputList;
    }

    static Destination getInstance(String outFileName) {
        if (outFileName != null) {
            try {
                return new OutFile(outFileName);
            } catch (FileNotFoundException e) {
                System.out.println("Destination::getInstance : " + e.getMessage());
            }
        }

        return new Console();
    }

    abstract void printData();
}

class Console extends Destination {

    @Override
    void printData() {
        writeData(outputList);
    }

    private void writeData(List<String> outputList) {
        for(String s : outputList) {
            System.out.println(s);
        }
    }
}

class OutFile extends Destination {
    private String outFileName;
    private BufferedWriter bufferedWriter;

    public OutFile (String outFileName) throws FileNotFoundException {
        this.outFileName = outFileName;
        try {
            File outFile = new File(outFileName);
            if (outFile.exists()) {
                bufferedWriter = new BufferedWriter(new FileWriter(outFile));
            }
        } catch(FileNotFoundException e) {
            System.out.println("File : " + outFileName + " not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    void printData() {
        try {
            writeData(outputList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeData(List<String> outputList) throws IOException {
        try {
            for(String s : outputList) {
                bufferedWriter.write(s);
                bufferedWriter.write("\n");
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

abstract class EncodeDecodeFormat {
    abstract String unicodeFormat(int key, String data);
    abstract String shiftFormat(int key, String data);
}

class EncryptFormat extends EncodeDecodeFormat {

    @Override
    String unicodeFormat(int key, String data) {
        return unicodeEncryption(key, data);
    }

    @Override
    String shiftFormat(int key, String data) {
        return shiftEncryption(key, data);
    }

    private String unicodeEncryption(int key, String normalText) {
        int asciiValue;
        StringBuilder stringBuilder = new StringBuilder();
        for (Character ch : normalText.toCharArray()) {
            asciiValue = (int) ch;
            if (asciiValue >= 32 && asciiValue <= 47) {
                for (int i = 0; i < key; i++) {
                    if (asciiValue == 47) {
                        asciiValue = 32;
                    } else {
                        ++asciiValue;
                    }
                }
            } else if (asciiValue >= 48 && asciiValue <= 64) {
                for (int i = 0; i < key; i++) {
                    if (asciiValue == 64) {
                        asciiValue = 48;
                    } else {
                        ++asciiValue;
                    }
                }
            } else if (asciiValue >= 65 && asciiValue <= 96){
                for (int i = 0; i < key; i++) {
                    if (asciiValue == 96) {
                        asciiValue = 65;
                    } else {
                        ++asciiValue;
                    }
                }
            } else if (asciiValue >= 97 && asciiValue <= 126) {
                for (int i = 0; i < key; i++) {
                    if (asciiValue == 126) {
                        asciiValue = 97;
                    } else {
                        ++asciiValue;
                    }
                }
            }

            stringBuilder.append((char)asciiValue);
        }

        return stringBuilder.toString();
    }

    private String shiftEncryption(int key, String normalText) {
        int asciiValue;
        StringBuilder stringBuilder = new StringBuilder();
        for (Character ch : normalText.toCharArray()) {
            asciiValue = (int) ch;
            if (asciiValue >= 65 && asciiValue <= 90){
                for (int i = 0; i < key; i++) {
                    if (asciiValue == 90) {
                        asciiValue = 65;
                    } else {
                        ++asciiValue;
                    }
                }
            } else if (asciiValue >= 97 && asciiValue <= 122) {
                for (int i = 0; i < key; i++) {
                    if (asciiValue == 122) {
                        asciiValue = 97;
                    } else {
                        ++asciiValue;
                    }
                }
            }

            stringBuilder.append((char)asciiValue);
        }

        return stringBuilder.toString();
    }
}

class DecryptFormat extends EncodeDecodeFormat {

    @Override
    String unicodeFormat(int key, String data) {
        return unicodeDecryption(key, data);
    }

    @Override
    String shiftFormat(int key, String data) {
        return shiftDecryption(key, data);
    }

    private String unicodeDecryption(int key, String cipherText) {
        int asciiValue;
        StringBuilder stringBuilder = new StringBuilder();

        for (Character ch : cipherText.toCharArray()) {
            asciiValue = (int) ch;
            if (asciiValue >= 32 && asciiValue <= 47) {
                for (int i = 0; i < key; i++) {
                    if (asciiValue == 32) {
                        asciiValue = 47;
                    } else {
                        --asciiValue;
                    }
                }
            } else if (asciiValue >= 48 && asciiValue <= 64) {
                for (int i = 0; i < key; i++) {
                    if (asciiValue == 48) {
                        asciiValue = 64;
                    } else {
                        --asciiValue;
                    }
                }
            } else if (asciiValue >= 65 && asciiValue <= 96) {
                for (int i = 0; i < key; i++) {
                    if (asciiValue == 65) {
                        asciiValue = 96;
                    } else {
                        --asciiValue;
                    }
                }
            } else if (asciiValue >= 97 && asciiValue <= 126) {
                for (int i = 0; i < key; i++) {
                    if (asciiValue == 97) {
                        asciiValue = 126;
                    } else {
                        --asciiValue;
                    }
                }
            }

            stringBuilder.append((char) asciiValue);
        }

        return stringBuilder.toString();
    }

    private String shiftDecryption(int key, String cipherText) {
        int asciiValue;
        StringBuilder stringBuilder = new StringBuilder();

        for (Character ch : cipherText.toCharArray()) {
            asciiValue = (int) ch;
            if (asciiValue >= 65 && asciiValue <= 90) {
                for (int i = 0; i < key; i++) {
                    if (asciiValue == 65) {
                        asciiValue = 90;
                    } else {
                        --asciiValue;
                    }
                }
            } else if (asciiValue >= 97 && asciiValue <= 122) {
                for (int i = 0; i < key; i++) {
                    if (asciiValue == 97) {
                        asciiValue = 122;
                    } else {
                        --asciiValue;
                    }
                }
            }

            stringBuilder.append((char) asciiValue);
        }

        return stringBuilder.toString();
    }
}



