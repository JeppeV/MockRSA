javac *.java
echo "The files have been compiled. \nRunning: RSAAuthenticationDriver. \n\n
    /** Hashing
     *  We have tested/measured the speed of the hashing with
     *  100.000 bits and 1000.000 bits, which took 0.005 and 0.017 sec. respectively.
     *  This corresponds to 20.000.000 bit/sec and approx. 60.000.000 bits/sec.
     */ \n
     /** Signing:
       * We have created two methods for signing; one which first uses the hashing method and then signs the
       * returned value, and one which signs the given message directly.
       * A run of the main method seen below resulted in the times: (for a message of 10M bits)
       *      "Time elapsed for signing with hashing: 0.02"
       *      "Time elapsed for signing without hash: 0.088"
       * From this we can conclude that it is much faster to sign a hashed message namely because the
       * bit-lengt is much smaller, but most importantly constant.
       * Furthermore we see that the larger the message, the more time it will take to sign it without hashing first.
       */ \n"
java RSAAuthenticationDriver 
