javac *.java
echo "The files have been compiled. \nRunning: RSAAuthenticationDriver. \n\n/** Hashing
     *  We have tested/measured the speed of the hashing with
     *  100.000 bits and 1000.000 bits, which took 0.005 and 0.017 sec. respectively.
     *  This corresponds to 20.000.000 bit/sec and approx. 60.000.000 bits/sec.
     */ \n "
java RSAAuthenticationDriver 
