package resources;

import java.io.IOException;

public class Utils {

    
    public static String upperCaseWords(String sentence) {
        String words[] = sentence.replaceAll("\\s+", " ").trim().split(" ");
        String newSentence = "";
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                newSentence = newSentence + ((i == 0) ? word.substring(i, i + 1).toUpperCase()
                        : (i != word.length() - 1) ? word.substring(i, i + 1).toLowerCase() : word.substring(i, i + 1).toLowerCase().toLowerCase() + " ");
            }
        }

        return newSentence.trim();
    }

    //Removes a used value from the array
    public static int[] removeInt(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                int[] copy = new int[array.length - 1];
                System.arraycopy(array, 0, copy, 0, i);
                System.arraycopy(array, i + 1, copy, i, array.length - i - 1);
                return copy;
            }
        }
        return array;
    }

    public static int[] arrayIntPush(int item, int[] oldArray) {
        int len = oldArray.length;
        int[] newArray = new int[len + 1];
        System.arraycopy(oldArray, 0, newArray, 0, len);
        newArray[len] = item;

        return newArray;
    }

    public static void addRows(int value) {
        for (int i = 0; i < value; i++) {
            System.out.println("");
        }
    }

}
