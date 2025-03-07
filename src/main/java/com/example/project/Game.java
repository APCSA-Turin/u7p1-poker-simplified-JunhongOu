package com.example.project;
import java.util.ArrayList;

public class Game{
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        p1.sortAllCards();
        p2.sortAllCards(); //organizing the cards so that all of the possible combinations can be done
        Card p1BestCard; 
        Card p2BestCard;
    
            if (Utility.getRankValue(p1.getHand().get(0).getRank()) > Utility.getRankValue(p1.getHand().get(1).getRank())){
                p1BestCard = p1.getHand().get(0);
            } 
            else {
                p1BestCard = p1.getHand().get(1); //returning the higher hand for p1
            } 
                if (Utility.getRankValue(p2.getHand().get(0).getRank()) > Utility.getRankValue(p2.getHand().get(1).getRank())){
                    p2BestCard = p2.getHand().get(0);
                } 
                else {
                    p2BestCard = p2.getHand().get(1); //returning the higher hand for p2
                }
    
            int p1Ranking = 0;
            int p2Ranking = 0;
            p1Ranking = Utility.getHandRanking(p1.playHand(communityCards)); 
            p2Ranking = Utility.getHandRanking(p2.playHand(communityCards)); //to figure out which player has the better hand
    
            if (p1Ranking == p2Ranking){ //in case of a tiebreaker, check for the player with the better card
                if (Utility.getRankValue(p1.getAllCards().get(4).getRank()) > Utility.getRankValue(p2.getAllCards().get(4).getRank())){
                    return "Player 1 wins!";
                }
                if (Utility.getRankValue(p1.getAllCards().get(4).getRank()) < Utility.getRankValue(p2.getAllCards().get(4).getRank())){
                    return "Player 2 wins!";
                }
                if (p1Ranking == 1 || p1Ranking == 5) { //if there's a four/three of a kind and more, return the better card from each player's hand
                    if (Utility.getRankValue(p1BestCard.getRank()) > Utility.getRankValue(p2BestCard.getRank())){
                        return "Player 1 wins!";
                    }
                    if (Utility.getRankValue(p1BestCard.getRank()) < Utility.getRankValue(p2BestCard.getRank())){
                        return "Player 2 wins!";
                    }
                }
                
                return "Tie!"; //returns tie if each player's best card are the same
            }
    
            if (p1Ranking > p2Ranking){ //player with the better combination will win
                return "Player 1 wins!";
            }
            if (p2Ranking > p1Ranking){
                return "Player 2 wins!";
            }
            return "Error";
    }

    public static void play(){ //simulate card playing
    
    }
        
        

}