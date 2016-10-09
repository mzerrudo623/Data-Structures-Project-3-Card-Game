/*Mishael Zerrudo
  CSC 311 Project 3
  A program that plays a simple two player card game
  November 14, 2015
*/

import java.util.*;
/**********Node Implementation**********/
class Node
{
	Object data;
	Node next;
	
	public Node(Object value)
	{
		data = value;
		next = null;
	}
	
	public void printList(Node head)
	{
		Node temp = head;
		System.out.print(temp.data + " ");
		if (temp.next != null){
			printList(temp.next);
		}
	}
}

/**********LinkedList Implementation**********/
class LinkedList
{
	public Node head = null;
	private int size = 0;
	
	//adds at the front of the list
	public void addFirst(Object item)
	{
		Node fNode = new Node(item);
		fNode.next = head;
		head = fNode;
		size++;
	}
	
	//adds after the node
	public void addAfter(Object item, Node node)
	{
		Node newNode = new Node(item);
		newNode.next = node.next;
		node.next = newNode;
		size++;
	}
	
	//removes the first on the list
	public Object removeFirst()
	{
		if(size > 0)
		{
			Node temp = head;
			head = temp.next;				//will this work?
			size--;
			return temp.data;
		}
		return null;
	}
	
	//removes node after rNode
	public Object removeAfter(Node rNode)
	{
		if (rNode.next != null)
		{
			Node temp = rNode.next;
			rNode.next = temp.next;
			size--;
			return temp.data;
		}
		return null;
	}
	
	//removes at the end of the list
	public Object remove()
	{
		if (size > 0)
		{
			Node last = getNode(size - 1);
			Node temp = getNode(size - 2);
			temp.next = null;
			size--;
			return last.data;
		}
		return null;
	}
	
	//gets the node located at the index
	public Node getNode(int index)
	{
		Node node = head;
		for (int i = 0; i < index && node != null; i++)
			node = node.next;
		return node;
	}
	
	//gets data of a node at the index
	public Object getNodeData(int index)
	{
		Node node = head;
		for (int i = 0; i <= index && node != null; i++)
			node = node.next;
		return node.data;
	}
	
	//adds at the end of the list
	public void add(Object item)
	{
		if (size == 0)
		{
			addFirst(item);
		}
		else
		{
			addAfter(item, getNode(size - 1));
		}
	}
	
	//adds at the index
	public void add(Object item, int index)
	{
		if (index < 0 || index > size)
			return;
		if(index == 0)
			addFirst(item);
		else
			addAfter(item, getNode(index-1));
	}
	
	//prints data of the linked list
	public String toString()
	{
		Node nodeRef = head;
		StringBuilder result = new StringBuilder();
		while (nodeRef != null)
		{
			result.append(nodeRef.data);
			if (nodeRef.next != null)
			{
				result.append(" ==> ");
			}
			nodeRef = nodeRef.next;
		}
		return result.toString();
	}
	
}

/**********Stack Implementation**********/
//stacks are used in the printTable method
class Stack {
	
    private int top;
    int size;
    Object[] stack ;
	
    public Stack(int arraySize){
        size=arraySize;
        stack= new Object[size];
        top=-1;
    }
    
    public void push(Object value){
        if(top==size-1){
            System.out.println("Stack is full, can't push a value");
        }
        else{

            top=top+1;
            stack[top]=value;
        }
    }
    public Object pop(){
		Object result = null;
        if(!isEmpty()){
			result = stack[top];
            top=top-1;
		}
        else{
            System.out.println("Can't pop...stack is empty");
        }
		
		return result;
    }
	
	public Object peek(){
		Object result = null;
        if(!isEmpty()){
			result = stack[top];
		}
        else{
            System.out.println("Can't pop...stack is empty");
        }
		
		return result;
	}
	
    public boolean isEmpty(){
        return top==-1;
    }
    public void display(){

        for(int i=0;i<=top;i++){
            System.out.print(stack[i]+ " ");
        }
        System.out.println();
    }
}

