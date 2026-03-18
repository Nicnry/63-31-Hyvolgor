package ch.hearc.ig.hyvolgor.business;

import ch.hearc.ig.hyvolgor.datastructure.ArrayList;
import ch.hearc.ig.hyvolgor.datastructure.List;

public abstract class Character {
    public static final int MAXIMUM_HP = 100;
    private final String name;
    private int hp;
    private final Type type;
    private final List<IAttack> attacks;

    protected Character(String name, Type type) {
        this.name = name;
        this.type = type;
        this.hp = MAXIMUM_HP;
        this.attacks = new ArrayList<>();
        this.initAvailableAttacks();
    }

    protected abstract void initAvailableAttacks();

    public String getName() {
        return this.name;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) throws FightException {
        if(hp > MAXIMUM_HP) {
            throw new FightException("HP over the maximum HP");
            //this.hp = MAXIMUM_HP;// it's a solution to not block the game process
        } else if (hp < 0) {
            this.hp = 0;
        } else {
            this.hp = hp;
        }
    }

    public Type getType() {
        return this.type;
    }

    public List<IAttack> getAttacks() {
        return this.attacks;
    }

    /**
     * @return true if at least 1 HP
     */
    public boolean isAlive() {
        return this.hp > 0;
    }

    /**
     * @param attack The attack to add on the character attack set
     */
    protected void addAttack(IAttack attack) {
        this.attacks.add(attack);
    }

    @Override
    public String toString() {
        return this.name + " (" + this.type + ") " + this.hp + " " + MAXIMUM_HP;
    }
}
