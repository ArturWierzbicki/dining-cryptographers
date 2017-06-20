package dcnet;

import dcnet.Cryptographer;
import dcnet.DcNet;
import dcnet.DcNetResult;
import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class DcNetTest {
    private final DcNet dcNet = new DcNet();

    @Test
    public void whenNoCryptographerHasPaid_resultShouldIndicateNsaPayment() throws Exception {
        //given
        Cryptographer a = new Cryptographer("A", false);
        Cryptographer b = new Cryptographer("B", false);
        Cryptographer c = new Cryptographer("C", false);
        Cryptographer d = new Cryptographer("D", false);

        //when
        DcNetResult result = dcNet.execute(asList(a, b, c, d));

        //then
        assertEquals(DcNetResult.NSA_PAID, result);
    }

    @Test
    public void whenASingleCryptographerHasPaid_resultShouldIndicateThatACryptographerPaid() throws Exception {
        //given
        Cryptographer a = new Cryptographer("A", false);
        Cryptographer b = new Cryptographer("B", false);
        Cryptographer c = new Cryptographer("C", false);
        Cryptographer d = new Cryptographer("D", true);

        //when
        DcNetResult result = dcNet.execute(asList(a, b, c, d));

        //then
        assertEquals(DcNetResult.CRYPTOGRAPHER_PAID, result);
    }

    @Test
    public void whenAnEvenNumberOfCryptographersPaid_resultShouldIndicateThatNsaHasPaid() throws Exception {
        //given
        Cryptographer a = new Cryptographer("A", false);
        Cryptographer b = new Cryptographer("B", false);
        Cryptographer c = new Cryptographer("C", true);
        Cryptographer d = new Cryptographer("D", true);

        //when
        DcNetResult result = dcNet.execute(asList(a, b, c, d));

        //then
        assertEquals(DcNetResult.NSA_PAID, result);
    }
}