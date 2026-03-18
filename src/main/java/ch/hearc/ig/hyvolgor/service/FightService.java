package ch.hearc.ig.hyvolgor.service;

import ch.hearc.ig.hyvolgor.business.FightException;
import ch.hearc.ig.hyvolgor.business.FireCharacter;
import ch.hearc.ig.hyvolgor.business.IAttack;
import ch.hearc.ig.hyvolgor.business.NatureCharacter;
import ch.hearc.ig.hyvolgor.business.WaterCharacter;
import ch.hearc.ig.hyvolgor.datastructure.ArrayList;
import ch.hearc.ig.hyvolgor.datastructure.List;
import ch.hearc.ig.hyvolgor.business.Character;

public class FightService implements IFightService {

    private final List<Character> availableCharacters;

    public FightService() {
        this.availableCharacters = new ArrayList<>();
        this.loadCharacters();
    }

    private void loadCharacters() {
        this.availableCharacters.add(new FireCharacter("Ignis"));
        this.availableCharacters.add(new WaterCharacter("Aqua"));
        this.availableCharacters.add(new NatureCharacter("Sylva"));
        this.availableCharacters.add(new FireCharacter("Pyros"));
        this.availableCharacters.add(new WaterCharacter("Marina"));
        this.availableCharacters.add(new NatureCharacter("Feuillard"));
    }

    @Override
    public Character getCharacter(int characterNumber) {
        Character character = this.availableCharacters.get(characterNumber);
        this.availableCharacters.remove(characterNumber);
        return character;
    }

    @Override
    public List<Character> getAvailableCharacters() {
        return this.availableCharacters;
    }

    @Override
    public String runTurn(Character attacker, Character target, int attackIndex) throws FightException {
        IAttack choosenAttack = attacker.getAttacks().get(attackIndex);

        return choosenAttack.launch(attacker, target);
    }

    @Override
    public Character winner(Character player1, Character player2) {
        if (!player1.isAlive() && player2.isAlive()) {
            return player2;
        }

        if (!player2.isAlive() && player1.isAlive()) {
            return player1;
        }

        return null;
    }
}
