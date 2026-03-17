package ch.hearc.ig.hyvolgor.business;

public interface IAttack {

    /**
     * @param attacker The player attacking
     * @param target The player attacked
     * @return Message for the console to show what happened
     */
    String launch(Character attacker, Character target);

    /**
     * @return The name of the attack
     */
    String getName();

    /**
     * @return The damages of the attack
     */
    int getDamages();
}
