import java.util.*;

public class knapsack {
    public static int gcd(int a, int b) {
        if (b > a)
            return gcd(b, a);
        int m = a % b;
        if (m == 1 || m == 0)
            return m;
        else
            return gcd(b, m);
    }

    static class MyHashIntArrayInt extends HashMap<Integer, ArrayList<Integer>> {
        public MyHashIntArrayInt() {
            super();
        }

        public MyHashIntArrayInt(MyHashIntArrayInt m) {
            super(m);
        }
    }

    static public void Show(MyHashIntArrayInt m) {
        for (Map.Entry<Integer, ArrayList<Integer>> e : m.entrySet()) {
            System.out.println(e.getKey() + "-> " + e.getValue());
        }
    }

    static MyHashIntArrayInt KnapsackTree = new MyHashIntArrayInt();

    public static void KnapsackTree(ArrayList<Integer> arr, int value,
            ArrayList<Integer> knapsack) {
        if (arr.isEmpty()) {
            KnapsackTree.put(value, knapsack);
            return;
        }
        // storing the first value of the arr
        int current_value = arr.get(0);
        var temp_arr = new ArrayList<>(arr);
        temp_arr.remove(0);
        var temp_knapsack = new ArrayList<>(knapsack);
        // for zero side
        temp_knapsack.add(0);
        KnapsackTree(temp_arr, value, new ArrayList<>(temp_knapsack));
        // for one side
        temp_knapsack = new ArrayList<>(knapsack);
        temp_knapsack.add(1);
        KnapsackTree(temp_arr, value + current_value, new ArrayList<>(temp_knapsack));
    }

    public static int inverseMod(int n, int m) {
        int i = 1;
        for (; i < m; i++) {
            if ((n * i) % m == 1) {
                break;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        Random rand = new Random();
        // Private Key
        var pKey = new ArrayList<Integer>(Arrays.asList(1, 2, 4, 10, 20, 40));
        System.out.println("private key-> " + pKey);
        int sum_pKey = 0;
        for (Integer i : pKey)
            sum_pKey += i;
        System.out.println("sum of pKey-> " + +sum_pKey);
        int m = rand.nextInt(420) + sum_pKey + 1;
        System.out.println("m-> " + m);
        int n;
        while (true) {
            n = rand.nextInt(200);
            if (gcd(n, m) == 1)
                break;
        }
        System.out.println("n-> " + n);
        // Public Key
        var pubKey = new ArrayList<Integer>();
        for (Integer i : pKey)
            pubKey.add((i * n) % m);
        System.out.println("pubKey-> " + pubKey);
        // Plaintext
        var plainText = new ArrayList<Integer>();
        for (int i = 0; i < 12; i++) {
            plainText.add(rand.nextInt(200) % 2);
        }
        System.out.println("plainText -> " + plainText);
        // Cipher Text
        int length = pubKey.size();
        var cipherText = new ArrayList<Integer>();
        var temp = 0;
        for (int i = 0; i < plainText.size(); i++) {
            temp += plainText.get(i) * pubKey.get(i % length);
            if ((i + 1) % length == 0) {
                cipherText.add(temp);
                temp = 0;
            }
        }
        System.out.println("cipherText-> " + cipherText);

        // Decryption
        int n_inv = inverseMod(n, m);
        System.out.println("n_inv-> " + n_inv);
        KnapsackTree(pKey, 0, new ArrayList<>());
        var decryptedText = new ArrayList<Integer>();
        for (int i : cipherText)
            decryptedText.addAll(KnapsackTree.get((i * n_inv) % m));
        System.out.println("DecryptedText -> " + decryptedText);
    }
}
