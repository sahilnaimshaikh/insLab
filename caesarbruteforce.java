import java.util.Scanner;

public class caesarbruteforce {
    char[] input;
    int key;

    void bruteforce() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the String : ");
        String ip = sc.nextLine();
        input = ip.toCharArray();

        System.out.println("\nAll Possible Combinations of Plain Texts: \n");

        for (key = 1; key < 27; key++) {
            for (int i = 0; i < input.length; i++) {
                if (input[i] == ' ')
                    continue;
                else {
                    if (input[i] >= 'A' && input[i] <= 'Z') {
                        input[i] = (char) (input[i] - key);
                        if (input[i] < 'A') {
                            input[i] = (char) (input[i] + 26);
                        }
                    } else {
                        input[i] = (char) (input[i] - key);
                        if (input[i] < 'a') {
                            input[i] = (char) (input[i] + 26);
                        }
                    }
                }
            }
            
            System.out.println("Key = " + key + " |" + " Decrypted String | " + String.valueOf(input));
            input = ip.toCharArray();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        new caesarbruteforce().bruteforce();
        
    }
}
