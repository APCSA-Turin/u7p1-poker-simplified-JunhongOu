package com.example.project;
import java.util.ArrayList;
import java.util.Collections;

public class Deck{
    private ArrayList<Card> cards;

    public Deck(){
        cards = new ArrayList<>();
        initializeDeck();
        shuffleDeck();
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    public void initializeDeck(){ //hint.. use the utility class
        String[] suits = Utility.getSuits();
        String[] ranks = Utility.getRanks();
        
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 13; j++){
            cards.add(new Card(ranks[j], suits[i]));
        }
    }
        
    }

    public void shuffleDeck(){ //You can use the Collections library or another method. You do not have to create your own shuffle algorithm
        Collections.shuffle(cards);
    }

    public Card drawCard(){
        if (isEmpty()){
            return null;

        }
        return cards.remove(0);
}

    public boolean isEmpty(){
        return cards.isEmpty();
    }
}