import java.math.BigInteger;

/**
 * Created by Jeppe Vinberg on 15-04-2016.
 */
public class RSAEncryptionDriver {

    public static void main(String[] args){
        RSA rsa = new RSA();
        int k = 32;
        System.out.println("Generating key with k = " + k);
        BigInteger privateKey = rsa.keyGen(k);
        BigInteger n = rsa.getN();
        System.out.println("Key generated: " + privateKey);
        System.out.println("n value is " + n);
        System.out.println("n length in bits is " + n.bitLength());
        BigInteger message = new BigInteger(args[0]);
        System.out.println("Message to be encrypted: " + message);
        BigInteger cipher = rsa.encrypt(rsa.getPublicKey(),message);
        System.out.println("Cipher text generated: " + cipher);
        BigInteger messageAfter = rsa.decrypt(privateKey, cipher);
        System.out.println("Decrypted message is: " + messageAfter);



    }

}
