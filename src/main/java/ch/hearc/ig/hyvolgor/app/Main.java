package ch.hearc.ig.hyvolgor.app;

import ch.hearc.ig.hyvolgor.business.FightException;
import ch.hearc.ig.hyvolgor.service.FightService;

public class Main {

    public static void main(String[] args) {
        GameController controller = new GameController();
        System.out.print(controller.buildWelcome());

        boolean playAgain = true;
        while (playAgain) {
            try {
                System.out.print(controller.buildNewGameHeader());
                controller.setFightService(new FightService());

                //Show availables characters
                int charMax = controller.getFightService().getAvailableCharacters().size();

                // Player 1 character selection
                System.out.print(controller.buildPlayerSelectionPrompt(1));
                System.out.print(controller.buildCharacterList());
                System.out.print(controller.buildInputPrompt(1, charMax));
                int input = controller.readInput();

                //Get input for character player 1 selection
                while (!controller.isValidInt(input, 1, charMax)) {
                    System.out.print(controller.buildInvalidInputMessage(1, charMax));
                    System.out.print(controller.buildInputPrompt(1, charMax));
                    input = controller.readInput();
                }
                controller.selectPlayer1(input);

                // Player 2 character selection
                System.out.print(controller.buildPlayerSelectionPrompt(2));
                System.out.print(controller.buildCharacterList());
                System.out.print(controller.buildInputPrompt(1, charMax));
                input = controller.readInput();

                //Get input for character player 2 selection
                while (!controller.isValidInt(input, 1, charMax)) {
                    System.out.print(controller.buildInvalidInputMessage(1, charMax));
                    System.out.print(controller.buildInputPrompt(1, charMax));
                    input = controller.readInput();
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
                    System.out.print(controller.buildInputPrompt(1, max1));
                    input = controller.readInput();

                    // Player 1 attack choice
                    while (!controller.isValidInt(input, 1, max1)) {
                        System.out.print(controller.buildInvalidInputMessage(1, max1));
                        System.out.print(controller.buildInputPrompt(1, max1));
                        input = controller.readInput();
                    }
                    System.out.print(controller.buildTurnResult(controller.getPlayer1(), controller.getPlayer2(), input - 1));

                    //Leave the loop when a player is dead
                    if (controller.isGameOver()) {
                        break;
                    }

                    // Player 2 turn
                    int max2 = controller.getAttackCount(controller.getPlayer2());
                    System.out.print(controller.buildAttackSelectionPrompt(controller.getPlayer2()));
                    System.out.print(controller.buildInputPrompt(1, max2));
                    input = controller.readInput();

                    // Player 2 attack choice
                    while (!controller.isValidInt(input, 1, max2)) {
                        System.out.print(controller.buildInvalidInputMessage(1, max2));
                        System.out.print(controller.buildInputPrompt(1, max2));
                        input = controller.readInput();
                    }
                    System.out.print(controller.buildTurnResult(controller.getPlayer2(), controller.getPlayer1(), input - 1));

                    round++;
                }

                System.out.print(controller.buildVictoryScreen());

            } catch (FightException e) {
                System.out.println("\n[FIGHT ERROR] " + e.getMessage());
            }

            System.out.print(controller.buildPlayAgainPrompt());
            playAgain = controller.readPlayAgain();
        }

        controller.close();
    }
}