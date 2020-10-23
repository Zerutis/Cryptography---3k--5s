package com.Zerutis;

public class Main {
    static int[][] EBC = {
            {61, 157}, {39, 154}, {41, 152}, {56, 141}, {39, 151}, {52, 143}, {34, 155}, {61, 139}, {39, 154}, {53, 136},
            {39, 154}, {62, 143}, {60, 155}, {47, 144}, {60, 141}, {39, 131}, {43, 136}, {59, 143}, {60, 141}, {44, 157},
            {44, 151}, {61, 153}, {60, 157}, {34, 157}, {44, 145}, {35, 158}, {53, 136}, {61, 155}, {39, 157}, {37, 132},
            {37, 131}, {39, 135}, {41, 131}, {35, 158}, {35, 157}, {39, 129}, {39, 128}, {50, 139}, {53, 138}, {41, 144},
            {43, 146}, {42, 147}, {44, 151}, {32, 133}, {61, 139}, {47, 151}, {47, 145}, {41, 151}, {59, 152}, {39, 129},
            {42, 155}, {60, 140}, {47, 142}, {33, 152}, {47, 142}, {61, 156}, {37, 148}, {42, 147}, {91, 203}};

    static int[][] CBC = {
            {87, 202}, {36, 76}, {69, 241}, {10, 73}, {101, 209}, {2, 112}, {69, 205}, {40, 80}, {93, 240}, {9, 68},
            {98, 209}, {3, 99}, {87, 203}, {53, 67}, {86, 239}, {25, 77}, {126, 195}, {0, 102}, {66, 199}, {37, 69},
            {64, 231}, {19, 73}, {104, 218}, {19, 117}, {84, 212}, {51, 68}, {77, 248}, {25, 66}, {126, 222}, {29, 101},
            {71, 211}, {57, 95}, {69, 227}, {2, 95}, {107, 215}, {14, 127}, {85, 196}, {63, 74}, {74, 224}, {3, 88},
            {118, 206}, {23, 108}, {93, 211}, {58, 83}, {68, 246}, {17, 94}, {126, 198}, {25, 124}, {68, 197}, {54, 78},
            {85, 252}, {30, 88}, {125, 214}, {10, 122}, {77, 208}, {42, 89}, {69, 254}, {6, 94}, {107, 204}, {12, 97},
            {75, 204}, {32, 70}, {79, 240}, {8, 70}, {123, 193}, {0, 97}, {85, 201}, {62, 70}, {91, 236}, {28, 72},
            {115, 201}, {0, 106}, {75, 203}, {32, 87}, {71, 241}, {0, 82}, {123, 220}, {15, 110}, {68, 208}, {47, 91},
            {72, 240}, {15, 95}, {96, 202}, {21, 127}, {90, 197}, {46, 86}, {77, 227}, {12, 77}, {101, 207}};

    static int[][] CFB = {
            {8, 194}, {48, 81}, {12, 252}, {5, 75}, {38, 212}, {9, 107}, {10, 220}, {37, 75}, {1, 247}, {27, 77},
            {58, 193}, {24, 104}, {13, 201}, {34, 70}, {13, 245}, {2, 84}, {56, 205}, {20, 98}, {19, 207}, {32, 73},
            {20, 250}, {8, 81}, {39, 202}, {17, 122}, {7, 203}, {32, 73}, {31, 232}, {16, 87}, {59, 209}, {5, 113},
            {27, 198}, {60, 70}, {7, 247}, {2, 75}, {55, 210}, {12, 116}, {8, 195}, {47, 83}, {0, 240}, {29, 67},
            {54, 211}, {14, 122}, {12, 203}, {36, 66}, {3, 245}, {8, 64}, {39, 213}, {0, 121}, {12, 192}, {35, 95},
            {21, 239}, {3, 77}, {54, 200}, {17, 124}, {14, 204}, {33, 65}, {6, 232}, {20, 81}, {63, 208}, {27, 120},
            {9, 212}, {42, 70}, {16, 239}, {5, 68}};

