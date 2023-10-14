import java.util.ArrayList;
import java.util.Collections;

// Card class represents each individual card in the deck
class Card {
    private int value; // each card has a value (2-14)
    private String name; // and a name (e.g. "King of Spades")

    public Card(int value, String name) {
        this.value = value;
        this.name = name;
    }

    // describe method prints out the name of the card
    public void describe() {
        System.out.println(this.name);
    }

    // getter methods for the value and name fields
    public int getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

    // setter methods for the value and name fields
    public void setValue(int value) {
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }
}

// Deck class represents the entire deck of cards
class Deck {
    private ArrayList<Card> cards; // a deck is made up of an ArrayList of Card objects

    public Deck() {
        this.cards = new ArrayList<Card>(); // initialize the deck with an empty list
        // create all 52 cards in the deck, using nested loops to loop through values and suits
        for (int i = 2; i <= 14; i++) {
            for (String suit : new String[]{"Diamonds", "Clubs", "Hearts", "Spades"}) {
                String name;
                if (i == 11) {
                    name = "Jack of " + suit;
                } else if (i == 12) {
                    name = "Queen of " + suit;
                } else if (i == 13) {
                    name = "King of " + suit;
                } else if (i == 14) {
                    name = "Ace of " + suit;
                } else {
                    name = i + " of " + suit;
                }
                Card c = new Card(i, name);
                this.cards.add(c);
            }
        }
    }

    // shuffle method shuffles the order of the cards in the deck using built-in Collections.shuffle()
    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    // draw method removes and returns the top card from the deck
    public Card draw() {
        return this.cards.remove(0); // remove the card at index 0 (the top card on the deck) and return it
    }
}

// Player class represents a player in the game
class Player {
    private ArrayList<Card> hand; // a player has a hand, which is a list of Card objects
    int score; // a player's score starts at 0 and increments for each point they earn
    private String name; // a player has a name (e.g. "Player 1")

    public Player(String name) {
        this.hand = new ArrayList<Card>(); // initialize the hand with an empty list
        this.score = 0; // initialize the score to 0
        this.name = name;
    }

    // describe method prints out the player's name, score, and the cards in their hand
    public void describe() {
        System.out.println("Player: " + this.name);
        System.out.println("Score: " + this.score);
        System.out.println("Cards in hand:");
        for (Card c : this.hand) {
            c.describe();
        }
    }

    // flip method removes and returns the top card from the player's hand (same as Deck.draw())
    public Card flip() {
        return this.hand.remove(0); // remove the card at index 0 (the top card in the hand) and return it
    }

    // draw method adds a card from the deck to the player's hand (same as Deck.draw())
    public void draw(Deck deck) {
        Card c = deck.draw();
        this.hand.add(c);
    }

    // incrementScore method adds 1 to the player's score
    public void incrementScore() {
        this.score++;
    }
}

// App class runs the game
public class CardGame {
    public static void main(String[] args) {
        // create a deck and shuffle it
        Deck deck = new Deck();
        deck.shuffle();

        // create two players and deal the cards to them (alternating between them)
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        for (int i = 0; i < 52; i++) {
            if (i % 2 == 0) {
                player1.draw(deck);
            } else {
                player2.draw(deck);
            }
        }

        // play the game for 26 rounds (each player flips over one card per round)
        for (int i = 0; i < 26; i++) {
            // each player flips over a card
            Card card1 = player1.flip();
            Card card2 = player2.flip();

            // print out the cards that were flipped
            System.out.println("Player 1 flipped:");
            card1.describe();
            System.out.println("Player 2 flipped:");
            card2.describe();

            // compare the cards and award points accordingly
            if (card1.getValue() > card2.getValue()) {
                player1.incrementScore();
                System.out.println("Player 1 receives a point!");
            } else if (card2.getValue() > card1.getValue()) {
                player2.incrementScore();
                System.out.println("Player 2 receives a point!");
            } else {
                System.out.println("It's a tie!");
            }
        }

        // print out the final scores and the winner (or if it's a tie)
        System.out.println("Final score:");
        player1.describe();
        player2.describe();
        if (player1.score > player2.score) {
            System.out.println("Player 1 Wins!");
        } else if (player2.score > player1.score) {
            System.out.println("Player 2 Wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }
}
            	
