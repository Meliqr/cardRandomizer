package cardRandom;

import java.util.ArrayList;

public class CardSet {
	private int numCommon;
	private int numUncommon;
	private int numRare;
	private int numMythic;
	private int numLands;
	private int packSize;
	private boolean hasPremium;
	private String setName;
	private String setAbbreviation;
	
	
	public CardSet(String inName, String inAbrv, int nc, int nu, int nr, int nm, int nl, int pz, boolean hp){
		this.setName = inName;
		this.setAbbreviation = inAbrv;
		this.numCommon = nc;
		this.numUncommon = nu;
		this.numRare = nr;
		this.numMythic = nm;
		this.numLands = nl;
		this.packSize = pz;
		this.hasPremium = hp;
	}
	public CardSet(){
	}
	
	public int getNumCommon() {
		return numCommon;
	}
	public void setNumCommon(int numCommon) {
		this.numCommon = numCommon;
	}
	public int getNumUncommon() {
		return numUncommon;
	}
	public void setNumUncommon(int numUncommon) {
		this.numUncommon = numUncommon;
	}
	public int getNumRare() {
		return numRare;
	}
	public void setNumRare(int numRare) {
		this.numRare = numRare;
	}
	public int getNumMythic() {
		return numMythic;
	}
	public void setNumMythic(int numMythic) {
		this.numMythic = numMythic;
	}
	public int getNumLands() {
		return numLands;
	}
	public void setNumLands(int numLands) {
		this.numLands = numLands;
	}
	public int getPackSize() {
		return packSize;
	}
	public void setPackSize(int packSize) {
		this.packSize = packSize;
	}
	public boolean isHasPremium() {
		return hasPremium;
	}
	public void setHasPremium(boolean hasPremium) {
		this.hasPremium = hasPremium;
	}
	public String getSetName() {
		return setName;
	}
	public void setSetName(String setName) {
		this.setName = setName;
	}
	public String getSetAbreviation() {
		return setAbbreviation;
	}
	public void setSetAbreviation(String setAbreviation) {
		this.setAbbreviation = setAbreviation;
	}
	public BoosterPack generateBoosterPack(int commons, int uncommons, int rares, int lands){
		System.out.println(numCommon + " " + numUncommon + " " + numRare + " " + numMythic + " " + numLands + " " + packSize + " " + hasPremium + " " + setName + " " + setAbbreviation + "\n");	
		int[] alreadyUsedCards= new int[packSize];		
		int curIndex = 0;
		int tempCard = 0;
		int packId = (int) (Math.random() * 700000);
		ArrayList<Card> boosterCardsList = new ArrayList<Card>();
		
		for(int i=0; i<commons;i++){
			tempCard = (int) ((Math.random() * numCommon) + 1);
			if(!checkDupes(alreadyUsedCards, tempCard, curIndex)){
				alreadyUsedCards[curIndex] = tempCard;
				boosterCardsList.add(findCardInfo(tempCard, setName, setAbbreviation, "common"));
				curIndex++;
			}
			else{
				i--;
			}
		}
		
		for(int i=0; i<uncommons;i++){
			tempCard = (int) ((Math.random() * numUncommon) + numCommon + 1);
			if(!checkDupes(alreadyUsedCards, tempCard, curIndex)){
				alreadyUsedCards[curIndex] = tempCard;
				boosterCardsList.add(findCardInfo(tempCard, setName, setAbbreviation, "uncommon"));
				curIndex++;
			}
			else{
				i--;
			}
		}
		if(numMythic != 0){
			int hasMythic = (int) (Math.random()*8);
			if(hasMythic == 7){
				tempCard = (int) ((Math.random() * numMythic) + numCommon + numUncommon + numRare + 1);
				boosterCardsList.add(findCardInfo(tempCard, setName, setAbbreviation, "mythic rare"));
			}
			else{
				tempCard = (int) ((Math.random() * numRare) + numCommon + numUncommon + 1);
				boosterCardsList.add(findCardInfo(tempCard, setName, setAbbreviation, "rare"));
			}
			alreadyUsedCards[curIndex] = tempCard;
			curIndex++;
		}
		else{
			tempCard = (int) ((Math.random() * numRare) + numCommon + numUncommon + 1);
			boosterCardsList.add(findCardInfo(tempCard, setName, setAbbreviation, "rare"));
			alreadyUsedCards[curIndex] = tempCard;
			curIndex++;
		}
		
		if(lands == 1){
			tempCard = (int) ((Math.random() * numLands) + numCommon + numUncommon + numRare + numMythic + 1);
			boosterCardsList.add(findCardInfo(tempCard, setName, setAbbreviation, "land"));
			alreadyUsedCards[curIndex] = tempCard;
			curIndex++;
		}
				
		return new BoosterPack(setName, setAbbreviation, boosterCardsList, packId);
	}
	
	public boolean checkDupes(int[] checkArray, int checkVal, int arrayIndex){
		for(int i=0;i<arrayIndex;i++){
			if(checkArray[i] == checkVal){
				return true;
			}
		}
		return false;
	}
	public Card findCardInfo(int cardId, String cardSet, String cardSetAbbreviation, String rarity){
		return new Card(cardSet, cardSetAbbreviation, rarity, cardId);
	}
	
}
