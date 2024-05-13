package Modulo;

import java.util.HashMap;
import java.util.Map;

import Modulo.Library.function;
public class main {
    public static void main(String[] args) {
        //System.out.println(function.moduloBinhPhuong(3, 341, 7433));
        Map<Integer,Integer> map=new HashMap<>();
        map.put(7, 5);
        map.put(11, 6);
        int nghiem=function.giaiphuongtrinhmodulo(map);
        System.out.println("nghiem: "+nghiem);
    }
        
}
