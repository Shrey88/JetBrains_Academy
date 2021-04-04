import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();

        str1 = str1.trim().replace(" ", "").replace("\t", "");
        str2 = str2.trim().replace(" ", "").replace("\t", "");

        if(str1.equals(str2)){
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}