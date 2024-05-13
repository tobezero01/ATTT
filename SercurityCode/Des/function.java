package Des;

import java.util.Map;

public class function {
    public static String changeHexToBinary(String input){// cần thêm từ khóa static vào vì nếu muốn gọi các hàm trong đây thì cần phải khởi tạo 1 đối tượng
        String output="";// dùng string builder để thuận tiện cho việc cộng chuỗi
        for(int i=0;i<input.length();i++){
            String hexCharacter = String.valueOf(input.charAt(i)).toUpperCase(); // Chuyển đổi ký tự hex sang chữ hoa để đảm bảo nằm trong hexMap
            String binaryEquivalent = constanst.hexMap.get(hexCharacter);
            if (binaryEquivalent != null) {
                output+=binaryEquivalent.toString();
            } else {
                System.err.println("Invalid hex character: " + hexCharacter);
            }
        }
        return output;
    }
    public static String changeBinaryToHex(String input){
        String output="";
        Map<String,String> hexMap=constanst.hexMap;
        for(int i=0;i<input.length();i+=4){
            String input_4_Bit="";
            for(int j=i;j<i+4;j++){
                input_4_Bit+=input.charAt(j);

            }
            for (Map.Entry<String, String> oneMap : hexMap.entrySet()) {
                if (oneMap.getValue().equals(input_4_Bit)) {
                    output += oneMap.getKey();
                    break; // Bạn có thể dừng vòng lặp ngay khi tìm thấy giá trị
                }
            }
        }
        return output;
    }
    public static int changeBinaryToDec(String input){
        int length=input.length();
        int dec=0;
        for(int i=0;i<length;i++){
            char c = input.charAt(length - i - 1); // Lấy ký tự ngược lại từ phải sang trái
            int binaryChar = Character.getNumericValue(c); // Chuyển ký tự thành số nguyên
            dec += binaryChar * Math.pow(2, i); // Cộng giá trị của bit vào dec
        }
        return dec;
    }
    public static String changeDecToBinary(int input){
        String tmp="",output="";
        while (input!=0) {
            tmp+= String.valueOf(input%2);
            input/=2;
        }
        while (tmp.length() < 4) {
            tmp += "0";
        }

            for(int i=tmp.length()-1;i>=0;i--){
                output+=tmp.charAt(i);
            }
        // System.out.println("out put dec to binaryChar ");
        // printString(output);
        return output;
    }
    public static String permutedChoiceOne(String input){
        String output="";
        int[] permutedChoice1=constanst.permutedChoice1;
        for(int i=0;i<permutedChoice1.length;i++){
            output+=input.charAt(permutedChoice1[i]-1);
        }
        return output;
    }
    public static String leftShift(String input,int bitsRoted){
        String output="";
        int length=input.length();
        for(int i=0;i<length;i++){
            output+=input.charAt((i+bitsRoted)%28);
        }
        return output;
    }
    public static String permutedChoice2(String input){
        String output="";
        int[] permutedChoice2=constanst.permutedChoice2;
        for(int i=0;i<permutedChoice2.length;i++){
            output+=input.charAt(permutedChoice2[i]-1);// vì vị trí trong string chỉ đến 55 nếu không trừ 1 sẽ lỗi
        }
        return output;
    }
    public static String initialPermutation(String inputM){
        String output="";
        int[] initialPermutation=constanst.initialPermutation;
        for(int i=0;i<initialPermutation.length;i++){
            output+=inputM.charAt(initialPermutation[i]-1);
        }
        return output;
    }
    public static String expansionPermutation(String input){
        String output="";
        int[] expansionPermutation=constanst.expansionPermutation;
        int lengthExpan=expansionPermutation.length;
        for(int i=0;i<lengthExpan;i++){
            output+=input.charAt(expansionPermutation[i]-1);
        }
        System.out.println("outExpansion: ");
        printString(output);
        return output;
    }
    public static String xor(String input1,String input2){
        String output="";
        int length=input1.length();
        for(int i=0;i<length;i++){
            if(input1.charAt(i)!=input2.charAt(i)){
                output+='1';
            }
            else    output+='0';
        }
        System.out.println("Xor:"+output);
        return output;
    }
    public static String sBox(String input,int i){
        int[][] sbox= constanst.sBoxes.get(i);
        int length=input.length();
        String rowBinary="",colBinary="";
        rowBinary=rowBinary+input.charAt(0)+input.charAt(length-1);
        colBinary=colBinary+input.substring(1, length-1);
      //  System.out.println("rowBinary: "+rowBinary);
       // System.out.println("colBinary: "+colBinary);
        int rowDec=changeBinaryToDec(rowBinary);
        int colDec=changeBinaryToDec(colBinary);
        int dec=sbox[rowDec][colDec];
        String output=changeDecToBinary(dec);
        return output;


    }
    public static String substitionSbox(String inputKey,String inputR){
        String output="";
        String outExpansion=expansionPermutation(inputR);
        String outputXorKeyAndR=function.xor(inputKey, outExpansion);
        for (int i = 0; i < outputXorKeyAndR.length(); i += 6) {
            String inputSbox = "";
            System.out.println(i/6);
            for (int j = i; j < i + 6; j++) { // Sửa i thành j ở đây
                inputSbox += outputXorKeyAndR.charAt(j);
            }
            output += sBox(inputSbox, i / 6);
        }
        return output;
    }
    public static String permutationFunction(String input){
        String output="";
        int[] permutation=constanst.permutationFunction;
        int length=permutation.length;
        for(int i=0;i<length;i++){
            output+= input.charAt(permutation[i]-1);
        }
        return output;
    }
    public static String inReverseInitialPermutation(String input){
        String output="";
        int[] reverse=constanst.inverseInitialPermutation;
        int length=reverse.length;
        for(int i=0;i<length;i++){
            output+=input.charAt(reverse[i]-1);
        }
        return output;
    }
    public static void printString(String input) {
        System.out.println();
        String output="";
        for (int i = 0; i < input.length(); i++) {
            output+=input.charAt(i);
            if ((i + 1) % 4 == 0) {
                output+=' '; // Không cần sử dụng printf cho một ký tự
            }
        }
        System.out.println(output);
        System.out.println();
    }
}
