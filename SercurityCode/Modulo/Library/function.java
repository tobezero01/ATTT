package Modulo.Library;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class function {
    public static int gcd(int a, int b){
        while (a*b != 0){ 
            if (a > b){
                a %= b; 
            }else{
                b %= a;
            }
        }
        return a + b;
    }
    public static int euler(int n){
        
        if (isNguyenTo(n)){
            return n-1;
        }
        else {
            int euler=0;
            for(int i=1;i<n;i++){
                if (gcd(i,n)==1){
                    euler+=1;
                }
            }
            return euler;
        }
    }
    public static boolean isNguyenTo(int n){
        boolean check=true;
        for(int i=2;i*i<=n;i++){
            if(n%i==0)
                check=false;
        }
        return check;
    }
    public static Map<Integer,Integer> phanTichThuaSoNguyenTo(int n){
         Map<Integer, Integer> ketqua = new HashMap<>();
         for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                ketqua.put(i, ketqua.getOrDefault(i, 0) + 1);
                n /= i;
            }
        }
        // Nếu n là số nguyên tố lớn hơn 1
        if (n > 1) {
            ketqua.put(n, ketqua.getOrDefault(n, 0) + 1);
        }
        return ketqua;
    }
    public static ArrayList<Integer> uocSo(int n) {
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 1; i  <= n; i++) {
            if (n % i == 0) {
                a.add(i);
            }
        }
        return a;
    }
    // định lý femar
    // •Cho p là số nguyên tố, khi đó:
    // • Nếu a là số nguyên dương và GCD(a, p) = 1, thì: 
    // a
    // p-1(mod p) = 1
    // • Nếu a là số nguyên dương bất kỳ thì:
    // a
    // p(mod p) = a (mod p)

    // Định lý Euler (Tổng quát hoá của Định lý Ferma):
    // • Cho a,n là hai số nguyên tố cùng nhau, tức là gcd(a,n)=1. 
    // Khi đó
    // a^ ø(n)(mod n) = 1
    // • Nếu a và n là hai số nguyên bất kỳ (không cần nguyên tố 
    // cùng nhau), khi đó:
    // a^ ø(n)+1 ≡ a (mod n)
    public static int moduloBinhPhuong(int a,int b,int n){
        Comparator<Integer> giamDanTheoKey = Comparator.reverseOrder();
        Map<Integer,Integer> modLuyThua=new TreeMap<>(giamDanTheoKey);// Key luu so mu, value luu ket qua cua modulo;
        modLuyThua.put(1, a%n);
        for(int i=2;i<=b;i*=2){
            int x=(int) Math.pow(modLuyThua.get(i/2), 2)%n;
            modLuyThua.put(i, x);
        }
        int mod=1;
        while (b!=0) {
            for (Map.Entry<Integer, Integer> entry : modLuyThua.entrySet()) {
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                if(b>=key){
                    mod=(mod*value)%n;
                    b-=key;
                }
            }  
        }
        
        return mod;
    }
    public static int moduloPhanDuTrungHoa(int A,int b,int M){
        Map<Integer,Integer> phanTichSoNT=phanTichThuaSoNguyenTo(M);
        int tong=0;
        for(Map.Entry<Integer, Integer> one: phanTichSoNT.entrySet()){
            int mi=one.getKey();
            int Mi=nghichDaoModulo(M/mi, mi);
            int c=moduloBinhPhuong(A, b, mi);
            tong+=c*Mi*(M/mi);
        }
        return tong%M;
    }
    public static int nghichDaoModulo(int a,int n){
        int A1=0;
        int A2=n;
        int B1=1;
        int B2=a;
        int tempA1=A1;
        int tempA2=A2;
        int Q=0;
        while (B2!=1) {
            Q=(int) A2/B2;
            A1=B1;
            A2=B2;
            B1=tempA1-Q*A1;
            B2=tempA2-Q*A2;
            tempA1=A1;
            tempA2=A2;
        }
        if(B1<0)
            return B1+n;
        else return B1;
    }
    public static boolean checkCanNguyenThuy(int a, int n){
        boolean check=true;
        if(gcd(a, n)!=1)
            check=false;
        int phi_n=euler(n);
        ArrayList<Integer> uocso=uocSo(phi_n);
        for (Integer one : uocso) {
            System.out.printf("uoc:%d %d %n",one,moduloBinhPhuong(a, one, n));
            if(moduloBinhPhuong(a, one, n)==1&&one !=phi_n)
                check=false;
        }
        return check;
    }
    public static int logaritRoiRac(int a, int b, int n) {
        boolean checkFound = false;
        int i = 1;
        int result = -1;
        ArrayList<Integer> moduloLuuVet = new ArrayList<>();
        
        while (!checkFound && i < n) {
            int currentModulo = moduloBinhPhuong(a, i, n);
            if (currentModulo == b) {
                result = i;
                checkFound = true;
            }
            if (moduloLuuVet.contains(currentModulo)) {
                checkFound = true;
            } else {
                moduloLuuVet.add(currentModulo);
            }
            i++;
        }
        return result;
    }
    public static int giaiphuongtrinhmodulo(Map<Integer,Integer> map){
        int M=1;
        int sigma=0;
        for(Entry<Integer,Integer> entry: map.entrySet()){
            M=M*entry.getKey();
        }
        for(Entry<Integer,Integer> entry: map.entrySet()){
           int Mi=M/entry.getKey();
           int mi=entry.getKey();
           int Mi_nghichdao=nghichDaoModulo(Mi, mi);
           sigma+=Mi_nghichdao*Mi*entry.getValue();
        }
        return sigma %M;
    }
}
