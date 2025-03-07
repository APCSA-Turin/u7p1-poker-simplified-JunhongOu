package com.example.project;
import java.util.ArrayList;


public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards;
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<Card>();
        allCards = new ArrayList<Card>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c);
    }

    public void addAllCard(Card c){
        allCards.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){      
       allCards.clear();
       allCards.addAll(hand);
       allCards.addAll(communityCards); //adding all cards within the game so the match can be properly played
       sortAllCards();

       if (isRoyalFlush()){ //conditions for each combinations of the game
            return "Royal Flush";
       }

       if (isStraightFlush()){
            return "Straight Flush";
       }

       if (isFourOfAKind()){
            return "Four of a Kind";
       }

       if (isFullHouse()){
            return "Full House";
       }
       
       if (isFlush()){
            return "Flush";
       }

       if (isStraight()){
            return "Straight";
       }

       if (isThreeOfAKind()){
            return "Three of a Kind";
       }

       if (isTwoPair()){
            return "Two Pair";
       }

       if (isPair()){
            return "A Pair";
       }

       if (allCards.get(4).getRank() != communityCards.get(0).getRank() && allCards.get(4).getRank() != communityCards.get(1).getRank() && allCards.get(4).getRank() != communityCards.get(2).getRank()){
            return "High Card"; //finds the highest ranked card if the other conditions aren't fulfilled
       }
            
       return "Nothing"; //returns nothing if none of the conditions are fulfilled
}

public void sortAllCards(){
    boolean wrongSpot = true; //if the ranks aren't in the correct indexes
    int num = 0;
    int currentIdx = 0;
    Card placeholder = null; //null as the starting value before swapping happens

    for (int x = 0; x < allCards.size(); x++){ //traverses through the allCards list first
        num = Utility.getRankValue(allCards.get(x).getRank());
        wrongSpot = true;
        currentIdx = x - 1;
            while (wrongSpot && currentIdx > -1){ //to prevent out of bounds issue
                if (num < Utility.getRankValue(allCards.get(currentIdx).getRank())){
                    placeholder = allCards.get(currentIdx);
                    allCards.set(currentIdx, allCards.get(currentIdx + 1));
                    allCards.set(currentIdx + 1, placeholder); //swaps the cards to their correct indexes
            } 
                else {
                    wrongSpot = false; //only if the card was already in its appropriate index
            }
            
            currentIdx--;
        }
    }
}
    

public ArrayList<Integer> findRankingFrequency(){
    ArrayList<Integer> newArr = new ArrayList<>();
    for (int i = 0; i < 13; i++){
        newArr.add(0);
    }
        for (int x = 0; x < 5; x++){ //outer loop to traverse through suits list
            for (int y = 0; y < 13; y++){ //inner loop to traverse through ranks list
                if (allCards.get(x).getRank().equals(ranks[y])){
                    newArr.set(y, newArr.get(y) + 1); //search for new rank amount after a repeat of the previous rank is found
                }
            }
        }
        
        return newArr;
    }

    public ArrayList<Integer> findSuitFrequency(){
        ArrayList<Integer> newArr = new ArrayList<>();
        for (int i = 0; i < 13; i++){
            newArr.add(0);
        }
        
        for (int x = 0; x < 5; x++){ //outer loop to traverse through suits list
            for (int y = 0; y < 13; y++){ //inner loop to traverse through ranks list
                if (allCards.get(x).getSuit().equals(ranks[y])){
                    newArr.set(y, newArr.get(y) + 1); //search for new suit amount after a repeat of the previous suit is found
                }
            }
        }
        
        return newArr;
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }

public boolean isRoyalFlush(){
    sortAllCards(); //To organize the cards due to flush
        if (Utility.getRankValue(allCards.get(0).getRank()) == 10 && isFlush()){ //If the combo goes from 10 to ace + is a flush
            return true;
        }
        else { 
            return false;
        }
}

public boolean isStraightFlush(){
    if (isFlush() && isStraight()){
        return true; //returns true if the combo is both a flush and a straight
    }
        return false;
}

public boolean isFourOfAKind(){
    ArrayList<Integer> rank = new ArrayList<Integer>();
    rank = findRankingFrequency();
    for (int x = 0; x < rank.size(); x++){
      if (rank.get(x) == 4){
          return true; //returns true if there's a squad of the same rank
      }
    }
    
    return false;
  }

public boolean isFullHouse(){
    if (isThreeOfAKind() && isPair()){
        return true;
    }
    else {
        return false;
    }
}

public boolean isFlush(){
    String sameSuit = allCards.get(0).getSuit();
    
    for (int x = 1; x < 5; x++){
        if (!allCards.get(x).getSuit().equals(sameSuit)){
            return false; //false if the 5 cards doesn't have the same suit
        }
    }
    
    return true; //returns true if 5 cards has the same suit
}

public boolean isStraight(){
    sortAllCards(); //organizes the cards because straight goes by numerical order
    
    for (int x = 0; x < allCards.size() - 1; x++){
        if (Utility.getRankValue(allCards.get(x).getRank()) + 1 != Utility.getRankValue(allCards.get(x + 1).getRank())){
            return false;
        }
    }
        
    return true; //returns true if there are 5 cards that has consecutive ranks by order
}

public boolean isThreeOfAKind(){
    ArrayList<Integer> rank = new ArrayList<Integer>();
    rank = findRankingFrequency();
      
    for (int x = 0; x < rank.size(); x++){
        if (rank.get(x) == 3){
            return true; //returns true if there's 3 cards of the same rank
        }
    }
        
    return false;
}

public boolean isTwoPair(){
    ArrayList<Integer> rank = new ArrayList<Integer>();
    rank = findRankingFrequency();
    int counter = 0;
    
    for (int x = 0; x < rank.size(); x++){
        if (rank.get(x) == 2){ //determining if there are two pairs that each have the same ranks
            counter++; 
            rank.remove(x); //removes already found rank frequency to search for a new possible pair
            x--; //to prevent the original index value from changing
        }
    }
    if (counter == 2){
        return true; //returns true if two pairs of the same ranks are found respectively
    }
    else {
        return false;
    }
}

public boolean isPair(){
    ArrayList<Integer> rank = new ArrayList<Integer>();
    rank = findRankingFrequency();
    
    for (int i = 0; i < rank.size(); i++){
        if (rank.get(i) == 2){
            return true; //returns true if there's a pair of cards with the same rank
        }
    }
    
    return false;
  }    
}
