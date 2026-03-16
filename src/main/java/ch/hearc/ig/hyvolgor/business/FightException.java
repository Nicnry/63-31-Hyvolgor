package ch.hearc.ig.hyvolgor.business;

public class FightException extends RuntimeException {
    public FightException(String message) {
        super(message);
    }

    public FightException(String message, Throwable reason) {
        super(message, reason);
    }
}
