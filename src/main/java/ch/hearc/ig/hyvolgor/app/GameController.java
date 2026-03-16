package ch.hearc.ig.hyvolgor.app;

import ch.hearc.ig.hyvolgor.business.IAttack;
import ch.hearc.ig.hyvolgor.business.Character;
import ch.hearc.ig.hyvolgor.business.FightException;
import ch.hearc.ig.hyvolgor.service.FightService;
import ch.hearc.ig.hyvolgor.service.IFightService;

import java.util.Scanner;

public class GameController {

    private static final String SEPARATOR = "═══════════════════════════════════════════";
    private IFightService fightService;
    private final Scanner scanner;
    private Character player1;
    private Character player2;

    public GameController() {
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
     * @return Player to choose character
     */
    public StringBuffer buildPlayerSelectionPrompt(int playerNumber) {
        StringBuffer message = new StringBuffer();
        message.append("\nPlayer ");
        message.append(playerNumber);
        message.append(", choose your character:\n");
        return message;
    }


    /**
     * @return List of availables characters
     */
    public StringBuffer buildCharacterList() {
        StringBuffer message = new StringBuffer();
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
        return message;
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
     * @param round
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
     * @param character
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
     * @param minVal
     * @param maxVal
     * @return Generic message for different ask in the gameplay
     */
    public StringBuffer buildInputPrompt(int minVal, int maxVal) {
        StringBuffer message = new StringBuffer();
        message.append("Make a choice between ");
        message.append(minVal);
        message.append(" and ");
        message.append(maxVal);
        message.append(" ");
        return message;
    }

    /**
     * @param attacker
     * @param target
     * @param attackIndex
     * @return The fallback message when the round of the turn is played
     * @throws FightException
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
     * @param minVal
     * @param maxVal
     * @return The error message if wrong number picked
     */
    public StringBuffer buildInvalidInputMessage(int minVal, int maxVal) {
        StringBuffer message = new StringBuffer();
        message.append("Please enter a number between ");
        message.append(minVal);
        message.append(" and ");
        message.append(maxVal);
        message.append(".\n");
        return message;
    }

    /**
     * @param choice
     * @throws FightException
     */
    public void selectPlayer1(int choice) throws FightException {
        this.player1 = this.fightService.getCharacter(choice - 1);
    }

    /**
     * @param choice
     * @throws FightException
     */
    public void selectPlayer2(int choice) throws FightException {
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

    private String readRawInput() {
        return scanner.nextLine().trim();
    }

    /**
     *
     * @param input
     * @param minVal
     * @param maxVal
     * @return If the entered number is correct between the range
     * @throws NumberFormatException
     */
    public boolean isValidInt(int input, int minVal, int maxVal) throws NumberFormatException {
        try {
            int value = input;
            return value >= minVal && value <= maxVal;
        } catch (NumberFormatException e) {
            throw new NumberFormatException(e.getMessage());
        }
    }

    public int readInput() {
        return Integer.parseInt(this.readRawInput());
    }
}