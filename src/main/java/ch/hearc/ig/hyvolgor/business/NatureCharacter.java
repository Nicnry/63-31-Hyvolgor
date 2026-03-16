package ch.hearc.ig.hyvolgor.business;

public class NatureCharacter extends Character {
    public NatureCharacter(String name) {
        super(name, Type.NATURE);
    }

    @Override
    protected void initAvailableAttacks() {
        addAttack(new BasicAttack("Vines", 20));
        addAttack(new BasicAttack("Razor Leaves", 30));
        addAttack(new StrongAttack("Floral Storm", 50));
        addAttack(new HealAttack("Regeneration", 25));
    }
}
