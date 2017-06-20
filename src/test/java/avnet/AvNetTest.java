package avnet;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class AvNetTest {
    private final AvNet avNet = new AvNet();

    @Test
    public void whenNoParticipantHasVetoed_resultShouldIndicateNoVetoes() throws Exception {
        //given
        Participant a = new Participant("A", false);
        Participant b = new Participant("B", false);
        Participant c = new Participant("C", false);
        Participant d = new Participant("D", false);

        //when
        AvNetResult result = avNet.execute(asList(a, b, c, d));

        //then
        assertEquals(AvNetResult.NO_VETOES, result);
    }

    @Test
    public void whenASingleParticipantHasVetoed_resultShouldIndicateThatAParticipantVetoed() throws Exception {
        //given
        Participant a = new Participant("A", false);
        Participant b = new Participant("B", false);
        Participant c = new Participant("C", false);
        Participant d = new Participant("D", true);

        //when
        AvNetResult result = avNet.execute(asList(a, b, c, d));

        //then
        assertEquals(AvNetResult.VETO, result);
    }
}