/**********Card Implementation**********/
//Card class used in an example in Chapter 11 of Java Programming From the Ground Up textbook
class Card
{
	private int suit; //1 = Hearts, 2 = Diamonds, 3 = Clubs, 4 = Spades
	private int value; // 1 = Ace...11 = Jack, 12 = Queen, 13 = King
	
	public Card() //Ace of Hearts, by default
	{
		suit = 1;
		value = 1;
	}
	
	public Card(int s, int v) //two-argument constructor
	{
		suit = s;
		value = v;
	}
	
	public int getSuit()
	{
		return suit;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public void setSuit(int s)
	{
		suit = s;
	}
	
	public void setValue(int v)
	{
		value = v;
	}
	
	public String getName() //returns string, e.g., "Ace of Hearts"
	{
		String name = "";
		if (value == 1)
			name = "Ace of ";
		else if (value == 11)
			name = "Jack of ";
		else if (value == 12)
			name = "Queen of ";
		else if (value == 13)
			name = "King of ";
		else //use numerical value, "2 of ", "3 of ", etc.
			name = value + " of ";
			
		//Add on the suit
		
		if (suit == 1)
			name += "Hearts";
		else if (suit == 2)
			name += "Diamonds";
		else if (suit == 3)
			name += "Clubs";
		else
			name += "Spades";
		return name;
	}
}

/**********Deck Implementation**********/
//Deck class used in an example in Chapter 11 of Java Programming From the Ground Up textbook
class Deck
{
	private Card deck[];
	private int next; //holds position of next card to be dealt
	
	public Deck()
	{
		deck = new Card[53]; //does not use position 0, uses 1...52
		
		for (int rank = 1; rank <= 13; rank++)
		{ //place cards in order in deck
			deck[rank] = new Card(1, rank); //rank of first suit e.g., 3 of hearts
			deck[rank + 13] = new Card(2, rank); //rank of second suit e.g., 3 of diamonds
			deck[rank + 26] = new Card(3, rank); //rank of third suit e.g., 3 of clubs
			deck[rank + 39] = new Card(4, rank); //rank of fourth suit e.g., 3 of spades
		}
		next = 1; //first card dealt is deck[next]
	}
	
	public void shuffle()
	{
		Random randomNumber = new Random();
		for (int card = 1; card <= 52; card++)
		{
			//find a random place in the deck
			int rand = randomNumber.nextInt(52) + 1;
			//swap deck[card] with deck[rand]
			Card temp = deck[card];
			deck[card] = deck[rand];
			deck[rand] = temp;
		}
		next = 1; //top card of the deck
	}
	
	public Card deal()
	{
		if (next > 52) //if deck is depleted
			shuffle();
		Card c = deck[next];
		next++;
		return c;
	}
}

/**********Card Game Implementation**********/
public class CardGame
{
	private LinkedList plays;						//holds all the cards played by the players
	private Card[][] hand;							//row 0 holds player 1's hand and row 1 holds player 2's hand
	private int player1Count;						//number of cards player 1 currently holds in his or her hand
	private int player2Count;						//number of cards player 2 currently holds in his or her hand
	private int playCount;							//counts the number of cards played by both players
	
	public CardGame()
	{
		plays = new LinkedList();
		hand = new Card[2][52];			//2 players, each can hold a max of 52 cards
		//each player starts with 26 cards
		player1Count = 26;
		player2Count = 26;
	}
	
	//shuffles the deck and deals the cards to the players
	private void dealCards()
	{
		Deck deck = new Deck();
		deck.shuffle();
		for (int i = 0; i < 26; i++)
		{
			hand[0][i] = deck.deal();			//give player 1 a card
			hand[1][i] = deck.deal();			//give player 2 a card
		}
	}
	
