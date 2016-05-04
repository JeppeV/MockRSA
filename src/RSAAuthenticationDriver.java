import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Random;

/**
 * Created by Jeppe Vinberg on 27-04-2016.
 */
public class RSAAuthenticationDriver {

    /** Hashing
     *  We have tested/measured the speed of the hashing with
     *  100.000 bits and 1000.000 bits, which took 0.005 and 0.017 sec. respectively.
     *  This corresponds to 20.000.000 bit/sec and approx. 60.000.000 bits/sec.
     */

    public static void main(String[] args){
        RSA rsa = new RSA();
        int k = 2000;
        BigInteger message = new BigInteger(100000, new Random());

        System.out.println("Generating private key with k = " + k);
        BigInteger privateKey = rsa.keyGen(k);
        BigInteger n = rsa.getN();
        System.out.println("length of n is " + n.bitLength());
        BigInteger sign = rsa.sign(privateKey, message);
        //System.out.println("Sign s generated: " + sign);
        boolean verified = rsa.verify(rsa.getPublicKey(), message, sign);
        System.out.println("Verified is: " + verified);

    }
}
