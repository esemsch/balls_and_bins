package eduard.semsch.balls_and_bins;

/*
  Algorithm from M.V. Ramakrishna: Computing the probability of hash table/urn overflow (DOI: http://dx.doi.org/10.1080/03610928708829574)
  
  n .. number of balls
  m .. number of bins
  b .. maximum number of balls in a bin
 */
public class BallsAndBinsExact {

    public static void main(String[] args) {
        final int n = 400;
        final int m = 30;

        for (int b = 15; b <= 33; b++) {
            final double[][] pnmb = pnmb(n, m, b);
            System.out.println("P(" + n + ", " + m + ", " + b + ") = " + pnmb[n][m]);
        }
    }

    static double Ellps(final int n, final int m) {
        double Ellps = 1.0;
        for (int k = 1; k <= n; k++) {
            Ellps += 1 - pnmb(n, m, k)[n][m];
        }
        return Ellps;
    }

    static double[][] pnmb(final int nMax, final int mMax, final int b) {
        final double[][] P = new double[nMax + 1][mMax + 1];

        for (int m = 1; m <= mMax; m++) {
            for (int n = 0; n <= b; n++) {
                P[n][m] = 1.0;
            }
        }

        for (int m = 2; m <= mMax; m++) {
            int deficit = b;
            double term = 1.0;
            for (int n = b + 1; n <= nMax; n++) {
                final Adjustment adjustment = adjustTerm(term, m, deficit);
                deficit = adjustment.deficit;
                term = adjustment.term;

                P[n][m] = P[n-1][m] - term * P[n - b - 1][m-1];
                term = term * (m - 1) / m * n / (n - b);
            }
        }
        return P;
    }

    static Adjustment adjustTerm(double term, int m, int deficit) {
        while (deficit > 0 && term > Double.MIN_VALUE) {
            term /= m;
            deficit--;
        }
        return new Adjustment(deficit, term);
    }

    static final class Adjustment {
        final int deficit;
        final double term;

        Adjustment(int deficit, double term) {
            this.deficit = deficit;
            this.term = term;
        }
    }

}
