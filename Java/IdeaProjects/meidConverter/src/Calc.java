import java.awt.datatransfer.*;
import java.awt.Toolkit;
import java.util.Scanner;

public class Calc {

    private static meidHelper MEID;

    public static void main(String[] args){

        while(true) {
            System.out.print("Enter 18 digit DEC meid number: ");
            Scanner input = new Scanner(System.in);
            String userInput = input.nextLine();

            try {
                MEID = new meidHelper(userInput);
            } catch (Exception ignored) {
            }

            String meidHex = MEID.getMeidHex();
            String[] str = meidHex.split("");
            int total = 0;

            for (int i = 0; i < 14; i++) {
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
            String strNum = Integer.toString(checkNum);
            String imei = meidHex.concat(strNum);

            System.out.println("IMEI (HEX 15 digits): " + imei);

            StringSelection stringSelection = new StringSelection(imei);
            Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
            clpbrd.setContents(stringSelection, null);
            System.out.println("Copied to clipboard!\n");
        }
    }
}