    static int[][] CTR = {
            {248, 199}, {242, 214}, {253, 223}, {232, 221}, {240, 219}, {253, 199}, {242, 215}, {232, 198}, {245, 214},
            {232, 219}, {245, 216}, {238, 219}, {239, 209}, {253, 202}, {249, 200}, {234, 211}, {249, 220}, {253, 198},
            {243, 217}, {245, 208}, {249, 222}, {249, 217}, {245, 211}, {242, 198}, {232, 200}, {245, 213}, {247, 201},
            {232, 211}, {240, 207}, {230, 213}, {241, 207}, {239, 207}, {234, 247}, {230, 251}, {241, 243}, {239, 249},
            {249, 240}, {249, 224}, {245, 253}, {247, 225}, {232, 240}, {233, 234}, {249, 245}, {245, 245}, {245, 241},
            {253, 233}, {232, 251}, {248, 251}, {248, 247}, {246, 253}, {239, 251}};


    public static void main(String[] args) {
        System.out.println(feistelCipherDecoderEBC(new int[]{218,64,180}));
        System.out.println(feistelCipherDecoderCBC(new int[]{218,64,180}, new int[]{30,107}));
        System.out.println(feistelCipherDecoderCFB(new int[]{218,64,180}, new int[]{30,107}));
        System.out.println(feistelCipherDecoderCTR(new int[]{218,64,180}));
    }

    static int func(int m, int k) { return (m|k)^((k/16)&m); }


    static String feistelCipherDecoderEBC(int[] key){
        String decoded = "";
        for (int[] e : EBC) {
            int[] decryption = feistelCipherDecoder(e, key, true);
            decoded += String.valueOf((char) decryption[0]) + String.valueOf((char) decryption[1]);
        }
        return decoded;
    }
    static String feistelCipherDecoderCBC(int[] key, int[] iv){
        String decoded = "";
        for (int[] c : CBC) {
            int[] decryption = feistelCipherDecoder(c, key, true);
            decryption[0] ^= iv[0];
            decryption[1] ^= iv[1];
            decoded += String.valueOf((char) decryption[0]) + String.valueOf((char) decryption[1]);
            iv = c;
        }
        return decoded;
    }
    static String feistelCipherDecoderCFB(int[] key, int[] iv){
        String decoded = "";
        for (int[] c : CFB) {
            int[] decryption = feistelCipherEncoder(iv, key, true);
            int let1 = decryption[0] ^ c[0];
            int let2 = decryption[1] ^ c[1];
            decoded += String.valueOf((char) let1) + String.valueOf((char) let2);
            iv = c;
        }
        return decoded;
    }
    static String feistelCipherDecoderCTR(int[] key){
        String decoded = "";
        int[] nonceCounter = {0,0};

        for (int i =0; i < CTR.length; i++) {
            nonceCounter[0] = func(i,key[0]);
            nonceCounter[1] = func(i,key[0]);

            int[] decryption = feistelCipherEncoder(nonceCounter, key, true);
            int let1 = decryption[0] ^ CTR[i][0];
            int let2 = decryption[1] ^ CTR[i][1];

            decoded += String.valueOf((char) let1) + String.valueOf((char) let2);
            nonceCounter[1]++;
        }
        return decoded;
    }

    static int[] feistelCipherDecoder(int[] cipher, int[] key, boolean changeLast) {
        int L = cipher[0];
        int R = cipher[1];
        for (int i = key.length-1; i >= 0; i--) {
            int T = L;
            L = R;
            R = T ^ func(L, key[i]);

            if(i == 0 && changeLast){
                T = L;
                L = R;
                R = T;
            }
        }
        return new int[]{L,R};
    }
    static int[] feistelCipherEncoder(int[] cipher, int[] key, boolean changeLast) {
        int L = cipher[0];
        int R = cipher[1];
        for (int i = 0; i < key.length; i++) {
            int T = L;
            L = R;
            R = T ^ func(L, key[i]);

            if(i == key.length-1 && changeLast){
                T = L;
                L = R;
                R = T;
            }
        }
        return new int[]{L,R};
    }
}


