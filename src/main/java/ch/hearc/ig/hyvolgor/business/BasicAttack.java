package ch.hearc.ig.hyvolgor.business;

public class BasicAttack implements IAttack {

    private final String name;
    private final int damages;

    public BasicAttack(String name, int damages) {
        this.name = name;
        this.damages = damages;
    }

    @Override
    public String launch(Character attacker, Character target) throws FightException {
        double multiplicator = Type.getMultiplicator(attacker.getType(), target.getType());
        int totalDamages = (int) (this.damages * multiplicator);
        target.setHp(target.getHp() - totalDamages);

        return attacker.getName() + " used " + this.name + " on " + target.getName() + "!" + "(" + getEfficientMessage(multiplicator) + ") Damages : " + totalDamages;
    }

    /**
     * @param multiplicator The multiplicator bonus based on the rule (Fire > Water > Nature > Fire > ...)
     * @return Message to show in the console when needed
     */
    private String getEfficientMessage(double multiplicator) {
        if (multiplicator == Type.MULTIPLICATOR_BONUS) {
            return "Super effective!";
        }

        if (multiplicator == Type.MULTIPLICATOR_PENALITY) {
            return "Not very effective...";
        }

        return "Normal effectiveness";
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getDamages() {
        return this.damages;
    }

    @Override
    public String toString() {
        return this.name + " (Total damages: " + this.damages + ")";
    }
}
