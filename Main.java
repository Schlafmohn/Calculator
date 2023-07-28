import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ScannerException {
        Scanner scan = new Scanner(System.in);
        System.out.println(calc(scan.nextLine()));
        scan.close();
    }

    public static String calc(String input) throws ScannerException {
        String[] maths = input.split(" ");

        if (maths.length == 1) {
            throw new ScannerException("Строка не является математической операцией");
        } else if (maths.length > 3) {
            throw new ScannerException("Формат математической операции не удовлетворяет заданию");
        }

        String formatMaths = "arab";
        int math1, math2;

        try {
            math1 = Integer.parseInt(maths[0]);
        } catch (NumberFormatException e) {
            math1 = getNormalNumberFromRoman(maths[0]);
            formatMaths = "roman";
        }

        try {
            math2 = Integer.parseInt(maths[2]);
            if (!formatMaths.equals("arab")) {
                throw new ScannerException("Используются одновременно разные системы счисления");
            }
        } catch (NumberFormatException e) {
            if (!formatMaths.equals("roman")) {
                throw new ScannerException("Используются одновременно разные системы счисления");
            }
            math2 = getNormalNumberFromRoman(maths[2]);
        }

        if (!(math1 > 0 && math1 <= 10) || !(math2 > 0 && math2 <= 10)) {
            throw new ScannerException("Калькулятор принимает на вход числа от 1 до 10 включительно");
        }

        int answer;
        switch (maths[1]) {
            case "+":
                answer = math1 + math2;
                break;
            case "-":
                answer = math1 - math2;
                break;
            case "*":
                answer = math1 * math2;
                break;
            case "/":
                answer = math1 / math2;
                break;
            default:
                throw new ScannerException("Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления");
        }

        String number;
        if (formatMaths.equals("arab")) {
            number = String.valueOf(answer);
        } else {
            number = getRomanNumberFromNormal(answer);
        }

        return number;
    }

    public static int getNormalNumberFromRoman(String math) {
        String[] romanNumbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        int normalNumber = 11;

        for (int i = 0; i < romanNumbers.length; i++) {
            if (math.equals(romanNumbers[i])) {
                normalNumber = i + 1;
                break;
            }
        }

        return normalNumber;
    }

    public static String getRomanNumberFromNormal(int math) throws ScannerException {
        if (math <= 0) {
            throw new ScannerException("В римской системе нет отрицательных чисел");
        }

        String[] romanUnits = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] romanDozens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};

        return romanDozens[math / 10] + romanUnits[math % 10];
    }
}
