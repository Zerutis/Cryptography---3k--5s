package com.Zerutis;

import java.util.ArrayList;
import java.util.List;

public class StreamCipher {
    private List<Integer> keyStream = new ArrayList<>();

    public List<Integer> getKeyStream() { return keyStream; }

    public void fillKeyStream(int[] cipher, int length, char firstLetter, char secondLetter){
        keyStream.add(cipher[0] ^ firstLetter);
        keyStream.add(cipher[1] ^ secondLetter);
        int secretNumber = findSecret();

        for (int i = 2; i < length; i++) {
            int[] key = toArray(Integer.toBinaryString(keyStream.get(i-1)));
            int[] secretCode = toArray(Integer.toBinaryString(secretNumber));

            keyStream.add(
                    binaryToNumber(
                            createNewKey(key,secretCode))
            );
        }
    }

    public void clearKeyStream(){ keyStream.clear(); }

    public String streamCipher(int[] cipher){
        String answer = "";
        for(int i = 0; i < cipher.length; i++){
            int ASCII = cipher[i] ^ keyStream.get(i);
            answer += String.valueOf((char) ASCII);
        }
        return answer;
    }

    private int findSecret(){
        int secretNumber = 0;
        for (int i = 0; i < 255; i ++){
            int temp = binaryToNumber(
                    createNewKey(
                            toArray(Integer.toBinaryString(keyStream.get(0))),
                            toArray(Integer.toBinaryString(i)))
            );
            if (temp == keyStream.get(1)) {
                secretNumber = i;
                break;
            }
        }
        return secretNumber;
    }
    private int[] toArray(String binaryNum){
        char[] chars = binaryNum.toCharArray();
        int[] binary = new int[8];
        int i;

        if(binaryNum.length() != 8)
            i = 8 - binaryNum.length();
        else
            i = 0;

        for(char c : chars){
            binary[i++] = Integer.parseInt(String.valueOf(c));
        }
        return binary;
    }
    private int[] transform(int[] arr, int bit){
        int[] newArr = new int[8];
        for(int i = 0; i < arr.length-1; i++){
            newArr[i] = arr[i+1];
        }
        newArr[newArr.length-1] = bit;
        return newArr;
    }
    private int binaryToNumber(int[] arr){
        String number = "";
        for(int a : arr){
            number += Integer.toString(a);
        }
        return Integer.parseInt(number,2);
    }

    private int[] createNewKey(int[] key, int[] secretCode){
        int size = key.length;
        for (int j = 0; j < size; j++) {
            int bit = 0;
            for (int k = 0, l = size -1; k < size; k++, l--) {
                bit += secretCode[k] * key[l];
            }
            bit %= 2;
            key = transform(key, bit);
        }
        return key;
    }
}
