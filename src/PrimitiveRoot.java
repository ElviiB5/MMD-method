import java.util.Random;

public class PrimitiveRoot {
    int k, m, t;
    int[] p;

    public double getRoot(int m) {
        this.m = m;
        p = new int[16];
        double randm = Math.random();

        getP();
        getRnd();

        System.out.println("p: " + p[k]);
        if (randm < 0.5)
            return (200 * t - p[k]);
        else
            return (200 * t + p[k]);
    }

    public void getRnd() {
        Random r = new Random();
        k = r.nextInt(15);
        t = r.nextInt(m);
    }

    public void getP() {
        p[0] = 3;
        p[1] = 11;
        p[2] = 13;
        p[3] = 19;
        p[4] = 21;
        p[5] = 27;
        p[6] = 29;
        p[7] = 37;
        p[8] = 53;
        p[9] = 59;
        p[10] = 61;
        p[11] = 67;
        p[12] = 69;
        p[13] = 77;
        p[14] = 83;
        p[15] = 91;
    }
}
