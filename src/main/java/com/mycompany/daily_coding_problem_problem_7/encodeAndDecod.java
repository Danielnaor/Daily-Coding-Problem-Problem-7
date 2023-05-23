/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.daily_coding_problem_problem_7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Danielnaor
 */

/* 
   Given the mapping a = 1, b = 2, ... z = 26, and an encoded message, count the number of ways it can be decoded.
   For example, the message '111' would give 3, since it could be decoded as 'aaa', 'ka', and 'ak'.
   You can assume that the messages are decodable. For example, '001' is not allowed. 
 */
public class encodeAndDecod {

    private HashMap<String, Integer> lettersToNum;
    private HashMap<Integer, String> numToletters;

    public encodeAndDecod() {
        buildMaps();
    }

    public void buildMaps() {
        try {

            numToletters = new HashMap<>();
            lettersToNum = new HashMap<>();
            Scanner file = new Scanner(new File("Data" + File.separator + "NumToLetter.txt"));
            while (file.hasNextLine()) {
                String lineData = file.nextLine();

                Scanner line = new Scanner(lineData);
                line.useDelimiter("\t");
                int num = Integer.parseInt(line.next());
                String letter = line.next();
                numToletters.put(num, letter);
                lettersToNum.put(letter, num);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(encodeAndDecod.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int decodeMessage(String message) {
        try {
            String[] letters = message.split("");
            String messageAsString = "";
            for (String letter : letters) {

                if (lettersToNum.containsKey(letter)) {
                    messageAsString += lettersToNum.get(letter);

                } else {
                    System.out.println("unknown char");
                }
            }

            return Integer.parseInt(messageAsString);
        } catch (Exception e) {
            System.out.println("failed to decode");
        }

        return 0;

    }

    public int countNumWayCanBeDecoded(String message) {

        return countNumWayCanBeDecoded(message.toCharArray(), message.length());
    }

    private int countNumWayCanBeDecoded(char[] chars, int len) {
        //base case

        if (len == 1 || len == 0) {
            return 1;
        }
        int count = 0;

        if (chars[len - 1] > '0') {
            count += countNumWayCanBeDecoded(chars, len - 1);
        }

        if (chars[len - 2] == '1' || (chars[len - 2] == '2' && chars[len - 1] <= '6')) {
            count += countNumWayCanBeDecoded(chars, len - 2);
        }

        return count;
    }

}
