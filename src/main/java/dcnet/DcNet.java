package dcnet;

import java.security.SecureRandom;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static utils.CollectionUtils.xor;

class DcNet {
    private static final Logger LOGGER = Logger.getLogger(DcNet.class.getName());
    private final SecureRandom secureRandom = new SecureRandom();

    DcNetResult execute(List<Cryptographer> cryptographers) {
        establishSecretKeys(cryptographers);

        List<Integer> announcements = cryptographers.stream()
                .map(Cryptographer::announce)
                .collect(Collectors.toList());

        return determineProtocolResult(announcements);
    }

    private DcNetResult determineProtocolResult(List<Integer> announcements) {
        int result = xor(announcements);
        if(result == 0) {
            return DcNetResult.NSA_PAID;
        } else if (result == 1) {
            return DcNetResult.CRYPTOGRAPHER_PAID;
        }

        return DcNetResult.INVALID_RESULT;
    }

    private void establishSecretKeys(List<Cryptographer> cryptographers) {
        for (int i = 0; i < cryptographers.size() - 1; i++) {
            for (int j = i + 1; j < cryptographers.size(); j++) {
                int randomBit = secureRandom.nextInt(2);
                LOGGER.info("Cryptographers pair ["
                        + cryptographers.get(i) + ", " + cryptographers.get(j)
                        + "] has secret " + randomBit);

                cryptographers.get(i).addSecretBit(randomBit);
                cryptographers.get(j).addSecretBit(randomBit);
            }
        }
    }
}
