/**
 * main
 */
public class main {

    public static void main(String[] args) {
        // String inputKey="0f1571c947d9e8590cb7add6af7f6798";                
        // String inputM="0123456789abcdeffedcba9876543210";  
        

        String inputKey="CFD61D489E7C48BC46C9F875C1F04E1B";
        String inputM="18DC9095F9149EDB7323F20E4E462D92";

        inputKey=inputKey.toUpperCase();
        inputM=inputM.toUpperCase();
        Aes aes=new Aes(inputKey, inputM);
        aes.expandKey(inputKey);
        aes.printfKey();
        //aes.shiftRowOnRound(testShiftRow);
        aes.excuteRound();
    }
}