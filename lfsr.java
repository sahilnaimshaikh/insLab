import java.util.*;

public class lfsr {
    static int runTime = 16;

    static public int XOR(int a, int b) {
        if (a == b)
            return 0;
        else
            return 1;
    }

    static public ArrayList<Integer> MyFunc(ArrayList<Integer> iv, ArrayList<Integer> equation) {
        ArrayList<Integer> answer = new ArrayList<>();
        int length = iv.size();
        if (length == 1)
            return iv;
        int xor_value = iv.get(equation.get(0) - 1);
        for (int i = 1; i < equation.size(); i++) {
            xor_value = XOR(xor_value, iv.get(equation.get(i) - 1));
        }
        for (int i = 1; i < length; i++) {
            answer.add(iv.get(i - 1));
        }
        answer.add(0, xor_value);
        return answer;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        var equation = new ArrayList<Integer>();
        var iv = new ArrayList<Integer>();
        System.out.print("Enter number of Powers in the equation: ");
        int eq_length = input.nextInt();
        System.out.print("Enter IV length: ");
        int iv_length = input.nextInt();
        System.out.print("Enter powers in the equation : ");
        for (int i = 0; i < eq_length; i++) {
            int temp = input.nextInt();
            equation.add(temp);
        }
        System.out.print("Enter IV values : ");
        for (int i = 0; i < iv_length; i++) {
            int temp = input.nextInt();
            iv.add(temp);
        }
        var key = new ArrayList<Integer>();
        int maxValue = Collections.max(equation);
        for (int i = 0; i < runTime; i++) {
            key.add(iv.get(maxValue - 1));
            iv = new ArrayList<>(MyFunc(iv, equation));
            for (int j : iv) {
                System.out.print(j);
            }
            System.out.println("->" + i);
        }
        System.out.print("Key->");
        for (int i : key) {
            System.out.print(i);
        }
        input.close();
    }
}
