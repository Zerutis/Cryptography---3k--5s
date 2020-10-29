package com.Zerutis;


import java.util.ArrayList;
import java.util.List;

public class Main {

    static int[] cipher1 =  {222, 169, 170, 97, 22, 146, 109, 24, 126, 232,
            136, 174, 1, 49, 65, 156, 34, 213, 168, 56, 37,
            200, 62, 118, 243, 120, 39, 145, 242, 170, 32,
            216, 84, 170, 185, 2, 209, 197, 23, 237, 46, 6,
            217, 189, 210, 191, 93, 225, 131, 94, 135, 169,
            134, 106, 155, 53, 59, 62, 172, 252, 39, 142, 138,
            111, 127, 178, 171, 228, 67, 81, 198, 7, 139, 251,
            76, 189, 64, 139, 88, 20, 252, 69, 208, 129, 211,
            32, 228, 164, 161, 139, 130, 1, 187, 248, 213, 7,
            28, 181, 148, 24, 90, 116, 65};

    static int[] A5_1 = {129, 120, 243, 125, 51, 135, 239, 187, 51, 208,
            95, 182, 180, 21, 195, 197, 22, 240, 38, 27, 202,
            160, 193, 165, 67, 233, 158, 88, 152, 174, 128, 105,
            158, 58, 34, 35, 181, 236, 37, 138, 138, 121, 106,
            185, 175, 239, 64, 75, 192, 5, 140, 248, 84, 187,
            84, 151, 64, 21, 251, 68, 197, 138, 217, 38, 226,
            179, 161, 157, 153, 29, 171, 240, 213, 24, 22, 165,
            128, 11, 90, 107, 91, 204, 250, 31, 113, 157, 89,
            233, 104, 202};


    static List<Integer> keyStream = new ArrayList<>();

    public static void main(String[] args) {
        fillKeyStream(cipher1.length);
        System.out.println(keyStream);
        System.out.println(streamCipher());
        clearKeyStream();
    }

    static void fillKeyStream(int length){
        keyStream.add(138);
        keyStream.add(252);

        for (int i = 2; i < length; i++) {
            int[] key = toArray(Integer.toBinaryString(keyStream.get(i-1)));
            int[] cipher = toArray(Integer.toBinaryString(cipher1[i-1]));
            int size = cipher.length - 1;

           for (int j = 0; j < size; j++) {
                int bit = 0;
                for (int k = 0, l = size; k <= size; k++, l--) {
                    bit += cipher[k] * key[l];
                }
                bit %= 2;
                key = transform(key, bit);
            }
            keyStream.add(binaryToNumber(key));
        }
    }

    static int[] toArray(String binaryNum){
        char[] chars = binaryNum.toCharArray();
        int[] binary = new int[8];
        int i;

        if(binaryNum.length() != 8)
            i = 7 - binaryNum.length();
        else
            i = 0;

        for(char c : chars){
            binary[i++] = Integer.parseInt(String.valueOf(c));
        }
        return binary;
    }
    static int[] transform(int[] arr, int bit){
        for(int i = 0; i < arr.length-1; i++){
            arr[i] = arr[i+1];
        }
        arr[arr.length-1] = bit;
        return arr;
    }
    static int binaryToNumber(int[] arr){
        String number = "";
        for(int a : arr){
            number += Integer.toString(a);
        }
        return Integer.parseInt(number,2);
    }

    static void clearKeyStream(){ keyStream.clear(); }

    static String streamCipher(){
        String answer = "";
        for(int i = 0; i < cipher1.length; i++){
            int ASCII = cipher1[i] ^ keyStream.get(i);
            answer += String.valueOf((char) ASCII);
        }
        return answer;
    }
}

