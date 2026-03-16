package ch.hearc.ig.hyvolgor.business;

public interface IAttack {

    /**
     * @param attacker
     * @param target
     * @return Message for the console to show what happened
     */
    String launch(Character attacker, Character target);

    String getName();

    int getDamages();
}
