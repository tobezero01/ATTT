import java.math.BigInteger;
import java.util.*;

public class Main {

    public static boolean isPrime(long n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0)  return false;
        int i = 5;
        while (i * i <= n) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
            i += 6;
        }
        return true;
    }
    public static long gcd(long a,long b) {
        if(b == 0) return a;
        return gcd(b, a%b);
    }
    public static long euler(long n){

        if (isPrime(n)){
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

    public static ArrayList<Long> uocSo(long n) {
        ArrayList<Long> a = new ArrayList<>();
        for (long i = 1; i  <= n; i++) {
            if (n % i == 0) {
                a.add(i);
            }
        }
        return a;
    }
    public static boolean CanNguyenThuy(long a, long n){
        boolean check=true;
        if(gcd(a, n )!=1)
            check=false;
        long phi_n=euler(n);
        ArrayList<Long> uocso = uocSo(phi_n);
        for (Long one : uocso) {
            System.out.printf("uoc:%d %d %n",one,calculateModulo(a, one, n));
            if(calculateModulo(a, one, n)==1&&one !=phi_n)
                check=false;
        }
        return check;
    }
    public static int logaritRoiRac(long a, long b, long n) {
        boolean checkFound = false;
        int i = 1;
        int result = -1;
        ArrayList<Long> moduloLuuVet = new ArrayList<>();

        while (!checkFound && i < n) {
            long currentModulo = calculateModulo(a, i, n);
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

    // phân tích thừa số nguyên tố
    public static List<Long> primeFactorAnalysis(long number) {
        List<Long> primeFactors = new ArrayList<>();
        long divisor = 2;

        while (number > 1) {
            while (number % divisor == 0) {
                primeFactors.add(divisor);
                number /= divisor;
            }
            divisor++;
        }

        return primeFactors;
    }

    public static void TrungHoaModulo(long a, long k, long n) {
        List<Long> m = primeFactorAnalysis(n);
        List<Long> M = new ArrayList<>();
        List<Long> C = new ArrayList<>();
        List<Long> A = new ArrayList<>();
        for (int i = 0; i < m.size(); i++) {
            M.add(n / m.get(i));
            C.add(M.get(i) * eclidModInverse(M.get(i), m.get(i)));
            A.add(calculateModulo(a, k, m.get(i)));
        }
        long result = 0;
        for (int i = 0; i < m.size(); i++) {
            result += A.get(i) * C.get(i);
        }
        System.out.println( "Trung hoa modulo : " + a +"^" + k +" mod "+ n +" = " + calculateModulo(result, 1, n));
    }

    // tim nghịch đảo eclid mở rộng  b = a^-1 mod n
    public static long eclidModInverse(long a, long n) {

        long Q, A1 = 1, A2 = 0,A3 = n, B1 = 0, B2 = 1, B3 = a,T1,T2,T3;
        while (B3 != 1) {
            Q = A3 /B3;
            T1 = A1 - Q*B1 ; T2 = A2 - Q*B2 ; T3 = A3 - Q*B3;
            A1 = B1;A2 = B2;A3 =B3;
            B1 =T1;B2 =T2;B3 =T3;
        }

        return B2 <0 ? B2+n:B2;
    }
    public static void eclidModInverse2(long a, long n) {

        long Q=0, A1 = 1, A2 = 0,A3 = n, B1 = 0, B2 = 1, B3 = a,T1,T2,T3;
        System.out.println(Q + " " + A2 + " "+A3 +" " +B2 + " " +B3);
        while (B3 != 1) {
            Q = A3 /B3;
            T1 = A1 - Q*B1 ; T2 = A2 - Q*B2 ; T3 = A3 - Q*B3;
            A1 = B1;A2 = B2;A3 =B3;
            B1 =T1;B2 =T2;B3 =T3;
            System.out.println(Q + " " + A2 + " "+A3 +  " " +B2 + " " +B3);
        }

        //return B2 <0 ? B2+n:B2;
    }


    // Hàm tính modulo a^m mod n
    public static long calculateModulo(long a, long m, long n) {
        if (m == 0) {
            return 1 % n;
        }
        if (m % 2 == 0) {
            long temp = calculateModulo(a, m / 2, n);
            return (temp * temp) % n;
        } else {
            long temp = calculateModulo(a, m - 1, n);
            return (a * temp) % n;
        }
    }


    public static void main(String[] args) {
        long a = 2314;
        long m = 71;
        long n = 2491;
        
        TrungHoaModulo(a,m,n);

        System.out.println(eclidModInverse(71,2392));

        System.out.println("Kết quả: " + calculateModulo(a, m, n));

        eclidModInverse2(20,41);

    }
}