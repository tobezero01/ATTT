package Des;

import java.security.PublicKey;
import Des.function;

public class Des {
    public String inputM;
    public String inputKey;
    public String[] L,R;
    public GenerateKey key;
    public Des(String inputM, String inputKey) {
        this.inputM = function.changeHexToBinary(inputM);
        System.out.println("InputBinary: "+this.inputM);
        this.inputKey=inputKey;
        L=new String[17];
        R=new String[17];
        key=new GenerateKey(this.inputKey);
        
    }
    public String cryptToDes(){
        String initialPermutation=function.initialPermutation(inputM);
        System.out.println("initialPermutation ");
        function.printString(initialPermutation);
        int middle=initialPermutation.length()/2;
        L[0] = initialPermutation.substring(0, middle);
        R[0] = initialPermutation.substring(middle);
        System.out.println("L0 ");
        function.printString(L[0]);
        System.out.println("R0 "+ R[0]);
        function.printString(R[0]);
        for(int i=1;i<=16;i++){
            L[i]=R[i-1];
            // trong hàm sub có hàm hoán vị e rồi
            String susubstitionSboxb=function.substitionSbox(key.key[i-1],R[i-1]);
            System.out.printf("substitionSbox %d: ",i);
            function.printString(susubstitionSboxb);
            String outPermutation=function.permutationFunction(susubstitionSboxb);
            System.out.printf("outPermutation Function %d: ",i,outPermutation);
            function.printString(outPermutation);
            R[i]=function.xor(L[i-1], outPermutation);
            System.out.println("L i R i");
            System.out.printf("L %d: ",i-1);
            function.printString(L[i-1]);
            System.out.printf("R %d: ",i-1);
            function.printString(R[i-1]);
            System.out.printf("L %d: ",i);
            function.printString(L[i]);
            System.out.printf("R %d: ",i);
            function.printString(R[i]);
        }
        String out_32_BitSwap=R[16]+L[16];
        String output=function.inReverseInitialPermutation(out_32_BitSwap);
        System.out.println("output inverseInitialPermutation ");
        function.printString(output);
        return function.changeBinaryToHex(output);
    }

    
}
