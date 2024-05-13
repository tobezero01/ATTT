public class Main {

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
    // Diffie- hellman
    public static void Diffie_Hellman(long q, long a, long xA, long xB) {
        System.out.println("Giá trị đầu vào :");
        System.out.println("q,a là các số nguyên,  a là CNT của q");
        System.out.println("Đầu vào 2 khóa riêng xA, xB = " +xA + "," + xB);
        long yA = calculateModulo(a, xA, q);
        long yB = calculateModulo(a, xB, q);
        long kA = calculateModulo(yB, xA, q);
        long kB = calculateModulo(yA, xB, q);

        System.out.println("khóa công khai : kA = " + yA + " ; " + "kB = " +yB
        + "\n" + "Khóa phiên : kA = " + kA + " ; " + "kB = " + kB);
        if(kA == kB) System.out.println("Khóa phiên K thỏa mãn!");
    }

    public static void RSA(long p,long q , long e  ,long M, int check) {
        System.out.println("Giá trị đầu vào :");
        System.out.println("Các giá trị  p,q,e để tạo khóa");
        System.out.println("Ban mã thông điệp M");
        System.out.println("Dạng bài theo Check = {1,2}");
        long n = q*p;
        long phiN = (q-1) *(p-1);
        System.out.println("\nMã công khai  : {e,n} = {" + e +"," + n +"}");

        long d = eclidModInverse(e,phiN);
        System.out.println("Mã khóa riêng : {d,n} = {" + d +"," + n +"}");

        if( check == 1) {
            System.out.println("\nBài toán RSA dạng 1 ");
            long C = calculateModulo(M,d,n);
            System.out.println("Mã hóa bằng mã riêng d: C = " + C);
            System.out.println("Giải mã bằng mã công khai e : M = " + calculateModulo(C,e,n) + " " + (M==calculateModulo(C,e,n) ? "True" : "False"));
            System.out.println("Thực hiện chức năng CHỮ KÝ SỐ !");
        }else {
            System.out.println("\nBài toán RSA dạng 2 ");
            long C = calculateModulo(M,e,n);
            System.out.println("Mã hóa bằng mã công khai e : C = " + C);
            System.out.println("Giải mã bằng mã riêng d : M = " + calculateModulo(C,d,n) + " " + (M==calculateModulo(C,d,n) ? "True" : "False"));
            System.out.println("Thực hiện chức năng BẢO MẬT !");
        }

    }

    public static void ElGamal (long q ,long a, long xA, long k, long M) {
        System.out.println("Giá trị đầu vào :");
        System.out.println("Giá trị chung :");
        System.out.println("Giá trị đầu vào :");

        long yA = calculateModulo(a,xA,q);
        System.out.println("Khóa công khai : {q,a,yA} = {" + q + "," + a + "," + yA + "}");

        long K = calculateModulo(yA, k, q);
        long C1 = calculateModulo(a,k,q);
        long C2 = (K*M)%q;
        System.out.println("K = " + K);
        System.out.println("Bản mã : (C1,C2) = " + "(" + C1 + "," + C2 +")" );

        if(K == calculateModulo(C1,xA,q) && M == (calculateModulo(C2,1,q)*eclidModInverse(K,q))%q) {
            System.out.println("Giải mã thành công !");
        }
    }

    public static void ChuKySoDSA(long p,long q, long h,long xA, long k, long Hm) {
        Hm =7;

        long g = calculateModulo(h, (p-1)*q, p);
        long yA = calculateModulo(g,xA,p);
        System.out.println("Khóa công khai của A : yA = " + yA);

        long r = calculateModulo(g,k,p) %q;
        long s = (eclidModInverse(k,q)*((Hm + xA*r)%q)) %q;
        System.out.println("Chữ ký số của A : (r,s) = " + "(" + r + "," + s + ")");

        System.out.println("Xác minh");
        long w = eclidModInverse(s,q);
        long u1 = (Hm*w)%q;
        long u2 = (r*w)%q;
        long v = ((calculateModulo(g,u1,p)*calculateModulo(g,u2,p))%p)%q;
        if(v == r) {
            System.out.println("Xác minh thành công !");
        }
    }

    public static void main(String[] args) {
        System.out.println("Bài toán Diffie-Hellman");
        Diffie_Hellman(6781, 7,380,478);

        System.out.println("\n\n\nBài toán RSA ");

        RSA(47,53,71,67,1);
        RSA(47,53,71,67,2);

        System.out.println("\n\n\nBài toán ElGamal ");
        ElGamal(7057,5,463,973,402);

        System.out.println("\n\n\nBài toán Chữ ký số DSA ");
        ChuKySoDSA(83,41,32,2,2,7);

        System.out.println(calculateModulo(4302,14,7057));
    }
}