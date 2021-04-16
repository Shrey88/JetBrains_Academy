import java.io.InputStream;

class Main {
    public static void main(String[] args) throws Exception {
        InputStream inputStream = System.in;
        byte[] bytesArr = new byte[50];

        int current = inputStream.read(bytesArr);

        for (int i = 0; i < current; i++) {
            System.out.print(bytesArr[i]);
        }

        inputStream.close();
    }
}