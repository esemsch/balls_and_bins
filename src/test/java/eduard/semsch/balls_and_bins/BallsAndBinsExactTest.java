package eduard.semsch.balls_and_bins;

import org.junit.Assert;
import org.junit.Test;

public class BallsAndBinsExactTest {

    /*
    Numbers from M.V. Ramakrishna: Computing the probability of hash table/urn overflow (DOI: http://dx.doi.org/10.1080/03610928708829574)
     */
    @Test
    public void calculatesCorrectProbabilities() {
        final int n = 400;
        final int m = 30;

        Assert.assertTrue("wrong result", Math.abs(BallsAndBinsExact.pnmb(n, m, 18)[n][m] - 0.0441) < 0.0001);
        Assert.assertTrue("wrong result", Math.abs(BallsAndBinsExact.pnmb(n, m, 21)[n][m] - 0.5896) < 0.0001);
        Assert.assertTrue("wrong result", Math.abs(BallsAndBinsExact.pnmb(n, m, 24)[n][m] - 0.9321) < 0.0001);
    }

    /*
    Numbers from Pedro Reviriego,Lars Holst, Juan Antonio Maestro: On the expected longest length probe sequence for hashing with separate chaining. (DOI: http://dx.doi.org/10.1016/j.jda.2011.04.005)
     */
    @Test
    public void calculatesCorrectEllps() {
        Assert.assertTrue("wrong result", Math.abs(BallsAndBinsExact.Ellps(12, 24) - 2.2636) < 0.0001);
        Assert.assertTrue("wrong result", Math.abs(BallsAndBinsExact.Ellps(24, 24) - 3.3515) < 0.0001);
        Assert.assertTrue("wrong result", Math.abs(BallsAndBinsExact.Ellps(48, 24) - 5.1755) < 0.0001);
    }
}