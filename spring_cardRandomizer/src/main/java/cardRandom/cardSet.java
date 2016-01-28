package cardRandom;

public class cardSet {
	private int numCommon;
	private int numUncommon;
	private int numRare;
	private int numMythic;
	private int numLands;
	private int packSize;
	private boolean hasPremium;
	private String setName;
	private String setAbrv;
	
	
	public cardSet(String inName, String inAbrv, int nc, int nu, int nr, int nm, int nl, int pz, boolean hp){
		setName = inName;
		setAbrv = inAbrv;
		numCommon = nc;
		numUncommon = nu;
		numRare = nr;
		numMythic = nm;
		numLands = nl;
		packSize = pz;
		hasPremium = hp;
		
	}
	
	public void setNumCommon(int nm){
		numCommon = nm;
	}
	
	public void setNumUncommon(int nm){
		numUncommon = nm;
	}
	public void setNumRare(int nm){
		numRare = nm;
	}
	public void setNumMythic(int nm){
		numMythic = nm;
	}
	
	public void setLands(int nm){
		numLands = nm;
	}
	public void setPackSize(int nm){
		packSize = nm;
	}
	
	public int getNumCommon(){
		return numCommon;
	}
	
	public int getNumUncommon(){
		return numUncommon;
	}
	
	public int getNumRare(){
		return numRare;
	}
	
	public int getNumMythic(){
		return numMythic;
	}
	
	public int getNumLands(){
		return numLands;
	}
	
	public int getPackSize(){
		return packSize;
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