	public void startGame()
	{
		Random rand = new Random();
		int playerTurn = 0;							//determines whose turn it is to play a card
		int round = 0;
		
		dealCards();
		playerTurn = rand.nextInt(2) + 1;			//randomly selects which player starts first
		System.out.println("Player " + playerTurn + " will start\n");
		while (player1Count != 52 && player2Count != 52 && round != 10)
		{	
			printHand();
			printTable();
			
			if (playerTurn == 1)
			{
				System.out.println("Player 1 plays " + hand[0][player1Count - 1].getName() + "\n");
				plays.add(hand[0][player1Count - 1]);
				player1Count--;						//decrease number of cards player 1 has
				playCount++;						//increment the number of cards on the table
				if (checkMatch(playerTurn))			//if player 1 made a match
				{
					round++;						//increment the number of rounds
					addCards(playerTurn);			//adds the cards to the player's hand
					playCount = 0;
				}
				playerTurn++;						//switch to player 2's turn
			}
			else
			{
				System.out.println("Player 2 plays " + hand[1][player2Count - 1].getName() + "\n");
				plays.add(hand[1][player2Count - 1]);
				player2Count--;						//decrease number of cards player 2 has
				playCount++;						//increment the number of cards on the table
				if (checkMatch(playerTurn))			//if player 2 made a match
				{
					round++;						//increment the number of rounds
					addCards(playerTurn);			//add the cards to the player's hand
					playCount = 0;
				}
				playerTurn--;						//switch to player's 1 turn
			}	
		} //end of while loop
		
		printResults();
	}
	
	//checks if the card placed by player matches last card that was placed
	private boolean checkMatch(int player)
	{
		Card temp1;									//needed to check suits of cards
		Card temp2;									//needed to check suits of cards
		
		if (playCount != 1)
		{
			//get the last two cards from the linked list
			temp1 = (Card)plays.remove();
			temp2 = (Card)plays.remove();
			
			//return temp1 and temp 2 back to the linked list
			plays.add(temp2);
			plays.add(temp1);
			
			if (temp1.getSuit() == temp2.getSuit())			//if the suit of the two cards matches
			{
				System.out.println("\nPlayer " + player + " has made a match!\n");
				return true;
			}
		}
		return false;
	}
	
	//places all cards on the table to the winning player's hand
	private void addCards(int player)
	{
		if (player == 1)
		{
			//move cards near beginning of array to the end of the array
			//card at the end of array is the top card on the player's hand
			for (int i = player1Count - 1; i >= 0; i--)
				hand[0][i + playCount] = hand[0][i];
			//move cards played by players into the winning player's hand
			for (int i = 0; i < playCount; i++)
			{
				hand[0][i] = (Card)plays.remove();
				player1Count++;
			}
		}
		else
		{
			//move cards near beginning of array to the end of the array
			//card at the end of array is the top card on the player's hand
			for (int i = player2Count - 1; i >= 0; i--)
				hand[1][i + playCount] = hand[1][i];
			//move cards played by players into the winning player's hand
			for (int i = 0; i < playCount; i++)
			{
				hand[1][i] = (Card)plays.remove();
				player2Count++;
			}
		}
	}
	
	//prints the current card in each player's hand
	private void printHand()
	{
		int tempSize;
		for (int i = 0; i < 2; i++)
		{
			if (i == 0)
				tempSize = player1Count;
			else
				tempSize = player2Count;
				
			System.out.println("Player " + (i + 1) + " current hand: ");
			for (int j = tempSize - 1; j >= 0; j--)
			{
				System.out.println(hand[i][j].getName());
			}
			System.out.println("");
		}
	}
	
	//prints the current cards on the table
	private void printTable()
	{
		Stack temp = new Stack(52);
		
		System.out.println("\nCurrent cards in play:");
		if (playCount == 0)
			System.out.println("None");
		for (int i = 0; i < playCount; i++)
			temp.push(plays.remove()); 					//pushes data in linked list into stack
		for (int i = 0; i < playCount; i++)
		{
			System.out.println(((Card)temp.peek()).getName());		//prints all the value of stack
			plays.add(temp.pop());									//and pop back into linked list
		}
		System.out.println("");
	}
	
	//prints who won the game
	private void printResults()
	{
		System.out.println("Player 1 has " + player1Count + " cards");
		System.out.println("Player 2 has " + player2Count + " cards");
		if (player1Count == 52 || player1Count > player2Count)
			System.out.println("Player 1 wins!");
		else if (player2Count == 52 || player2Count > player1Count)
			System.out.println("Player 2 wins!");
		else
			System.out.println("It's a tie!");
	}
	
	public static void main(String[] args)
	{
		CardGame game = new CardGame();
		game.startGame();
	}
}