package ch.hearc.ig.hyvolgor.service;

import ch.hearc.ig.hyvolgor.business.IAttack;
import ch.hearc.ig.hyvolgor.business.Character;
import ch.hearc.ig.hyvolgor.business.FightException;

import java.util.Scanner;

public class GameService {

    private static final String SEPARATOR = "═══════════════════════════════════════════";
    private IFightService fightService;
    private final Scanner scanner;
    private Character player1;
    private Character player2;

    public GameService() {
        this.fightService = new FightService();
        this.scanner = new Scanner(System.in);
    }

    /**
     * @return the welcome message, first print
     */
    public StringBuffer buildWelcome() {
        StringBuffer message = new StringBuffer();
        message.append(SEPARATOR);
        message.append("\n");
        message.append("★ ELEMENTAL FIGHT GAME ★");
        message.append("\n");
        message.append("FIRE > NATURE > WATER > FIRE");
        message.append("\n");
        message.append(SEPARATOR);
        message.append("\n");
        return message;
    }

    /**
     * @return The message when a new game is loaded
     */
    public StringBuffer buildNewGameHeader() {
        StringBuffer message = new StringBuffer();
        message.append("\n");
        message.append(SEPARATOR);
        message.append("\n");
        message.append("NEW GAME");
        message.append("\n");
        message.append(SEPARATOR);
        message.append("\n");
        return message;
    }


    /**
     * @return Player selection character message
     */
    public StringBuffer buildPlayerSelectionPrompt(int playerNumber) {
        StringBuffer message = new StringBuffer();
        // https://docstore.mik.ua/orelly/java-ent/jnut/ch02_10.htm & https://stackoverflow.com/questions/2165950/passing-references-type-variable-as-method-parameter#answer-2166015
        // The "message" var passed by reference can let us edit the message with these 2 actions
        this.buildPlayerSelectionMessage(playerNumber, message);
        this.buildCharacterList(message);
        return message;
    }

    /**
     *
     * @param playerNumber The player number to know which one is playing
     * @param message The message passed by reference instead creating new StringBuffer
     */
    private void buildPlayerSelectionMessage(int playerNumber, StringBuffer message) {
        message.append("\nPlayer ");
        message.append(playerNumber);
        message.append(", choose your character:\n");
    }

    /**
     * @param message The message passed by reference instead creating new StringBuffer
     */
    private void buildCharacterList(StringBuffer message) {
        int index = 1;
        for (Character character : this.fightService.getAvailableCharacters()) {
            message.append("  ");
            message.append(index);
            message.append(". ");
            message.append(character.getName());
            message.append(" (");
            message.append(character.getType());
            message.append(")");
            message.append("\n");
            index++;
        }
    }

    /**
     * @return Message when fight is launched
     */
    public StringBuffer buildVersusScreen() {
        StringBuffer message = new StringBuffer();
        message.append("\n");
        message.append(SEPARATOR);
        message.append("\n");
        message.append("THE FIGHT WILL STARTED FOOOOOOOOOOOOOOOOOOR\n");
        message.append(player1.getName());
        message.append(" [");
        message.append(player1.getType());
        message.append("]  VS  ");
        message.append(player2.getName());
        message.append(" [");
        message.append(player2.getType());
        message.append("]");
        message.append("\n");
        message.append(SEPARATOR);
        message.append("\n");
        return message;
    }


    /**
     * @param round The round number for the message
     * @return The round header message with recap of the current game
     */
    public StringBuffer buildRoundHeader(int round) {
        StringBuffer message = new StringBuffer();
        message.append("\n--- Round ");
        message.append(round);
        message.append(" ---\n");
        message.append("  ");
        message.append(player1.getName());
        message.append(" HP: ");
        message.append(player1.getHp());
        message.append("  |  ");
        message.append(player2.getName());
        message.append(" HP: ");
        message.append(player2.getHp());
        message.append("\n");
        return message;
    }


    /**
     * @param character The character attacking
     * @return Player choose with the list of available attacks
     */
    public StringBuffer buildAttackSelectionPrompt(Character character) {
        StringBuffer message = new StringBuffer();
        message.append("\n");
        message.append(character.getName());
        message.append(" (");
        message.append(character.getType());
        message.append(") (HP: ");
        message.append(character.getHp());
        message.append(") — choose an attack:\n");
        int index = 1;
        for(IAttack IAttack : character.getAttacks()) {
            message.append(" ");
            message.append(index);
            message.append(". ");
            message.append(IAttack.getName());
            message.append(" (base damage: ");
            message.append(IAttack.getDamages());
            message.append(")");
            message.append("\n");
            index++;
        }
        return message;
    }

