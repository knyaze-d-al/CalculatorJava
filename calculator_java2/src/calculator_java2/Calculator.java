package calculator_java2;

import java.util.Scanner;

public class Calculator {
    private static String input;
    private static int result;
    private static String op1, op2;
    private static int op1_value, op2_value;
    private static char operator;
    private static boolean isRoman;
    private static void checkInput(String input) {
        int N = input.length();
        if (N < 3) throw new NumberFormatException("Неверный формат входных данных");
        if (
                input.charAt(0) == '+' || input.charAt(N-1) == '+'||
                input.charAt(0) == '-' || input.charAt(N-1) == '-' ||
                input.charAt(0) == '*' || input.charAt(N-1) == '*' ||
                input.charAt(0) == '/' || input.charAt(N-1) == '/'
        )
            throw new NumberFormatException("Неверный формат входных данных");
        boolean hasOperator = false;
        for (int i = 1; i < N-1; i++) {
            if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*' || input.charAt(i) == '/') {
                op1 = input.substring(0, i);
                op2 = input.substring(i + 1);
                operator = input.charAt(i);
                hasOperator = true;
                break;
            }
        }
        if(!hasOperator) throw new NumberFormatException("Неверный формат входных данных");
    }
    private static int count(String op1, String op2, char operator) {
        try {
            op1_value = Integer.parseInt(op1);
            if (op1_value < 1 || op1_value > 10) throw new RuntimeException("Неверный формат входных данных");
            op2_value = Integer.parseInt(op2);
            if (op2_value < 1 || op2_value > 10) throw new RuntimeException("Неверный формат входных данных");
            switch (operator) {
                case '+': result = op1_value + op2_value; break;
                case '-': result = op1_value - op2_value; break;
                case '*': result = op1_value * op2_value; break;
                case '/': result = op1_value / op2_value; break;
            }
            return result;
        }
        catch (NumberFormatException e) {
            RomanNumeral rn1 = RomanNumeral.parseRoman(op1);
            RomanNumeral rn2 = RomanNumeral.parseRoman(op2);
            isRoman = true;
            switch (operator) {
                case '+': result = rn1.getValue() + rn2.getValue(); break;
                case '-': result = rn1.getValue() - rn2.getValue(); break;
                case '*': result = rn1.getValue() * rn2.getValue(); break;
                case '/': result = rn1.getValue() / rn2.getValue(); break;
            }
        return result;
        }
    }
    public static void main(String[] args) {
        while (true) {
            isRoman = false;
            Scanner sc = new Scanner(System.in);
            input = sc.next();
            checkInput(input);
            result = count(op1, op2, operator);
            if (isRoman) {
                String romanOutput;
                if (result > 10)
                    romanOutput = RomanNumeral.valueOf(result - result % 10) + RomanNumeral.valueOf(result % 10);
                else
                    romanOutput = RomanNumeral.valueOf(result);
                System.out.println(romanOutput);
            }
            else System.out.println(result);
        }
    }
}

class RomanNumeral {
    private int value;
    public int getValue() {
        return this.value;
    }
    public RomanNumeral(String str) {
        switch (str) {
            case "I": this.value = 1; break;
            case "II": this.value = 2; break;
            case "III": this.value = 3; break;
            case "IV": this.value = 4; break;
            case "V": this.value = 5; break;
            case "VI": this.value = 6; break;
            case "VII": this.value = 7; break;
            case "VIII": this.value = 8; break;
            case "IX": this.value = 9; break;
            case "X": this.value = 10; break;
        }
    }
    public static RomanNumeral parseRoman(String str) {
        switch (str) {
            case "I":
                return new RomanNumeral("I");
            case "II":
                return new RomanNumeral("II");
            case "III":
                return new RomanNumeral("III");
            case "IV":
                return new RomanNumeral("IV");
            case "V":
                return new RomanNumeral("V");
            case "VI":
                return new RomanNumeral("VI");
            case "VII":
                return new RomanNumeral("VII");
            case "VIII":
                return new RomanNumeral("VIII");
            case "IX":
                return new RomanNumeral("IX");
            case "X":
                return new RomanNumeral("X");
            default:
                throw new NumberFormatException("Неверный формат входных данных");
        }
    }
    public static String valueOf(int number) {
        String str = "";
        switch(number) {
            case(1): str = str + "I"; break;
            case(2): str = str + "II"; break;
            case(3): str = str + "III"; break;
            case(4): str = str + "IV"; break;
            case(5): str = str + "V"; break;
            case(6): str = str + "VI"; break;
            case(7): str = str + "VII"; break;
            case(8): str = str + "VIII"; break;
            case(9): str = str + "IX"; break;
            case(10): str = str + "X"; break;
            case(20): str = str + "XX"; break;
            case(30): str = str + "XXX"; break;
            case(40): str = str + "XL"; break;
            case(50): str = str + "L"; break;
            case(60): str = str + "LX"; break;
            case(70): str = str + "LXX"; break;
            case(80): str = str + "LXXX"; break;
            case(90): str = str + "XC"; break;
            case(100): str = str + "C"; break;
        }
        return str;
    }
}