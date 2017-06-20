package avnet;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static avnet.CryptoConfiguration.Q;

class AvNet {
    private final static Logger LOGGER = Logger.getLogger(AvNet.class.getName());

    AvNetResult execute(List<Participant> participants) {
        participants.forEach(Participant::selectSecretKey);
        //TODO secret keys should be verified with ZKP

        createGYsForParticipants(participants);

        List<BigInteger> announcements = participants.stream().map(Participant::getAnnouncement).collect(Collectors.toList());
        //TODO private factors of announcements should be verified with ZKP

        BigInteger result = multiplyAnnouncements(announcements);
        LOGGER.info("Result is " + result);

        if (result.equals(BigInteger.ONE)) {
            return AvNetResult.NO_VETOES;
        }

        return AvNetResult.VETO;
    }

    private void createGYsForParticipants(List<Participant> participants) {
        for (int i = 0; i < participants.size(); i++) {
            List<Participant> participantsBefore = getParticipantsBefore(participants, i);
            List<Participant> participantsAfter = getParticipantsAfter(participants, i);

            BigInteger multipliedParticipantsBefore = multiply(participantsBefore);
            LOGGER.info("Participants before multiplied to " + multipliedParticipantsBefore);

            BigInteger multipliedParticipantsAfter = multiply(participantsAfter);
            LOGGER.info("Participants after multiplied to " + multipliedParticipantsAfter);

            BigInteger gy = multipliedParticipantsBefore.multiply(multipliedParticipantsAfter.modInverse(Q)).mod(Q);
            participants.get(i).setGy(gy);
        }
    }

    private BigInteger multiply(List<Participant> participants) {
        return participants.stream().map(Participant::getPublicKey)
                .reduce(BigInteger.ONE, (x, y) -> x.multiply(y).mod(Q));
    }

    private BigInteger multiplyAnnouncements(List<BigInteger> bigIntegers) {
        return bigIntegers.stream().reduce(BigInteger.ONE, (x, y) -> x.multiply(y).mod(Q));
    }

    private List<Participant> getParticipantsAfter(List<Participant> participants, int i) {
        return i == participants.size() - 1 ? new ArrayList<>() : participants.subList(i + 1, participants.size());
    }

    private List<Participant> getParticipantsBefore(List<Participant> participants, int i) {
        return i == 0 ? new ArrayList<>() : participants.subList(0, i);
    }
}
