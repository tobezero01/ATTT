import java.util.Map;

/**
 * function
 */
public class function {

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
        //System.out.println("Xor:"+output);
        return output;
    }
    public static String shiftRowByte(String input,int i){
        String first=input.substring(0,i*2);
        String second=input.substring(i*2);
        return second+first;
    }
    public static String shiftRowBit(String input,int i){
        String second=input.substring(i);
        return second+'0';
    }
    public static String RotWord(String input){
        String firstWord="";
        firstWord=firstWord+input.substring(0,2);
        input=input.substring(2);
        input=input.concat(firstWord);
        // tách byte để thực hiện dịch vòng trái    
        return input;
    }
    public static String subByte(String input){
        String[] B=new String [input.length()/2];
        String output="";
        for(int i=0;i<B.length;i++){
            B[i]="";
            B[i]=B[i]+input.charAt(i*2)+input.charAt(i*2+1);
            int row=constanst.hexToDecimalMap.get(B[i].charAt(0));
            int col=constanst.hexToDecimalMap.get(B[i].charAt(1));
            output+=constanst.Sbox[row][col];
        }
        return output.toUpperCase();
    }
    public static String XorSubWordAndRcon(String input, int indexRC){
        String output="";
        String hexToBinary1=function.changeHexToBinary(input);
        String hexToBinary2=function.changeHexToBinary(constanst.Rcon[indexRC-1]);
        String outputXorRconAndOutputSub=xor(hexToBinary1,hexToBinary2);
        output=function.changeBinaryToHex(outputXorRconAndOutputSub);
        return output;
    }
    public static String XorTowInputOnWord(String input1,String input2){
        String hexToBinary1=function.changeHexToBinary(input1);
        String hexToBinary2=function.changeHexToBinary(input2);
        String outputXor=xor(hexToBinary1,hexToBinary2);
        outputXor=function.changeBinaryToHex(outputXor);
        return outputXor;
    }
    public static String[][] convertStringToMatric(String input){
        String[][] matrix = new String[4][4];
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[j][i] = input.substring(index, index + 2);// fill cột trước
                index += 2; // Tăng index để chuyển sang cặp ký tự tiếp theo trong input
            }
        }
        return matrix;
    }
    public static String mixColumnOne(String[][] matric,int m,int n){
        String output=function.multipTwoInput(constanst.mixColumnsMatrix[m][0],matric[0][n]);
        for(int i=1;i<4;i++){
            String resultOfmul=function.multipTwoInput(constanst.mixColumnsMatrix[m][i], matric[i][n]);
            output=function.XorTowInputOnWord(output,resultOfmul);
        }
        return output;
    }
    public static String multipTwoInput(String input1,String input2){
        // input là số input 2 là hex
        String output="";
        if(input1.equals("01")){
            output=input2;
        }
        else if(input1.equals("02")) {
            String hexToBinary=function.changeHexToBinary(input2);
            output=function.shiftRowBit(hexToBinary, 1);//i là số bit
            if(hexToBinary.charAt(0)=='1'){
                output=function.xor(output, "00011011");
            }
            output=function.changeBinaryToHex(output);
        }
        else if(input1.equals("03")){
            String hexToBinary=function.changeHexToBinary(input2);
            String output1=function.shiftRowBit(hexToBinary, 1);//i là số bit
            if(hexToBinary.charAt(0)=='1'){
                output1=function.xor(output1, "00011011");
            }
            output1=function.xor(output1, hexToBinary);
            
            output=function.changeBinaryToHex(output1);

        }
        return output;
    }
    public static void printString(String input) {
        System.out.println();
        String output="";
        for (int i = 0; i < input.length(); i++) {
            output+=input.charAt(i);
            if ((i + 1) % 8 == 0) {
                output+=' '; // Không cần sử dụng printf cho một ký tự
            }
        }
        System.out.println(output);
        System.out.println();
    }
}