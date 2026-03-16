package ch.hearc.ig.hyvolgor.business;

public class WaterCharacter extends Character {

    public WaterCharacter(String name) {
        super(name, Type.WATER);
    }

    @Override
    protected void initAvailableAttacks() {
        addAttack(new BasicAttack("Water jet", 20));
        addAttack(new BasicAttack("Wave", 30));
        addAttack(new StrongAttack("Tsunami", 50));
        addAttack(new HealAttack("Pure Source",25));
    }
}
