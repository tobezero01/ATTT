package Des;

public class GenerateKey {
    public String inputKey;
    public String[] key;
    public String[] C,D;
    public GenerateKey(String input){
        this.inputKey=function.changeHexToBinary(input);
        System.out.println("input key: ");
        function.printString(inputKey);
        String permutedChoice1=function.permutedChoiceOne(inputKey);
        int middle=permutedChoice1.length()/2;
        key=new String[16];
        C=new String[17];
        D=new String[17];
        System.out.println("permutedChoiceOne");
        function.printString(permutedChoice1);
        C[0] = permutedChoice1.substring(0, middle);
        D[0] = permutedChoice1.substring(middle);
        System.out.println("C[0] ");
        function.printString(C[0]);
        System.out.println("D[0] ");
        function.printString(D[0]);
        for(int i=1;i<=16;i++){
            int bitsRoted= constanst.shiftLeft[i-1];
            C[i]=function.leftShift(C[i-1], bitsRoted);
            D[i]=function.leftShift(D[i-1], bitsRoted);
            String inputKey=C[i]+D[i];
            key[i-1]=function.permutedChoice2(inputKey);
        }
    }
    public void printfKey(){
        for(int i=0;i<key.length;i++){
            System.out.printf("C[%d]: ",i+1);
            function.printString(C[i+1]);
            System.out.printf(" C[%d]: ",i+1);
            function.printString(D[i+1]);
            System.out.printf(" Key[%d]: ",i+1);
            function.printString(key[i]);
        }
    }
}
