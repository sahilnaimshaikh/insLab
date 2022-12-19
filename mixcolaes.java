import java.util.*;

public class mixcolaes {

    static StringBuffer primePoly = new StringBuffer("100011011");
    static ArrayList<StringBuffer> coefficients = new ArrayList<>() {
        {
            add(new StringBuffer("10"));
            add(new StringBuffer("11"));
            add(new StringBuffer("01"));
            add(new StringBuffer("01"));
        }
    };

    static public class Matrix_StringBuffer extends ArrayList<ArrayList<StringBuffer>> {
        public Matrix_StringBuffer() {
            super();
        }

        public Matrix_StringBuffer(Matrix_StringBuffer m) {
            super(m);
        }
    }

    static public void ShowMatrix(Matrix_StringBuffer m) {
        for (ArrayList<StringBuffer> i : m) {
            System.out.println(i);
        }
    }

    static public Matrix_StringBuffer Convert_to_Matrix(StringBuffer text) {
        var textMatrix = new Matrix_StringBuffer();
        var forCol = new StringBuffer();
        var forRow = new ArrayList<StringBuffer>();

        for (int i = 0; i < text.length(); i++) {
            forCol.append(Character.toString(text.charAt(i)));
            if ((i + 1) % 8 == 0) {
                forRow.add(new StringBuffer(forCol));
                forCol = new StringBuffer();
            }
            if ((i + 1) % 32 == 0) {
                textMatrix.add(new ArrayList<>(forRow));
                forRow.clear();
            }
        }
        return textMatrix;
    }

    static public String XOR(char a, char b) {
        if (a == b)
            return "0";
        else
            return "1";
    }

    static public StringBuffer XOR(StringBuffer x, StringBuffer y) {
        var temp = new StringBuffer();
        for (int i = 0; i < x.length(); i++) {
            temp.append(XOR(x.charAt(i), y.charAt(i)));
        }
        return temp;
    }

    static public StringBuffer Fmultiply(StringBuffer s, StringBuffer coff) {
        var value = new StringBuffer(s);
        if (coff.charAt(0) == '1') {
            var new_s = new StringBuffer(s);
            new_s.append('0');
            // System.out.println(new_s);
            value = new StringBuffer(new_s);
            if (coff.charAt(1) == '1') {
                value = new StringBuffer();
                value.append(new_s.charAt(0));
                for (int i = 0; i < new_s.length() - 1; i++) {
                    value.append(XOR(new_s.charAt(i), new_s.charAt(i + 1)));
                }
            }
        }

        if (value.length() > 8) {
            if (value.charAt(0) == '1')
                for (int i = 0; i < 9; i++) {
                    char t = XOR(value.charAt(i), primePoly.charAt(i)).charAt(0);
                    value.setCharAt(i, t);
                }
            value.deleteCharAt(0);
        }
        return value;
    }

    static public ArrayList<StringBuffer> SijFunc(ArrayList<StringBuffer> wordCol) {
        var answer_arr = new Matrix_StringBuffer();
        int l = wordCol.size();

        for (int i = 0; i < l; i++) {
            var for_Sij = new ArrayList<StringBuffer>();
            for (int j = 0; j < l; j++) {
                int temp_coffIndex = (j - i) >= 0 ? (j - i) % l : l + ((j - i) % l);
                for_Sij.add(Fmultiply(wordCol.get(j), coefficients.get(temp_coffIndex)));
            }
            answer_arr.add(for_Sij);
        }

        var Sij = new ArrayList<StringBuffer>();
        for (int i = 0; i < answer_arr.size(); i++) {
            var answer = new StringBuffer(answer_arr.get(i).get(0));
            // System.out.println(answer);
            for (int j = 1; j < answer_arr.get(i).size() - 1; j++) {
                answer = new StringBuffer(XOR(answer, answer_arr.get(0).get(i)));
                // System.out.println(answer);
            }
            Sij.add(answer);
        }
        // System.out.println("Answer->" + Sij);
        return Sij;
    }

    static public Matrix_StringBuffer Transpose(Matrix_StringBuffer m) {
        var answer = new Matrix_StringBuffer();

        for (int i = 0; i < m.get(0).size(); i++) {
            var temp = new ArrayList<StringBuffer>();
            for (int j = 0; j < m.size(); j++) {
                temp.add(m.get(j).get(i));
            }
            answer.add(temp);
        }
        return answer;
    }

    static public Matrix_StringBuffer MixColFunc(Matrix_StringBuffer matrix) {

        var answer = new Matrix_StringBuffer();
        for (int j = 0; j < matrix.get(0).size(); j++) {
            var temp = new ArrayList<StringBuffer>();
            for (int i = 0; i < matrix.size(); i++) {
                temp.add(matrix.get(i).get(j));
            }
            // System.out.println();
            answer.add(SijFunc(temp));

        }
        // ShowMatrix(answer);

        return new Matrix_StringBuffer(Transpose(answer));
    }

    public static void main(String[] args) {
        var plainText = new StringBuffer(
                "01110011001000011110110100001100101101011010110110101101100010101000100001110000101100010110000101011110110000101110011001010000");

        var plainTextMatrix = new Matrix_StringBuffer(Convert_to_Matrix(plainText));
        System.out.println("Word Matrix->");
        ShowMatrix(plainTextMatrix);
        System.out.println("Answer->");
        ShowMatrix(MixColFunc(plainTextMatrix));
        // System.out.println("Main->" + plainTextMatrix.get(1).get(0));
        // Fmultiply(plainTextMatrix.get(1).get(0), "10");

    }

    static public ArrayList<String> HexDeciToBinary = new ArrayList<>(Arrays.asList("0000", "0001", "0010", "0011",
            "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"));

    static public int Alpha_To(Character t) {

        String s = Character.toString(t);
        try {
            int i = Integer.parseInt(s);
            return i;
        } catch (Exception e) {
            switch (s) {
                case "a":
                    return 10;
                case "b":
                    return 11;
                case "c":
                    return 12;
                case "d":
                    return 13;
                case "e":
                    return 14;
                default:
                    return 15;
            }
        }
    }
}

