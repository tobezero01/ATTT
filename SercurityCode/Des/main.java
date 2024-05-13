package Des;
public class main {
    public static void main(String[] args) {
        String inputM="D8B8217DA16D5B5F";          //"B71127D233E316C3";
        String inputKey ="B71127D233E316C3";       //"E35CB18E63EEED18";
        // String inputM="02468ACEECA86420";          //"B71127D233E316C3";
        // String inputKey ="0F1571C947D9E859";       //"E35CB18E63EEED18";
        Des des=new Des(inputM, inputKey);
        String output=des.cryptToDes();
        des.key.printfKey();
        System.out.printf("Out put of input %s la: %s%n",inputM,output);
    }
}
