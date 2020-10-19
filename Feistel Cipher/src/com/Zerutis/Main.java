package com.Zerutis;

public class Main {
    static int[][] cipher1 = {
            {26, 79}, {18, 73}, {9, 65}, {11, 93}, {26, 65}, {15, 77}, {6, 65}, {9, 65}, {9, 65}, {11, 92},
            {5, 65}, {11, 93}, {28, 77}, {5, 73}, {29, 94}, {12, 65}, {23, 90}, {17, 76}, {13, 92}, {29, 90},
            {7, 66}, {25, 88}, {9, 70}, {13, 91}, {9, 69}, {30, 65}, {26, 65}, {13, 92}, {11, 91}, {17, 88},
            {9, 70}, {13, 92}, {23, 91}, {13, 79}, {17, 90}, {29, 73}, {17, 91}, {21, 82}, {6, 71}, {27, 77},
            {29, 90}, {13, 79}, {7, 66}, {17, 91}, {20, 91}, {19, 81}, {12, 91}, {11, 93}, {26, 73}, {9, 74},
            {12, 91}, {30, 71}, {2, 77}, {9, 71}, {19, 93}, {12, 91}, {22, 81}, {7, 67}, {7, 66}, {1, 69},
            {26, 91}, {4, 77}, {13, 65}, {13, 69}, {27, 70}};

    static int[][] cipher2 = {
            {241, 173}, {226, 191}, {255, 168}, {249, 189}, {241, 170}, {247, 182}, {234, 183}, {247, 166}, {255, 167}, {241, 179},
            {243, 172}, {242, 167}, {253, 181}, {249, 174}, {253, 168}, {251, 164}, {238, 181}, {247, 162}, {245, 179}, {250, 175},
            {253, 173}, {255, 174}, {224, 184}, {231, 184}, {235, 163}, {252, 175}, {249, 177}, {242, 175}, {239, 176}, {231, 184},
            {245, 176}, {228, 187}, {247, 166}, {250, 182}, {234, 181}, {253, 172}, {228, 162}, {250, 189}, {231, 168}, {225, 189},
            {253, 174}, {239, 180}, {242, 175}, {253, 185}, {235, 182}, {248, 161}, {243, 166}, {239, 176}, {231, 184}, {253, 176},
            {241, 179}, {241, 169}, {255, 179}, {238, 160}, {239, 176}, {235, 163}, {231, 184}, {253, 177}, {247, 167}, {227, 186}};

    static int[][] cipher3 = {
            {5, 71}, {6, 74}, {14, 90}, {27, 73}, {29, 95}, {5, 77}, {11, 73}, {11, 69}, {29, 76}, {6, 79},
            {10, 89}, {14, 64}, {5, 75}, {6, 82}, {31, 84}, {6, 82}, {11, 77}, {25, 81}, {31, 93}, {29, 76},
            {6, 82}, {14, 67}, {0, 73}, {0, 84}, {6, 72}, {29, 73}, {4, 81}, {26, 82}, {28, 79}, {26, 78},
            {27, 83}, {4, 80}, {25, 91}, {6, 69}, {29, 85}, {11, 69}, {28, 87}, {14, 75}, {14, 64}, {11, 86},
            {6, 84}, {8, 70}, {1, 73}, {21, 75}, {1, 79}};


    public static void main(String[] args) {
        System.out.println(feistelCipherDecoder1());
        System.out.println(feistelCipherDecoder2(162)); // 162

        for(int i = 0; i < 256; i++){
            for( int j =0; j < 256; j++){
                String answer = feistelCipherDecoder3(i,j,"J", "E"); // 200 ir 39
                if(answer.length() > 0)
                    System.out.println(answer + " " + i + " " + j);
            }
        }
    }

    static int func1(int m, int k) {
        return (m&k)^((k%16)|m);
    }

    static int func2(int m, int k){
        return (m|k)^((k/16)&m);
    }

    static int func3(int m, int k){
        return (m&k)^((k%16)|m);
    }

    static String feistelCipherDecoder1(){
        int[] key = {133,45,245};
        String decoded = "";
        for(int i = 0; i < cipher1.length; i++){
            int L0 = cipher1[i][0];
            int R0 = cipher1[i][1];
            int L1 = R0;
            int R1 = L0 ^ func1(R0, key[2]);
            int L2 = R1;
            int R2 = L1 ^ func1(R1, key[1]);
            int L3 = R2;
            int R3 = L2 ^ func1(R2, key[0]);

            decoded += String.valueOf((char) R3) + String.valueOf((char) L3);
        }
        return decoded;
    }
    static String feistelCipherDecoder2(int firstKey){
        int secondKey = 156;
        String decoded = "";
        for(int i = 0; i < cipher2.length; i++){
            int L0 = cipher2[i][0];
            int R0 = cipher2[i][1];
            int L1 = R0;
            int R1 = L0 ^ func2(R0, secondKey);
            int L2 = R1;
            int R2 = L1 ^ func2(R1, firstKey);

            decoded += String.valueOf((char) R2) + String.valueOf((char) L2);
        }
        return decoded;
    }
    static String feistelCipherDecoder3(int firstKey, int secondKey, String firstLetter, String secondLetter){
        String decoded = "";
        for(int i = 0; i < cipher3.length; i++){
            int L0 = cipher3[i][0];
            int R0 = cipher3[i][1];
            int L1 = R0;
            int R1 = L0 ^ func3(R0, secondKey);
            int L2 = R1;
            int R2 = L1 ^ func3(R1, firstKey);

            String L = String.valueOf((char) R2);
            String R = String.valueOf((char) L2);
            if(i == 0 && (!L.equals(firstLetter) || !R.equals(secondLetter))){
                return "";
            }

            decoded += L + R;
        }
        return decoded;
    }
}
