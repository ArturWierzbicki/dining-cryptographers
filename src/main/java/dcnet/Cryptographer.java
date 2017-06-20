package dcnet;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static utils.CollectionUtils.xor;

class Cryptographer {
    private static final Logger LOGGER = Logger.getLogger(Cryptographer.class.getName());
    private final String name;
    private final List<Integer> secretBits = new ArrayList<>();
    private final boolean paid;

    Cryptographer(String name, boolean paid) {
        this.name = name;
        this.paid = paid;
    }

    void addSecretBit(int secretBit) {
        secretBits.add(secretBit);
    }

    int announce() {
        int xor = xor(secretBits);
        log("Xor of secret bits is " + xor);

        int result = paid ? negate(xor) : xor;
        log("Announcing " + result);

        return result;
    }

    private int negate(int bit) {
        if (bit == 0) {
            return 1;
        } else if (bit == 1) {
            return 0;
        }

        throw new IllegalArgumentException("Number to negate should be 0 or 1, is " + bit);
    }

    private void log(String message) {
        LOGGER.info("Crypto[" + name + "]: " + message);
    }

    @Override
    public String toString() {
        return name;
    }
}
