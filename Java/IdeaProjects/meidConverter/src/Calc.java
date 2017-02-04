import java.awt.datatransfer.*;
import java.awt.Toolkit;
import java.util.Scanner;

public class Calc {

    private static meidHelper MEIDtemp;
    private static String meidHex;

    public static void main(String[] args) {

        while (true) {
            System.out.print("Enter DEC or HEX number: ");
            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();

            try {
                MEIDtemp = new meidHelper(userInput);
            } catch (Exception i) {}

            if (userInput.length() == 18) {
                meidHex = MEIDtemp.getMeidHex();
                String[] str = meidHex.split("");
                int checkNum = getIMEI(meidHex, str);
                String cN = Integer.toString(checkNum);
                String imei = meidHex.concat(cN);
                printOut(imei);

            }

            if (userInput.length() == 14 || userInput.length() == 15) {
                String imei;
                if (userInput.length() == 14) {
                    meidHex = userInput;
                    String[] str = meidHex.split("");
                    int checkNum = getIMEI(meidHex, str);
                    String cN = Integer.toString(checkNum);
                    imei = meidHex.concat(cN);
                } else {
                    imei = userInput;
                }

                printOut(imei);
            }
        }
    }

    public static int getIMEI(String meidHex, String[] str) {

        int total = 0;
        for (int i = 0; i < meidHex.length(); i++) {
            int digit = Integer.parseInt(str[i]);
            if ((i + 1) % 2 == 0) {
                digit = digit * 2;
                if (digit > 9) {
                    String number = String.valueOf(digit);
                    String[] splitStr = number.split("");
                    int NUM1 = Integer.parseInt(splitStr[0]);
                    int NUM2 = Integer.parseInt(splitStr[1]);
                    int sum = NUM1 + NUM2;
                    total = total + sum;
                    continue;
                }
                total = total + digit;
                continue;
            }
            total = total + digit;
        }

        int modHelper = total % 10;
        int checkNum = 10 - modHelper;
        //System.out.println(checkNum);
        return checkNum;
    }

    public static void printOut(String imei) {

        String[] str = imei.split("");
        int temp = getIMEI(imei, str);
        if (temp == 10) {
            System.out.println("Valid MEID");
            System.out.println("IMEI: " + imei);
            copyIMEI(imei);
        } else {
            System.out.println("NOT A VALID MEID --- Check input");
            System.out.println();
        }
    }

    public static void copyIMEI (String imei) {

        StringSelection stringSelection = new StringSelection(imei);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);
        System.out.println("Copied to clipboard!");
        System.out.println();
    }

    public static boolean validate(String numberString) {
        return checkSum(numberString) == 0;
    }

    public static int checkSum(String numberString) {
        return checkSum(numberString, false);
    }

    public static int checkSum(String numberString, boolean noCheckDigit) {
        int sum = 0, checkDigit = 0;

        if(!noCheckDigit)
            numberString = numberString.substring(0, numberString.length()-1);

        boolean isDouble = true;
        for (int i = numberString.length() - 1; i >= 0; i--) {
            int k = Integer.parseInt(String.valueOf(numberString.charAt(i)));
            sum += sumToSingleDigit((k * (isDouble ? 2 : 1)));
            isDouble = !isDouble;
        }

        if ((sum % 10) > 0)
            checkDigit = (10 - (sum % 10));

        return checkDigit;
    }

    private static int sumToSingleDigit(int k) {
        if (k < 10)
            return k;
        return sumToSingleDigit(k / 10) + (k % 10);
    }
}



