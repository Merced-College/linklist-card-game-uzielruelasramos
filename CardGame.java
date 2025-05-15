//package linkedLists;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Scanner;



public class CardGame {
	
	private static LinkList cardList = new LinkList();  // make list
    private static Scanner scanner = new Scanner(System.in); // Addition - Initiates Scanner.

	public static void main(String[] args) {

		// File name to read from
        String fileName = "cards.txt"; // Ensure the file is in the working directory or specify the full path

        // Read the file and create Card objects
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into components
                String[] details = line.split(","); // Assuming comma-separated values
                if (details.length == 4) {
                    // Parse card details
                    String suit = details[0].trim();
                    String name = details[1].trim();
                    int value = Integer.parseInt(details[2].trim());
                    String pic = details[3].trim();

                    // Create a new Card object
                    Card card = new Card(suit, name, value, pic);

                    // Add the Card object to the list
                    cardList.add(card);
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        // Addition: Blackjack Game Starts.
        
        System.out.println("Welcome to Blackjack.");

        // Addition: Method to draw two cards for the user.
        int playerScore = 0;
        System.out.println("\nYour starting hand:");
        for(int i = 0; i < 2; i++) {
            Card card = cardList.getFirst();
            System.out.println(card);
            playerScore += card.getCardValue();
        }
        
        //Addition: Players turn. Method for player to draw cards, stand, hit, calculate score.
        boolean playerTurn = true;
        while (playerTurn && playerScore < 21) {
            System.out.println("Your current score: " + playerScore);
            System.out.println("Dou you want to hit or stand? (Type h/s) ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("h")) {
                Card card = cardList.getFirst();
                System.out.println("You drew: " + card);
                playerScore += card.getCardValue();
                if (playerScore > 21) {
                    System.out.println("You Lost! Score: " + playerScore);
                    break;
                }
            } else if (choice.equalsIgnoreCase("s")) {
                playerTurn = false;
            } else {
                System.out.println("Invalid input. Remember to type 'h' or 's'.");
            }
        }

        // Addition: Computers turn method. Shows the computers hand and calculates score.
        int computerScore = 0;
        System.out.println("\nComputers Hand: ");
        while (computerScore < 17) {
            Card card = cardList.getFirst();
            System.out.println("Computer drew: " + card);
            computerScore += card.getCardValue();
        }

        // Addition: Method to determine the winner of the round.
        if (playerScore > 21) {
            System.out.println("You bused. Computer won!");
        } else if (computerScore > 21) {
            System.out.println("Computer busted. You won!");
        } else if (playerScore > computerScore) {
            System.out.println("You won!");
        } else if (computerScore > playerScore) {
            System.out.println("Computer won!");
        } else {
            System.out.println("Its a tie!");
        }

	}//end main

}//end class
