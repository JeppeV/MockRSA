import java.math.BigInteger;
import java.util.Random;

/**
 * Created by Jeppe Vinberg on 27-04-2016.
 */
public class RSAAuthenticationDriver {

    /** Hashing:
     *  We have tested/measured the speed of the hashing with
     *  100.000 bits and 1000.000 bits, which took 0.005 and 0.017 sec. respectively.
     *  This corresponds to 20.000.000 bit/sec and approx. 60.000.000 bits/sec.
     */


    /** Signing:
     * We have created two methods for signing; one which first uses the hashing method and then signs the
     * returned value, and one which signs the given message directly.
     * A run of the main method seen below resulted in the times: (for a message of 10M bits)
     *      "Time elapsed for signing with hashing: 0.02"
     *      "Time elapsed for signing without hash: 0.088"
     * From this we can conclude that it is much faster to sign a hashed message namely because the
     * bit-lengt is much smaller, but most importantly constant.
     * Furthermore we see that the larger the message, the more time it will take to sign it without hashing first.
     */

    public static void main(String[] args){
        RSA rsa = new RSA();
        int k = 2000;
        BigInteger message = new BigInteger(10000000, new Random());

        System.out.println("Generating private key with k = " + k);
        KeyObject privateKey = rsa.generatePrivateKey(k);
        KeyObject publicKey = new KeyObject(new BigInteger("7"), privateKey.getN());
        BigInteger sign = rsa.sign(privateKey, message);
        boolean verified = rsa.verify(publicKey, message, sign);
        System.out.println("Verified is: " + verified);

    }
}
