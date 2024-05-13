import java.util.ArrayList;

public class AES {
    private final int[][] state = new int[4][4]; // Ma trận 4x4 để lưu trữ thông điệp
    private final int[][] keyary = new int[4][4]; // Ma trận 4x4 để lưu trữ khóa

    private static final int[][] SBOX = {{0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5, 0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76},
            {0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0, 0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0},
            {0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc, 0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15},
            {0x04, 0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a, 0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75},
            {0x09, 0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0, 0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84},
            {0x53, 0xd1, 0x00, 0xed, 0x20, 0xfc, 0xb1, 0x5b, 0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf},
            {0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85, 0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8},
            {0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5, 0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2},
            {0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17, 0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73},
            {0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88, 0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb},
            {0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c, 0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79},
            {0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9, 0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08},
            {0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6, 0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a},
            {0x70, 0x3e, 0xb5, 0x66, 0x48, 0x03, 0xf6, 0x0e, 0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e},
            {0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94, 0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf},
            {0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68, 0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16}};
    private static final int[][] Mix = {{0x02, 0x03, 0x01, 0x01},
            {0x01, 0x02, 0x03, 0x01},
            {0x01, 0x01, 0x02, 0x03},
            {0x03, 0x01, 0x01, 0x02}};
    private static final int[] RCON = {0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36};

    // Phương thức thực hiện mã hóa AES
    private void AESEnc(){
        for(int i = 0; i < 11; i++){
            if (i == 0) addKey();
            else if(i == 10){
                byteSub();
                shiftRow();
                keySchedule(i);
                addKey();
            }
            else{
                byteSub();
                shiftRow();
                mixColumn();
                keySchedule(i);
                addKey();
            }
        }
    }

    // Phương thức thực hiện thay thế byte
    private void byteSub(){
        int hex;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++) {
                hex = state[j][i];
                state[j][i] = SBOX[hex / 16][hex % 16];
            }
        }
    }

    // Phương thức thực hiện dịch hàng
    private void shiftRow(){
        for(int i = 1; i < 4; i++){
            for(int j = 0; j < i; j++) shiftAry(i);
        }
    }

    // Phương thức dịch mảng hỗ trợ dịch byte
    private void shiftAry(int row){
        int temp = state[row][0];
        System.arraycopy(state[row], 1, state[row], 0, 3);
        state[row][3] = temp;
    }

    // Phương thức mixColumn
    private void mixColumn(){
        int[] tmp = new int[4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) tmp[j] = state[j][i];
            for (int k = 0; k < 4; k++) state[k][i] = mcCal(tmp, k);
        }
    }

    // Phương thức tính toán mixColumn cho từng phần tử
    private int mcCal(int a[], int i){
        int[] ary = new int[4];
        int hex = 0;
        int tmp = 0;
        for (int k = 0; k < 4; k++){
            hex = a[k];
            switch(Mix[i][k]){
                case 0x01:
                    ary[k] = hex;
                    break;
                case 0x02:
                    ary[k] = shift(hex);
                    break;
                case 0x03:
                    tmp = hex;
                    hex = shift(hex);
                    ary[k] = tmp ^ hex;
                    break;
            }
        }
        return ary[0] ^ ary[1] ^ ary[2] ^ ary[3];
    }

    // Phương thức dịch (1b)
    private int shift(int a){
        int hex = a;
        final int carry = 0x1b;
        hex = (hex << 1);
        if(hex > 255){
            hex -= 256;
            hex ^= carry;
        }
        return hex;
    }

    // Phương thức lập lịch khóa
    private void keySchedule(int rc){
        int[] gen = new int[4];
        for (int i = 0; i < 4; i++) gen[i] = keyary[i][3];

        int temp = gen[0];
        System.arraycopy(gen, 1, gen, 0, 3);
        gen[3] = temp;

        int hex;
        for (int i = 0; i < 4; i++) {
            hex = gen[i];
            gen[i] = SBOX[hex / 16][hex % 16];
        }
        gen[0] ^= RCON[rc];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(i == 0) keyary[j][0] ^= gen[j];
                else keyary[j][i] ^= keyary[j][i - 1];
            }
        }
    }



    // Phương thức thêm khóa
    private void addKey(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++) state[j][i] ^= keyary[j][i];
        }
    }

    // Phương thức chuyển đổi từ hex sang chuỗi
    private String HextoString(){
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(state[j][i] < 16) result.append(0);
                result.append(Integer.toHexString(state[j][i]));
            }
        }
        return result.toString();
    }

    // Phương thức mã hóa AES
    public String encryptAES(String msg, String keys){

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                state[j][i] = Integer.parseInt(msg.substring((8 * i) + (2 * j), (8 * i) + (2 * j + 2)), 16);
                keyary[j][i] = Integer.parseInt(keys.substring((8 * i) + (2 * j), (8 * i) + (2 * j + 2)), 16);
            }
        }
        AESEnc();
        return HextoString().toUpperCase();
    }

    private int[] getKey7() {
        int[][] expandedKeys = new int[11][4 * 4];
        for (int i = 0; i < 11; i++) {
            keySchedule(i);
            for (int j = 0; j < 4; j++) {
                System.arraycopy(keyary[j], 0, expandedKeys[i], j * 4, 4);
            }
        }
        return expandedKeys[5];
    }
    public static void main(String[] args) {
        AES aes = new AES();
        String plaintext = "18DC9095F9149EDB7323F20E4E462D92"; // Hex, 128 bit
        String key = "CFD61D489E7C48BC46C9F875C1F04E1B"; // Hex, 128 bit
        System.out.println("Mã hóa C = "+ aes.encryptAES(plaintext, key));


        int[] key7 = aes.getKey7();
        //String str = new String();
        ArrayList<String>  s = new ArrayList<>();
        System.out.print("W25 : ");
        for (int i = 4; i < 8; i++) {

            System.out.print(String.format("%02X", key7[i]));
        }

        System.out.println();
        for (int i = 0; i < 16; i++) {

            System.out.print(String.format("%02X", key7[i]));
        }
        System.out.println();
    }
}
