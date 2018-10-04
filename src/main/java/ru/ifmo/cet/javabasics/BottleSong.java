package ru.ifmo.cet.javabasics;

/**
 * Нужно реализовать констурктор и метод, возвращающий слова песни про бутылки на стене.
 * <p>
 * Слова следующие:
 * <p>
 * 99 bottles of beer on the wall, 99 bottles of beer
 * Take one down, pass it around, 98 bottles of beer
 * 98 bottles of beer on the wall, 98 bottles of beer
 * Take one down, pass it around, 97 bottles of beer
 * 97 bottles of beer on the wall, 97 bottles of beer
 * Take one down, pass it around, 96 bottles of beer
 * 96 bottles of beer on the wall, 96 bottles of beer
 * Take one down, pass it around, 95 bottles of beer
 * 95 bottles of beer on the wall, 95 bottles of beer
 * ...
 * <p>
 * 3 bottles of beer on the wall, 3 bottles of beer
 * Take one down, pass it around, 2 bottles of beer
 * 2 bottles of beer on the wall, 2 bottles of beer
 * Take one down, pass it around, 1 bottles of beer
 * 1 bottle of beer on the wall, 1 bottle of beer
 * Take one down, pass it around, no more bottles of beer on the wall
 * No more bottles of beer on the wall, no more bottles of beer
 * Go to the store and buy some more, 99 bottles of beer on the wall
 * <p>
 * Дело усложняется тем, что текст песни переменный:
 * За раз может быть взято несколько бутылок.
 * Значение передается в качестве параметра конструктора
 * Нужно ограничить возможность взятия бутылок натуральным число не более 99 бутылок за раз.
 */
public class BottleSong {

    private int numOfBottles;

    public static String[] numbers = {
            "",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
            "ten",
            "eleven",
            "twelve",
            "thirteen",
            "fourteen",
            "fifteen",
            "sixteen",
            "seventeen",
            "eighteen",
            "nineteen"
    };

    public static String[] hignumbers = {
            "",
            "",
            "twenty",
            "thirty",
            "forty",
            "fifty",
            "sixty",
            "seventy",
            "eighty",
            "ninety"
    };

    public static String intToText(int n) {
        String result = null;

        if (n > 0 && n < 20) {
            result = numbers[n];
        }

        if (n >= 20 && n <= 99) {
            result = hignumbers[n / 10];

            if (!(n % 10 == 0)) {
                result += " ";
            } else result += "";

            result += numbers[n % 10];
        }

        return result;
    }


    public BottleSong(int bottleTakenAtOnce) {

        this.numOfBottles = bottleTakenAtOnce;
    }

    public String getBottleSongLyrics() {
        String resultString = "";

        if (numOfBottles >= 100) {
            throw new IllegalArgumentException();
        }
        if (numOfBottles <= 0) {
            throw new IllegalArgumentException();
        }

        if (numOfBottles <= 99 && numOfBottles > 0) {

            int beerNum = 99;
            String mno = "bottles";
            while (beerNum > 0) {
                if (beerNum == 1) {
                    mno = "bottle";
                }

                if (numOfBottles > beerNum)
                    numOfBottles = beerNum;

                resultString += (beerNum + " " + mno + " of beer on the wall, " + beerNum + " " + mno + " of beer.\n");
                resultString += ("Take " + intToText(numOfBottles) + " down and pass around, ");

                beerNum = beerNum - numOfBottles;

                if (beerNum > 0) {
                    if (beerNum == 1)
                        mno = "bottle";
                    resultString += (beerNum + " " + mno + " of beer on the wall.\n");
                } else {
                    resultString += ("no more bottles of beer on the wall.\n");
                    resultString += ("No more bottles of beer on the wall, no more bottles of beer.\n");
                    resultString += ("Go to the store and buy some more, 99 bottles of beer on the wall.\n");
                }
            }

        }
        return resultString;
    }
}
