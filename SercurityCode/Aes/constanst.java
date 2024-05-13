import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class constanst {
    public static Map<String, String> hexMap = new HashMap<>();
    static {
        hexMap.put("0", "0000");
        hexMap.put("1", "0001");
        hexMap.put("2", "0010");
        hexMap.put("3", "0011");
        hexMap.put("4", "0100");
        hexMap.put("5", "0101");
        hexMap.put("6", "0110");
        hexMap.put("7", "0111");
        hexMap.put("8", "1000");
        hexMap.put("9", "1001");
        hexMap.put("A", "1010");
        hexMap.put("B", "1011");
        hexMap.put("C", "1100");
        hexMap.put("D", "1101");
        hexMap.put("E", "1110");
        hexMap.put("F", "1111");
    }
    public static String[] Rcon = {
        "01000000", // 0x01
        "02000000", // 0x02
        "04000000", // 0x04
        "08000000", // 0x08
        "10000000", // 0x10
        "20000000", // 0x20
        "40000000", // 0x40
        "80000000", // 0x80
        "1B000000", // 0x11b
        "36000000"  // 0x036
    };
    public static final List<int[][]> sBoxes = new ArrayList<>();

    // sus box
    static final String[][] Sbox = {
        {"63", "7c", "77", "7b", "f2", "6b", "6f", "c5", "30", "01", "67", "2b", "fe", "d7", "ab", "76"},
        {"ca", "82", "c9", "7d", "fa", "59", "47", "f0", "ad", "d4", "a2", "af", "9c", "a4", "72", "c0"},
        {"b7", "fd", "93", "26", "36", "3f", "f7", "cc", "34", "a5", "e5", "f1", "71", "d8", "31", "15"},
        {"04", "c7", "23", "c3", "18", "96", "05", "9a", "07", "12", "80", "e2", "eb", "27", "b2", "75"},
        {"09", "83", "2c", "1a", "1b", "6e", "5a", "a0", "52", "3b", "d6", "b3", "29", "e3", "2f", "84"},
        {"53", "d1", "00", "ed", "20", "fc", "b1", "5b", "6a", "cb", "be", "39", "4a", "4c", "58", "cf"},
        {"d0", "ef", "aa", "fb", "43", "4d", "33", "85", "45", "f9", "02", "7f", "50", "3c", "9f", "a8"},
        {"51", "a3", "40", "8f", "92", "9d", "38", "f5", "bc", "b6", "da", "21", "10", "ff", "f3", "d2"},
        {"cd", "0c", "13", "ec", "5f", "97", "44", "17", "c4", "a7", "7e", "3d", "64", "5d", "19", "73"},
        {"60", "81", "4f", "dc", "22", "2a", "90", "88", "46", "ee", "b8", "14", "de", "5e", "0b", "db"},
        {"e0", "32", "3a", "0a", "49", "06", "24", "5c", "c2", "d3", "ac", "62", "91", "95", "e4", "79"},
        {"e7", "c8", "37", "6d", "8d", "d5", "4e", "a9", "6c", "56", "f4", "ea", "65", "7a", "ae", "08"},
        {"ba", "78", "25", "2e", "1c", "a6", "b4", "c6", "e8", "dd", "74", "1f", "4b", "bd", "8b", "8a"},
        {"70", "3e", "b5", "66", "48", "03", "f6", "0e", "61", "35", "57", "b9", "86", "c1", "1d", "9e"},
        {"e1", "f8", "98", "11", "69", "d9", "8e", "94", "9b", "1e", "87", "e9", "ce", "55", "28", "df"},
        {"8c", "a1", "89", "0d", "bf", "e6", "42", "68", "41", "99", "2d", "0f", "b0", "54", "bb", "16"}
    };
    public static Map<Character, Integer> hexToDecimalMap = new HashMap<>();
    static{
        hexToDecimalMap.put('0', 0);
        hexToDecimalMap.put('1', 1);
        hexToDecimalMap.put('2', 2);
        hexToDecimalMap.put('3', 3);
        hexToDecimalMap.put('4', 4);
        hexToDecimalMap.put('5', 5);
        hexToDecimalMap.put('6', 6);
        hexToDecimalMap.put('7', 7);
        hexToDecimalMap.put('8', 8);
        hexToDecimalMap.put('9', 9);
        hexToDecimalMap.put('A', 10);
        hexToDecimalMap.put('B', 11);
        hexToDecimalMap.put('C', 12);
        hexToDecimalMap.put('D', 13);
        hexToDecimalMap.put('E', 14);
        hexToDecimalMap.put('F', 15);
    }  
    public static final String[][] mixColumnsMatrix = {
        {"02", "03", "01", "01"},
        {"01", "02", "03", "01"},
        {"01", "01", "02", "03"},
        {"03", "01", "01", "02"}
    };
}