    /**
     * @param attacker The player attacking
     * @param target The player attacked
     * @param attackIndex The index of the attack selected
     * @return The fallback message when the round of the turn is played
     * @throws FightException If the attacker can't attack based on rules of FightService.turnValidator(...)
     */
    public StringBuffer buildTurnResult(Character attacker, Character target, int attackIndex) throws FightException {
        String result = this.fightService.runTurn(attacker, target, attackIndex);
        StringBuffer message = new StringBuffer();
        message.append(" #");
        message.append(result);
        message.append("\n");
        message.append("  ");
        message.append(target.getName());
        message.append(" HP: ");
        message.append(target.getHp());
        message.append("\n");
        return message;
    }

    /**
     * @return Winning message
     */
    public StringBuffer buildVictoryScreen() {
        Character winner = this.fightService.winner(player1, player2);
        StringBuffer message = new StringBuffer();
        message.append("\n");
        message.append(SEPARATOR);
        message.append("\n");
        if (winner != null) {
            message.append(" ");
            message.append(winner.getName().toUpperCase());
            message.append(" (");
            message.append(winner.getType());
            message.append(") WINS!");
            message.append("\n");
        }
        message.append(SEPARATOR);
        message.append("\n");
        return message;
    }

    /**
     * @return Play again message
     */
    public StringBuffer buildPlayAgainPrompt() {
        StringBuffer message = new StringBuffer();
        message.append("\nWant to play again? (y/n): ");
        return message;
    }


    /**
     * @param minVal The minimum value to select for the choice
     * @param maxVal The maximum value to select for the choice
     * @return Generic message for different ask in the gameplay
     */
    public StringBuffer buildAskANumberBetween(int minVal, int maxVal) {
        StringBuffer message = new StringBuffer();
        message.append("Make a choice between ");
        message.append(minVal);
        message.append(" and ");
        message.append(maxVal);
        message.append(" ");
        return message;
    }

    /**
     *
     * @param minVal The minimum value to select for the choice
     * @param maxVal The maximum value to select for the choice
     * @return The initial message with the "Error" message
     */
    public StringBuffer buildAskANumberBetweenAgain(int minVal, int maxVal) {
        StringBuffer message = new StringBuffer();
        message.append(this.buildWrongAskANumberBetween(minVal, maxVal));
        message.append(this.buildAskANumberBetween(minVal, maxVal));
        return message;
    }

    /**
     * @param minVal The minimum value to select for the choice
     * @param maxVal The maximum value to select for the choice
     * @return The error message if wrong number picked
     */
    private StringBuffer buildWrongAskANumberBetween(int minVal, int maxVal) {
        StringBuffer message = new StringBuffer();
        message.append("Please enter a number between ");
        message.append(minVal);
        message.append(" and ");
        message.append(maxVal);
        message.append(".\n");
        return message;
    }

    /**
     * @param choice The number selected by the player
     */
    public void selectPlayer1(int choice) {
        this.player1 = this.fightService.getCharacter(choice - 1);
    }

    /**
     * @param choice The number selected by the player
     */
    public void selectPlayer2(int choice) {
        this.player2 = this.fightService.getCharacter(choice - 1);
    }

    /**
     * @return If game is ended
     */
    public boolean isGameOver() {
        return this.fightService.winner(player1, player2) != null;
    }

    public Character getPlayer1() {
        return this.player1;
    }

    public Character getPlayer2() {
        return this.player2;
    }

    public IFightService getFightService() {
        return this.fightService;
    }

    public void setFightService(IFightService fightService) {
        this.fightService = fightService;
    }

    public int getAttackCount(Character character) {
        return character.getAttacks().size();
    }

    public boolean readPlayAgain() {
        String answer = scanner.nextLine().trim().toLowerCase();
        return answer.equals("y");
    }

    public void close() {
        scanner.close();
    }

    private String scannerReadInput() {
        return scanner.nextLine().trim();
    }

    /**
     *
     * @param input The player input value
     * @param minVal The minimum selectable value
     * @param maxVal The maximum selectable value
     * @return If the entered number is correct between the range
     * @throws NumberFormatException If the selected value is not a number. (Only for exercice, can be removed with some checks)
     */
    public boolean isValidInt(int input, int minVal, int maxVal) throws NumberFormatException {
        try {
            return input >= minVal && input <= maxVal;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage());
        }
    }

    public int getInputValue() {
        return Integer.parseInt(this.scannerReadInput());
    }
}