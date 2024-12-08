/**
 * This class sets up the basic logic of the game, manages user inputs, and displays the results
 */

import javax.swing.plaf.basic.BasicRootPaneUI;
import java.util.Scanner;

public class BattleshipGame {

/////////////////////////////////////////--------------- Fields ---------------/////////////////////////////////////////

    private static int row;
    private static int col;
    private static Scanner sc = new Scanner(System.in);

///////////////////////////////////////////////////////// main /////////////////////////////////////////////////////////

    public static void main(String[] args) {
        //print instructions
        instructions();
        boolean play = true;
        //main game loop
        while(play) {
            //initialize ocean
            Ocean ocean = new Ocean();
            ocean.placeAllShipsRandomly();
            ocean.print();
            boolean allSunk = false;
            //shooting loop
            while(!allSunk) {
                inputCheckShoot();
                ocean.shootAt(row, col);
                ocean.print();
                if(ocean.isGameOver()) {
                    System.out.println("🎉 Game over! Well done 🫡");
                    System.out.println("You fired " + ocean.getShotsFired() + " shots.");
                    allSunk = true;
                }
            }
            play = inputCheckYN();
        }
        sc.close();
    }

///////////////////////////////////////////////////// instructions /////////////////////////////////////////////////////

    /**
     * Prints instructions
     */
    private static void instructions() {
        System.out.println("👋Hello! Welcome to single-player Battleship! ⚓️");
        System.out.println("There are: ");
        System.out.println("· 1 Battleship (length 1)");
        System.out.println("· 2 Cruisers (length 2)");
        System.out.println("· 3 Destroyers (length 3)");
        System.out.println("· 4 Submarines (length 4)");
        System.out.println("Your goal is try to hit all of them. Good luck! 😬");
    }

////////////////////////////////////////////////// inputCheckShoot /////////////////////////////////////////////////////

    /**
     * Checks whether the row & column numbers entered by the users are valid, and set row & col to valid inputs
     */
    private static void inputCheckShoot() {
        String input;
        String cleanedInput;
        while(true) {
            System.out.println("Where would you like to shoot? 💣");
            System.out.print("Please enter the row and column number: ");
            input = sc.nextLine();
            //use regex to clean input
            cleanedInput = input.replaceAll("[^0-9]", "");
            //if user input didn't contain any valid characters
            if (cleanedInput.isEmpty()) {
                System.out.println("😠Invalid input! Please try again.");
                System.out.println();
                continue;
            }
            //if user entered too many numbers
            if (cleanedInput.length() > 2) {
                System.out.println("😠Too many digits! Row & column should range from 0-9. Please try again.");
                System.out.println();
                continue;
            }
            if (cleanedInput.length() == 1) {
                System.out.println("😠You only entered one number! Please enter the column number too.");
                System.out.println();
            } else {
                row = cleanedInput.charAt(0) - '0';
                col = cleanedInput.charAt(1) - '0';
                break;
            }
        }
    }

//////////////////////////////////////////////////// inputCheckYN //////////////////////////////////////////////////////

    /**
     * Check whether the "yes / no" input is valid.
     * @return true if the player wants to play again, false otherwise
     */
    private static boolean inputCheckYN() {
        String input;
        while(true) {
            System.out.println("🚢Would you like to play again? (y/n)");
            input = sc.nextLine();
            if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                return true;
            } else if (input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) {
                return false;
            } else {
                System.out.println("😠Invalid input! Please try again.");
            }
        }
    }

}
