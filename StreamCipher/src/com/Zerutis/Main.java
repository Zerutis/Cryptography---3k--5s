package com.Zerutis;

import java.util.ArrayList;
import java.util.List;

public class Main {

     static int[] cipher =  {222, 169, 170, 97, 22, 146, 109, 24, 126, 232,
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


    public static void main(String[] args) {
        StreamCipher streamCipher = new StreamCipher();
        // First part
        streamCipher.fillKeyStream(cipher, cipher.length,'T','U');
        System.out.println(streamCipher.streamCipher(cipher));
        // Second part
    }
}

