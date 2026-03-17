package ch.hearc.ig.hyvolgor.app;

import ch.hearc.ig.hyvolgor.business.FightException;
import ch.hearc.ig.hyvolgor.service.FightService;
import ch.hearc.ig.hyvolgor.service.GameService;

public class Main {

    public static void main(String[] args) {
        GameService controller = new GameService();
        System.out.print(controller.buildWelcome());

        boolean playAgain = true;
        while (playAgain) {
            try {
                //Init the new game
                System.out.print(controller.buildNewGameHeader());
                controller.setFightService(new FightService());

                int charMax = controller.getFightService().getAvailableCharacters().size();

                // Player 1 character selection
                System.out.print(controller.buildPlayerSelectionPrompt(1));
                System.out.print(controller.buildAskANumberBetween(1, charMax));
                int input = controller.getInputValue();

                //Get input for character player 1 selection
                while (!controller.isValidInt(input, 1, charMax)) {
                    System.out.print(controller.buildAskANumberBetweenAgain(1, charMax));
                    input = controller.getInputValue();
                }
                controller.selectPlayer1(input);

                // The selected is removed
                charMax--;

                // Player 2 character selection
                System.out.print(controller.buildPlayerSelectionPrompt(2));
                System.out.print(controller.buildAskANumberBetween(1, charMax));
                input = controller.getInputValue();

                //Get input for character player 2 selection
                while (!controller.isValidInt(input, 1, charMax)) {
                    System.out.print(controller.buildAskANumberBetweenAgain(1, charMax));
                    input = controller.getInputValue();
                }
                controller.selectPlayer2(input);

                System.out.print(controller.buildVersusScreen());

                // Fight loop
                int round = 1;
                while (!controller.isGameOver()) {
                    System.out.print(controller.buildRoundHeader(round));

                    // Player 1 turn
                    int max1 = controller.getAttackCount(controller.getPlayer1());
                    System.out.print(controller.buildAttackSelectionPrompt(controller.getPlayer1()));
                    System.out.print(controller.buildAskANumberBetween(1, max1));
                    input = controller.getInputValue();

                    // Player 1 attack choice
                    while (!controller.isValidInt(input, 1, max1)) {
                        System.out.print(controller.buildAskANumberBetweenAgain(1, max1));
                        input = controller.getInputValue();
                    }
                    System.out.print(controller.buildTurnResult(controller.getPlayer1(), controller.getPlayer2(), input - 1));

                    //Leave the loop when a player is dead
                    if (controller.isGameOver()) {
                        break;
                    }

                    // Player 2 turn
                    int max2 = controller.getAttackCount(controller.getPlayer2());
                    System.out.print(controller.buildAttackSelectionPrompt(controller.getPlayer2()));
                    System.out.print(controller.buildAskANumberBetween(1, max2));
                    input = controller.getInputValue();

                    // Player 2 attack choice
                    while (!controller.isValidInt(input, 1, max2)) {
                        System.out.print(controller.buildAskANumberBetweenAgain(1, max2));
                        input = controller.getInputValue();
                    }
                    System.out.print(controller.buildTurnResult(controller.getPlayer2(), controller.getPlayer1(), input - 1));

                    round++;
                }

                System.out.print(controller.buildVictoryScreen());

            } catch (FightException e) {
                System.out.println("[FIGHT ERROR] " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("[INPUT ERROR] " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("[OUT OF RANGE] " + e.getMessage());
            } catch (Exception e) {
                System.out.println("[ERROR UNEXPECTED] " + e.getMessage());
            }

            System.out.print(controller.buildPlayAgainPrompt());
            playAgain = controller.readPlayAgain();
        }

        controller.close();
    }
}