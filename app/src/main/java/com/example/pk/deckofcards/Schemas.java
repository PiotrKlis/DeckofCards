package com.example.pk.deckofcards;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by PK on 09.08.2017.
 */

 class Schemas {

    private String TAG = "Schemas";

    /**
     * Method which checks for "figures" match
     * @param arrayList list of values
     * @return empty or filled String
     */

     String figures(ArrayList<String> arrayList) {

        String result = "";

        int freqJ = Collections.frequency(arrayList, "J");
        int freqQ = Collections.frequency(arrayList, "Q");
        int freqK = Collections.frequency(arrayList, "K");

        if (freqJ + freqK + freqQ >= 3) {
            result = "Figury";
            Log.i(TAG, "Figury");
        }

        return result;

    }

    /**
     * Method which checks for "color" match
     * @param arrayList list of values
     * @return empty or filled String
     */

     String color(ArrayList<String> arrayList) {

        String result = "";

        int freqS = Collections.frequency(arrayList, "S");
        if (freqS >= 3) {
            result = ("Kolor - Pik");
            Log.i(TAG, "Kolor - Pik");
        }

        int freqD = Collections.frequency(arrayList, "D");
        if (freqD >= 3) {
            result = ("Kolor - Karo");
            Log.i(TAG, "Kolor - Karo");
        }

        int freqC = Collections.frequency(arrayList, "C");
        if (freqC >= 3) {
            result = ("Kolor - Trefl");
            Log.i(TAG, "Kolor - Tefl");
        }

        int freqH = Collections.frequency(arrayList, "H");
        if (freqH >= 3) {
            result = ("Kolor - Kier");
            Log.i(TAG, "Kolor - Kier");
        }

        return result;
    }

    /**
     * Method which checks for "stairs" match
     * @param arrayList list of values
     * @return empty or filled String
     */

     String stairs(ArrayList<Integer> arrayList) {

        String result = "";
         Boolean foundIt = false;

         for (Integer value : arrayList) {

            boolean negativeNumber = false;
            boolean positiveNumber = false;

            for (int i = 0; i < arrayList.size(); i++) {

                if (foundIt) {
                    break;
                }

                int conditionCheck = arrayList.get(i) - value;

                if (conditionCheck == 1 || conditionCheck == -1) {

                    if (conditionCheck < 0) {
                        negativeNumber = true;
                    } else {
                        positiveNumber = true;
                    }

                    if (negativeNumber && positiveNumber) {

                        if (result.equals("")) {
                            result = "Schodki";
                            Log.i(TAG, "Schodki");
                            foundIt = true;
                            break;
                        }
                    }
                }
            }
        }
        return result;

    }

    /**
     * Method which checks for twins match
     * @param arrayList list of values
     * @return empty or filled String
     */

         String twins(ArrayList<Integer> arrayList) {

            String result = "";
            Boolean foundIt = false;

            for (Integer value : arrayList) {

                if (foundIt) {
                    break;
                }

                int countTwins = 0;

                for (int i = 0; i < arrayList.size(); i++) {

                    if (arrayList.get(i) - value == 0) {

                        countTwins += 1;

                        if (countTwins == 3) {

                            if (result.equals("")) {
                                result = "Bliźniaki";
                                Log.i(TAG, "Bliźniaki");
                                foundIt = true;
                                break;
                            } else {
                                result = result + ", Bliźniaki";
                                Log.i(TAG, "Bliźniaki");
                                foundIt = true;
                                break;
                            }
                        }
                    }
                }
            }

        return result;

    }

    /**
     * Method which changes Strings to Ints and change letters into given numbers
     * @param arrayList list of values
     * @return ArrayList of Ints
     */

     ArrayList<Integer> changeValuesToInts(ArrayList<String> arrayList) {

        ArrayList<Integer> integerArrayList = new ArrayList<>();

        for (String arr : arrayList) {
            switch (arr) {

                case "A":
                    integerArrayList.add(DictCards.Ace);
                    break;
                case "0":
                    integerArrayList.add(DictCards.Ten);
                    break;
                case "J":
                    integerArrayList.add(DictCards.Jack);
                    break;
                case "Q":
                    integerArrayList.add(DictCards.Queen);
                    break;
                case "K":
                    integerArrayList.add(DictCards.King);
                    break;
                default:
                    integerArrayList.add(Integer.parseInt(arr));
            }
        }

        return integerArrayList;

    }
}