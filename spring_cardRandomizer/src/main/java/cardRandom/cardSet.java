package cardRandom;

public class CardSet {
	private int numCommon;
	private int numUncommon;
	private int numRare;
	private int numMythic;
	private int numLands;
	private int packSize;
	private boolean hasPremium;
	private String setName;
	private String setAbreviation;
	
	
	public CardSet(String inName, String inAbrv, int nc, int nu, int nr, int nm, int nl, int pz, boolean hp){
		this.setName = inName;
		this.setAbreviation = inAbrv;
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
		return setAbreviation;
	}
	public void setSetAbreviation(String setAbreviation) {
		this.setAbreviation = setAbreviation;
	}
	public int[] generateBoosterPack(int commons, int uncommons, int rares, int lands){
		int[] retArry= new int[packSize];		
		int curIndex = 0;
		int tempCard = 0;
		
		for(int i=0; i<commons;i++){
			tempCard = (int) ((Math.random() * numCommon) + 1);
			if(!checkDupes(retArry, tempCard, curIndex)){
				retArry[curIndex] = tempCard;
				curIndex++;
			}
			else{
				i--;
			}
		}
		
		for(int i=0; i<uncommons;i++){
			tempCard = (int) ((Math.random() * numUncommon) + numCommon + 1);
			if(!checkDupes(retArry, tempCard, curIndex)){
				retArry[curIndex] = tempCard;
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
			}
			else{
				tempCard = (int) ((Math.random() * numRare) + numCommon + numUncommon + 1);
			}
			retArry[curIndex] = tempCard;
			curIndex++;
		}
		else{
			tempCard = (int) ((Math.random() * numRare) + numCommon + numUncommon + 1);
			retArry[curIndex] = tempCard;
			curIndex++;
		}
		
		if(lands == 1){
			tempCard = (int) ((Math.random() * numLands) + numCommon + numUncommon + numRare + numMythic + 1);
			retArry[curIndex] = tempCard;
			curIndex++;
		}
		
		
		return retArry;
	}
	
	public boolean checkDupes(int[] checkArray, int checkVal, int arrayIndex){
		for(int i=0;i<arrayIndex;i++){
			if(checkArray[i] == checkVal){
				return true;
			}
		}
		return false;
	}
	
}
