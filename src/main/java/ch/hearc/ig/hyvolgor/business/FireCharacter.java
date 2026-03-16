package ch.hearc.ig.hyvolgor.business;

public class FireCharacter extends Character {
    public FireCharacter(String name) {
        super(name, Type.FIRE);
    }

    @Override
    protected void initAvailableAttacks() {
        addAttack(new BasicAttack("Ember",20));
        addAttack(new BasicAttack("Blaze", 30));
        addAttack(new StrongAttack("Inferno",50));
        addAttack(new HealAttack("Vital Warmth",25));
    }
}
