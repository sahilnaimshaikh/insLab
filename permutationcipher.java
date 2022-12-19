import java.nio.file.SecureDirectoryStream;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class permutationcipher {
    String encrypt(String pt, int depth) throws Exception {
        int r = depth, len = pt.length();
        int c = len / depth;
        char mat[][] = new char[r][c];
        int k = 0;
        String ct = "";
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                if (k != len) {
                    mat[j][i] = pt.charAt(k++);
                }
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                ct += mat[i][j];
            }
        }
        return ct;
    }

    String Decryption(String ct, int depth) throws Exception {
        int r = depth, len = ct.length();
        int c = len / depth;
        char mat[][] = new char[r][c];
        int k = 0;
        String pt = "";
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                mat[i][j] = ct.charAt(k++);
            }
        }
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                pt += mat[j][i];
            }
        }
        return pt;
    }

    public static void main(String args[]) {
        try {
            System.out.println();
            System.out.println("Encrypting Using Permutation Cipher: ");
            System.out.println();
            permutationcipher rf = new permutationcipher();
            System.out.println("Enter the Text to be Encrypted: ");
            System.out.println(); 
            Scanner sc = new Scanner(System.in);
            int depth;
            String pt, ct, decryptedtext;
            pt = sc.nextLine();
            System.out.println();
           
            System.out.println("Enter the Depth of the Permutation: ");
            System.out.println();
            depth = sc.nextInt();
            while (pt.length() % depth != 0) {
                pt += " ";
            }
            ct = rf.encrypt(pt, depth);
            System.out.println();
            System.out.println("Encrypted Text: " + ct);
            System.out.println();
            decryptedtext = rf.Decryption(ct, depth);
            System.out.println("Decrypted Text: " + decryptedtext);
        } 
        catch (Exception ex) {
            Logger.getLogger(permutationcipher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
/* 
import java.io.*;
import java.util.Scanner;

public class Permutation_Cipher {
    public static char p[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h','i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r','s', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
    
    public static char ch[] = { 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I','O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J','K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M' };
    
    public static String doEncryption(String s) {
        char c[] = new char[(s.length())];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < 26; j++) {
                if (p[j] == s.charAt(i)) {
                    c[i] = ch[j];
                    break;
                }
            }
        }
        return (new String(c));
    }

    public static String doDecryption(String s) {
        char pt[] = new char[(s.length())];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < 26; j++) {
                if (ch[j] == s.charAt(i)) {
                    pt[i] = p[j];
                    break;
                }
            }
        }
        return (new String(pt));
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the message:");
        String en = doEncryption(sc.next().toLowerCase());
        System.out.println("Encrypted message:" + en);
        System.out.println("Decrypted message:" + doDecryption(en));
        sc.close();
    }
}
*/

