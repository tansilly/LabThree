package pokerBase;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pokerEnums.eRank;
import pokerEnums.eSuit;

@XmlRootElement
public class Deck {
	
	@XmlElement (name="Remaining Card")
	private ArrayList<Card> cards;
	private int numJoker = 2;
	
	public Deck() {

		//	Create an ArrayList of Cards, add each card
		ArrayList<Card> MakingDeck = new ArrayList<Card>();
		for (short i = 0; i <= 3; i++) {
			eSuit SuitValue = eSuit.values()[i];			
			for (short j = 0; j <= 12; j++) {
				eRank RankValue = eRank.values()[j];				
				Card NewCard = new Card(SuitValue,RankValue, (13 * i) + j+1);
				MakingDeck.add(NewCard);
			}
		}
		
		//	Set the instance variable
		cards = MakingDeck;
		ShuffleCards();

	}
	
	// Making a deck with jokers in it
	public Deck(int numJokers){
		this();
		if (numJoker !=0){
			for (int i = 0; i < numJokers; i++){
				Card NewCard = new Card(eSuit.JOKER, eRank.JOKER, cards.size());
				cards.add(NewCard);
			}
		}
		ShuffleCards();
	}
	
	// making a deck with wildcards and jokers
	public Deck(int numJokers, ArrayList<Card> WildCard){
		this(numJokers);
		for (Card i : WildCard){
			for (Card CardDeck : cards){
				if ((CardDeck.getRank() == i.getRank()) && 
						(CardDeck.getSuit() == i.getSuit())){
					i.setWild();
				}
			}
		}
		ShuffleCards();
	}
	
	private void ShuffleCards()
	{
		//	Shuffle the cards
		Collections.shuffle(cards);
	}

	public Card drawFromDeck() {
		// Removes the first card from the deck and return the card
		Card FirstCard = cards.get(0);
		cards.remove(0);
		return FirstCard;
	}

	public int getTotalCards() {
		// Returns the total number of cards still in the deck
		return cards.size();
	}
	
	public ArrayList<Card> getCards()
	{
		return this.cards;
	}
	
	public StringWriter SerializeMe()
	{
	    StringWriter sw = new StringWriter();
		try
		{
		    //Write it
		    JAXBContext ctx = JAXBContext.newInstance(Deck.class);
		    Marshaller m = ctx.createMarshaller();
		    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    m.marshal(this, sw);
		    sw.close();			
		}
		catch (Exception ex)
		{
			
		}
    
    return sw;
	}
}