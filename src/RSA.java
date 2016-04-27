import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Created by Jeppe Vinberg on 12-04-2016.
 */
public class RSA {

    private BigInteger one = BigInteger.ONE;
    private BigInteger e = new BigInteger("3");
    private BigInteger n = BigInteger.ZERO;
    private BigInteger cachedQ = null, cachedP = null;

    private MessageDigest sha_256;

    public RSA(){
        try{
            sha_256 = MessageDigest.getInstance("SHA-256");
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }

    public BigInteger getN(){
        return n;
    }

    public BigInteger getPublicKey() {
        return e;
    }

    public BigInteger keyGen(int k){
        Random r = new Random();
        BigInteger p, q;
        int k1 = k/2;
        int k2 = (int) Math.ceil(k/2);
        while(true){
            p = getP(k1, r);
            q = getQ(k2, r);
            if(p != null && q != null){
                if(p.equals(q)){
                    cachedQ = null;
                    continue;
                }
                n = p.multiply(q);
                break;
            }

        }
        BigInteger qMinusOne = q.subtract(one);
        BigInteger pMinusOne = p.subtract(one);
        return e.modInverse(qMinusOne.multiply(pMinusOne));
    }

    private BigInteger getQ(int k, Random r){
        if(cachedQ != null){
            return cachedQ;
        }
        BigInteger q = BigInteger.probablePrime(k, r);
        BigInteger qMinusOne = q.subtract(one);
        if(e.gcd(qMinusOne).equals(one)){
            cachedQ = q;
        }
        return cachedQ;



    }

    private BigInteger getP(int k, Random r){
        if(cachedP != null){
            return cachedP;
        }
        BigInteger p = BigInteger.probablePrime(k, r);
        BigInteger pMinusOne = p.subtract(one);
        if(e.gcd(pMinusOne).equals(one)){
            cachedP = p;
        }
        return cachedP;
    }

    public BigInteger encrypt(BigInteger message, BigInteger key){
        return encryptDecrypt(message, key);
    }

    public BigInteger decrypt(BigInteger message, BigInteger key){
        return encryptDecrypt(message, key);
    }

    private BigInteger encryptDecrypt(BigInteger message, BigInteger key){
        return message.modPow(key, getN());
    }


    public BigInteger sign(BigInteger privateKey, BigInteger message){

        System.out.println("Starting hashing operation");
        double start = System.currentTimeMillis();
        sha_256.update(message.toByteArray());
        byte[] h = sha_256.digest();
        double stop = System.currentTimeMillis();
        System.out.println("Time elapsed: " + ((stop-start)/1000.0));
        BigInteger hash = new BigInteger(h);
        return encrypt(hash, privateKey);
    }

    public boolean verify(BigInteger publicKey, BigInteger message, BigInteger signature){
        sha_256.update(message.toByteArray());
        byte[] h = sha_256.digest();
        BigInteger hash = new BigInteger(h);
        return decrypt(signature, publicKey).equals(hash);
    }




}