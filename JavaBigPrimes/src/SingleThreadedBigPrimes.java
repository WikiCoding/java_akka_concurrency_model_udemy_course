import java.math.BigInteger;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

public class SingleThreadedBigPrimes {
    public static void main(String[] args) {
        Long start = System.currentTimeMillis();

        SortedSet<BigInteger> primes = new TreeSet<>();

        while (primes.size() < 20) {
            // random big integer between 0 and 2^(2000-1)
            BigInteger bigInteger = new BigInteger(3000, new Random());
            primes.add(bigInteger.nextProbablePrime());
        }

        Long end = System.currentTimeMillis();

        System.out.println(primes);
        System.out.println("Time taken: " + (end - start) + " ms.");
        // Time taken: 19210 ms. Doesn't max out CPU
    }
}
