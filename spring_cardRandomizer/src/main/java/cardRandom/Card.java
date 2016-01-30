package cardRandom;

public class Card {
	private String cardName;
	private String cardSet;
	private String cardSetAbbreviation;
	private String rarity;
	private int cardId;
	
	public Card(String cardName, String cardSet, String cardSetAbbreviation, String rarity, int cardId) {
		this.cardName = cardName;
		this.cardSet = cardSet;
		this.cardSetAbbreviation = cardSetAbbreviation;
		this.rarity = rarity;
		this.cardId = cardId;
	}
	public Card(String cardSet, String cardSetAbbreviation, String rarity, int cardId){
		this.cardId = cardId;
		this.cardSet = cardSet;
		this.cardSetAbbreviation = cardSetAbbreviation;
		this.rarity = rarity;
	}
	
	public Card(){
	}
	
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardSet() {
		return cardSet;
	}
	public void setCardSet(String cardSet) {
		this.cardSet = cardSet;
	}
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public String getRarity() {
		return rarity;
	}
	public void setRarity(String rarity) {
		this.rarity = rarity;
	}
	public String getCardSetAbbreviation() {
		return cardSetAbbreviation;
	}
	public void setCardSetAbbreviation(String cardSetAbbreviation) {
		this.cardSetAbbreviation = cardSetAbbreviation;
	}
	
	
}
