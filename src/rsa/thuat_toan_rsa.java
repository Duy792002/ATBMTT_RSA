package rsa;

import java.math.BigInteger;
import java.security.SecureRandom;

public class thuat_toan_rsa {
//d: private key, public key
    private BigInteger n, d, e;
   

    public BigInteger getN() {
        return n;
    }

    public void setN(BigInteger n) {
        this.n = n;
    }

    public BigInteger getD() {
        return d;
    }

    public void setD(BigInteger d) {
        this.d = d;
    }

    public BigInteger getE() {
        return e;
    }

    public void setE(BigInteger e) {
        this.e = e;
    }

    public thuat_toan_rsa(BigInteger newn, BigInteger newe) {
        n = newn;
        e = newe;
    }
    
    public thuat_toan_rsa() {     
       
    }
    
    //Tạo khóa
    public void KeyRSA(int bits){
        
        
        SecureRandom r = new SecureRandom();//Tạo số r random
        BigInteger p = new BigInteger(bits , 100, r);
        BigInteger q = new BigInteger(bits , 100, r);
        n = p.multiply(q);
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q
                .subtract(BigInteger.ONE)); //m là phi n
        boolean found = false;
        do {
            e = new BigInteger(bits , 50, r);
            if (m.gcd(e).equals(BigInteger.ONE) && e.compareTo(m) < 0) {
                found = true;
            }
        } while (!found);
        d = e.modInverse(m);
        
    }


    //Mã hóa tin nhắn văn bản gốc, sử dụng giải mã khóa chung
   
    
    public synchronized String encrypt(String message) {
        return (new BigInteger(message.getBytes())).modPow(e, n).toString();
    }

    public synchronized BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

  
     //Giải mã tin nhắn văn bản, sử dụng giải mã khóa riêng
   
    public synchronized String decrypt(String message) {
        return new String((new BigInteger(message)).modPow(d, n).toByteArray());
    }

    public synchronized BigInteger decrypt(BigInteger message) {
        return message.modPow(d, n);
    }

 

     // Test program.
  
    public static void main(String[] args) throws Exception {
        thuat_toan_rsa rsa = new thuat_toan_rsa();
        rsa.KeyRSA(1024);
        String text ="Hallo, Xin chào!";
        String mahoa=rsa.encrypt(text);
        System.out.println("Đây là kết quả đoạn mã hóa:"+mahoa);
        System.out.println("Giải mã được như sau: "+rsa.decrypt(mahoa));
       
    }

    void setN(int bitleg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
