import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Random;

/**
 * Created by Jeppe Vinberg on 27-04-2016.
 */
public class RSAAuthenticationDriver {

    public static void main(String[] args){
        RSA rsa = new RSA();
        int k = 2000;
        BigInteger message = new BigInteger(80000, new Random());

        System.out.println("Generating private key with k = " + k);
        BigInteger privateKey = rsa.keyGen(k);
        BigInteger n = rsa.getN();
        System.out.println("Private key generated: " + privateKey);
        System.out.println("n value is " + n);
        System.out.println("length of n is " + n.bitLength());
        //System.out.println("Message to be signed: " + message);
        BigInteger sign = rsa.sign(privateKey, message);
        System.out.println("Sign s generated: " + sign);
        boolean verified = rsa.verify(rsa.getPublicKey(), message, sign);
        System.out.println("Verified is: " + verified);



    }
}
