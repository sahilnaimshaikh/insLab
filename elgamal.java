import java.util.*;
import java.lang.Math;

public class elgamal {
    static Random rand = new Random();

    static public class cipherText {
        int y1;
        int y2;

        public cipherText() {
        }

        public cipherText(int y1, int y2) {
            this.y1 = y1;
            this.y2 = y2;
        }

        public int getY1() {
            return y1;
        }

        public int getY2() {
            return y2;
        }

        @Override
        public String toString() {
            return "cipherText [y1=" + y1 + ", y2=" + y2 + "]";
        }
    }

    static public class encrypt {
        int p;
        int alpha;
        int beta;
        int a;
        int msg;
        int k = 853;
        cipherText c = new cipherText();

        public encrypt(int p, int alpha, int a, int msg) {
            this.p = p;
            this.alpha = alpha;
            this.a = a;
            this.msg = msg;
            this.beta = (int) (Math.pow(alpha, a) % p);

            int k = rand.nextInt(p - 1);
            int y1 = (int) (Cal(alpha, k, p));
            int y2 = (int) ((msg * (Cal(beta, k, p))) % p);
            cipherText c = new cipherText(y1, y2);
            this.c = c;
        }

        public cipherText getC() {
            return c;
        }

        @Override
        public String toString() {
            return "encrypt [p=" + p + ", alpha=" + alpha + ", beta=" + beta + ", a=" + a + ", msg=" + msg + ", k=" + k
                    + ", c=" + c + "]";
        }
    }

    static public int Cal(int a, int k, int p) {
        int answer = 1;
        for (int i = 1; i <= k; i++) {
            answer = (answer * a) % p;
        }
        return (answer % p);
    }

    static public int _inv(int a, int p) {
        int i = 1;
        for (; i < p; i++) {
            if ((a * i) % p == 1) {
                break;
            }
        }
        return i;
    }

    static public int Decrypt(cipherText c, int a, int p) {
        int y1 = c.getY1();
        int y2 = c.getY2();
        return (y2 * _inv(Cal(y1, a, p), p)) % p;
    }

    static public boolean primeChecker(int a) {

        for (int i = 2; i < a / 2; i++) {
            if (a % i == 0) {
                System.out.println(i);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int p, alpha = 2, a = 765, msg = 1299;

        while (true) {
            System.out.print("Enter a Prime Number: ");
            p = input.nextInt();
            if (primeChecker(p))
                break;
        }

        System.out.print("Enter Secret Key (0 <= a <= " + (p - 2) + "): ");
        a = input.nextInt();

        System.out.print("Enter alpha: ");
        alpha = input.nextInt();

        System.out.print("Enter message (0< msg < p): ");
        msg = input.nextInt();

        encrypt e = new encrypt(p, alpha, a, msg);
        System.out.println(e);

        int con_msg = Decrypt(e.getC(), a, p);
        System.out.println("Decrypted message: " + con_msg);
    }
}

