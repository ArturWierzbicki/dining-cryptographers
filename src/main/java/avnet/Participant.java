package avnet;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;

import static avnet.CryptoConfiguration.Q;
import static avnet.CryptoConfiguration.g;

class Participant {
    private final Random random = new SecureRandom();
    private final String name;
    private final boolean veto;
    private BigInteger secretKey;
    private BigInteger publicKey;
    private BigInteger gy;

    Participant(String name, boolean veto) {
        this.name = name;
        this.veto = veto;
    }

    BigInteger getPublicKey() {
        return publicKey;
    }

    void selectSecretKey() {
        secretKey = random();
        publicKey = g.modPow(secretKey, Q);
    }

    void setGy(BigInteger gy) {
        this.gy = gy;
    }

    BigInteger getAnnouncement() {
        return veto ? gy.modPow(random(), Q) : gy.modPow(secretKey, Q);
    }

    @Override
    public String toString() {
        return name;
    }

    private BigInteger random() {
        return BigInteger.valueOf(random.nextInt(412412414)).mod(Q);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
