package ch.hearc.ig.hyvolgor.business;

// Enum in java can use local static method as explained in this oracle documentation https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
public enum Type {
    FIRE,
    WATER,
    NATURE;

    public static final double MULTIPLICATOR_BONUS = 1.5;

    public static final double MULTIPLICATOR_PENALITY = 0.5;

    public static final double MULTIPLICATOR_DEFAULT = 1.0;

    /**
     * @param attackerType
     * @param targetType
     * @return The damage multiplicator
     */
    public static double getMultiplicator(Type attackerType, Type targetType) {
        if (isStronger(attackerType, targetType)) {
            return MULTIPLICATOR_BONUS;
        } else if (isStronger(targetType, attackerType)) {
            return MULTIPLICATOR_PENALITY;
        }
        return MULTIPLICATOR_DEFAULT;
    }

    /**
     * @param typeA
     * @param typeB
     * @return true if A type is stronger than B
     */
    public static boolean isStronger(Type typeA, Type typeB) {
        return (typeA == FIRE && typeB == NATURE) || (typeA == NATURE && typeB == WATER) || (typeA == WATER && typeB == FIRE);
    }
}
