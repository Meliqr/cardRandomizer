package us.kindt.cardapp;

import java.util.List;

public class BoosterPack {
	private String setName;
	private String setAbbreviation;
	private List<Card> cardsList;
	private int packId;

	public BoosterPack(String setName, String setAbbreviation, List<Card> cardsList, int packId) {
		this.setName = setName;
		this.setAbbreviation = setAbbreviation;
		this.cardsList = cardsList;
		this.packId = packId;
	}

	public BoosterPack(){
	}

	public String getSetName() {
		return setName;
	}

	public void setSetName(String setName) {
		this.setName = setName;
	}

	public String getSetAbbreviation() {
		return setAbbreviation;
	}

	public void setSetAbbreviation(String setAbbreviation) {
		this.setAbbreviation = setAbbreviation;
	}

	public int getPackId() {
		return packId;
	}

	public void setPackId(int packId) {
		this.packId = packId;
	}

	public List<Card> getCards() {
		return cardsList;
	}

	public void setCards(List<Card> cardsList) {
		this.cardsList = cardsList;
	}
	
	
}
