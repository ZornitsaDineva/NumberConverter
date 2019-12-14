/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplicationnumtoword;

import java.util.Scanner;

public class JavaApplicationNumToWord {

    public int readInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number: ");
        int n = scanner.nextInt();
        return n;
    }

    public String convertToWord(int num) {
        int[] digits = getDigits(num);
        final int len = digits.length;
        if (len > 4) {
            throw new IllegalArgumentException("Numbers bigger than 9999 are not supported!");
        }

        StringBuilder numBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            final int digit = digits[i];
            int position = len - i;
            if (position == 4) {
                numBuilder.append(digitToWord(digit) + " thousand ");
            } else if (position == 3) {
                if (digit != 0) {
                    numBuilder.append(digitToWord(digit) + " hundred ");
                }
            } else if (position == 2) {
                if (digit == 1) {
                    int teenDigit = digits[i + 1];
                    numBuilder.append(teenDigitToWord(teenDigit));
                    i++;
                } else if (digit > 1) {
                    numBuilder.append(digitToTens(digit));
                    numBuilder.append(" ");
                }
            } else if (position == 1) {
                if (digit != 0 || num == 0) {
                    numBuilder.append(digitToWord(digit));
                }
            }
        }

        return numBuilder.toString();
    }

    private int[] getDigits(int num) {
        final String numStr = String.valueOf(num);
        char[] chars = numStr.toCharArray();
        final int len = chars.length;
        int[] digits = new int[len];

        for (int i = 0; i < len; i++) {
            digits[i] = chars[i] - '0';
        }

        return digits;
    }

    public void printResult(String numAsWord) {
        System.out.println(numAsWord);
    }

    public static void main(String[] args) {
        try {
            JavaApplicationNumToWord app = new JavaApplicationNumToWord();
            int number = app.readInput();
            String stringNum = app.convertToWord(number);
            app.printResult(stringNum);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String digitToWord(int digit) {
        final String[] words = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        return words[digit];
    }

    private String teenDigitToWord(int teenDigit) {
        final String[] wordsTeen = new String[]{"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        return wordsTeen[teenDigit];
    }

    private String digitToTens(int digit) {
        final String[] multipleOfTen = new String[]{"twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eghty", "ninety"};
        return multipleOfTen[digit - 2];
    }

}
