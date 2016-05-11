import java.math.BigInteger;
import java.util.Random;

/**
 * Created by Jeppe Vinberg on 15-04-2016.
 */
public class RSAEncryptionDriver {

    public static void main(String[] args){
        RSA rsa = new RSA();
        int k = 32;
        System.out.println("Generating key with k = " + k);
        KeyObject privateKey = rsa.generatePrivateKey(k);
        KeyObject publicKey = new KeyObject(new BigInteger("3"),privateKey.getN());
        System.out.println("Key generated: " + privateKey);
        BigInteger message = new BigInteger(args[0]);
        System.out.println("Message to be encrypted: " + message);
        BigInteger cipher = rsa.encrypt(publicKey,message);
        System.out.println("Cipher text generated: " + cipher);
        BigInteger messageAfter = rsa.decrypt(privateKey, cipher);
        System.out.println("Decrypted message is: " + messageAfter);



    }

}
