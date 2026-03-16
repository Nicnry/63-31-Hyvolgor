package ch.hearc.ig.hyvolgor.business;

import java.util.Random;

public class StrongAttack implements IAttack {

    private static final double FAIL_CHANCE = 0.30;
    private final String name;
    private final int damages;
    private final Random random;

    public StrongAttack(String name, int damages) {
        this.name = name;
        this.damages = damages;
        this.random = new Random();
    }

    @Override
    public String launch(Character attacker, Character target) {
        if (random.nextDouble() < FAIL_CHANCE) {
            return attacker.getName() + " attack " + this.name + "... But failed !";
        }

        double multiplicator = Type.getMultiplicator(attacker.getType(), target.getType());
        int finalDamages = (int) (this.damages * multiplicator);

        target.setHp(target.getHp() - finalDamages);

        return attacker.getName() + " used " + this.name + " on " + target.getName() + "!" + "(" + getEfficientMessage(multiplicator) + ") Critical damages : " + finalDamages;
    }

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
    public int getDamages(){
        return this.damages;
    }
}
