
/**
 * Aes
 */
public class Aes {
    public String[] key;
    public String[] roundState;
    public String inputKey;
    public String inputM;
    public String[] words;
    public Aes(String inputK,String input){
        this.key=new String[11];
        this.words=new String[45];
        this.roundState=new String[11];
        this.inputM=input;
        this.inputKey=inputK;
        key[0]=inputK;
        System.out.println(inputKey);
        System.out.println(inputM);
        roundState[0]=addRoundKey(key[0],this.inputM);
        System.out.println("roundState 0 : ");
        printString(roundState[0]);
        System.out.println("output subByte on Round 0 ");
        printString(function.subByte(roundState[0]));
    }
    public String addRoundKey(String input1,String input2){// đúng
        String output;
        System.out.println("addRoundKey");
        output=function.XorTowInputOnWord(input1,input2);
        return output;
    };// đúng
    // hàm subyte trong trong file funtion
    public String shiftRowOnRound(String input){// đúng
        String output="";
        String[][] A=new String[4][4];
        String[][] B=new String[4][4];
        A=function.convertStringToMatric(input);
        for(int i=0;i<4;i++){
            String oneRow="";
            for(int j=0;j<4;j++){
                oneRow+=A[i][j];
            }
            output+=function.shiftRowByte(oneRow, i);
        }
        B=function.convertStringToMatric(output);
        output="";
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                output+=B[i][j];
            }
        }
        return output;
    };
    public String mixColumn(String input){// đúng
        String output="";
        String[][] B=new String[4][4];
        String[][] mixColumn=new String[4][4];
        mixColumn=function.convertStringToMatric(input);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                output+= function.mixColumnOne(mixColumn, i, j);
            }
        }
        B=function.convertStringToMatric(output);
        output="";
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                output+=B[i][j];
            }
        }
        return output;
    }
    public void expandKey(String inputKey){// đúng
        String temp;
        for(int i=0;i<4;i++){
            words[i]="";
            words[i]=words[i]+inputKey.charAt(i*8)+inputKey.charAt(i*8+1)+inputKey.charAt(i*8+2)+inputKey.charAt(i*8+3)+inputKey.charAt(i*8+4)+inputKey.charAt(i*8+5)+inputKey.charAt(i*8+6)+inputKey.charAt(i*8+7);
        }
        System.out.println(words[0]);
        System.out.println(words[1]);
        System.out.println(words[2]);
        System.out.println(words[3]);
        for(int i=4;i<44;i++){
            if(key[i/4]==null)
                key[i/4]="";
            temp=words[i-1];
            
            if(i%4==0){
                // hàm g
                String outputRotWord=function.RotWord(temp);
                String outputSubWord=function.subByte(outputRotWord);
                temp=function.XorSubWordAndRcon(outputSubWord, i/4);
            }
            words[i]=function.XorTowInputOnWord(temp, words[i-4]);
            System.out.printf("words[%d]    =%s%n",i,words[i]);
            key[i/4]+=words[i];
        }
    }
    public void printfKey(){
        for (int i = 0; i < key.length; i++) {
            String string = key[i];
            System.out.printf("Key %d la: ", i);
            printString(string);
        }
    }
    public void excuteRound(){
        System.out.printf("key on Round %d is: ",0);
        printString(key[0]);
        System.out.printf("roundState on Round %d is: ",0);
        printString(roundState[0]);
        for(int i=1;i<=10;i++){
            String outputSubByteOnRound=function.subByte(roundState[i-1]);//1
            System.out.printf("outputSubByteOnRound on Round %d is: ",i);
            printString(outputSubByteOnRound);
            String outputShiftRowOnRound=shiftRowOnRound(outputSubByteOnRound);//2
            String outMixColumn=outputShiftRowOnRound;
            System.out.printf("outputShiftRowOnRound on Round %d is: ",i);
            printString(outputShiftRowOnRound);
            if(i!=10){
                outMixColumn=mixColumn(outputShiftRowOnRound);//3
                System.out.printf("outMixColumn on Round %d is: ",i);
                printString(outMixColumn);
            }
            System.out.printf("key on Round %d is: ",i);
            printString(key[i]);
            roundState[i]=addRoundKey(outMixColumn, key[i]);//4
            System.out.printf("roundState on Round %d is: ",i);
            printString(roundState[i]);
        }
        System.out.println("Output: ");
        printString(roundState[10]);

    }
    public void printString(String input) {
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