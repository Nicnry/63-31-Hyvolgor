package ch.hearc.ig.hyvolgor.service;

import ch.hearc.ig.hyvolgor.business.FightException;
import ch.hearc.ig.hyvolgor.datastructure.List;
import ch.hearc.ig.hyvolgor.business.Character;

public interface IFightService {

    /**
     * @return List with all characters
     */
    List<Character> getAvailableCharacters();

    /**
     *
     * @param characterNumber The character index wanted for the player
     * @return The selected character
     */
    Character getCharacter(int characterNumber);

    /**
     * @param attacker The attacker player
     * @param target The targeted player
     * @param attackIndex index of the attack
     * @return Message with turn result
     * @throws FightException If the target is under than 0 hp
     */
    String runTurn(Character attacker, Character target, int attackIndex) throws FightException;

    /**
     * @param player1 The player
     * @param player2 The openent
     * @return Winner or null
     */
    Character winner(Character player1, Character player2);
}
