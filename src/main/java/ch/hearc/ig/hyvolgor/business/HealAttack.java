package ch.hearc.ig.hyvolgor.business;

public class HealAttack implements IAttack {

    private final String name;
    private final int healing;

    public HealAttack(String name, int healing) {
        this.name = name;
        this.healing = healing;
    }

    @Override
    public String launch(Character attacker, Character target) throws FightException {
        int pvAvant = attacker.getHp();
        attacker.setHp(pvAvant + this.healing);

        return attacker.getName() + " used " + this.name + " and healed " + (attacker.getHp() - pvAvant) + "HP!" + "(HP : " + attacker.getHp() + "/" + Character.MAXIMUM_HP + ")";
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getDamages() {
        return this.healing;
    }
}
