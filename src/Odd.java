public class Odd {
    public long getOdd(int m) {
        long calculatedOdd = (2 * (int) (m * Math.random() + 0.5) + 1) % m;
        return calculatedOdd;
    }
}
