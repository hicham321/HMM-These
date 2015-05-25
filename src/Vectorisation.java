import java.util.ArrayList;
import java.util.Arrays;





// cette class doit utilise la class stop words pour faire la vectorisation .

public class Vectorisation {
	private ArrayList<String> ListeDesMotFinale;
	private ArrayList<Integer> listedesfrequence;
	
	public Vectorisation(ArrayList<String>ListeDesMotsFinale){
		
		this.ListeDesMotFinale=ListeDesMotsFinale;
		
	}

	public void vectoriser(){
		
		int[] Vecteur=new int[10];
		
		
		for (int i =0; i<this.ListeDesMotFinale.size();i++){
			int compteur =0;
			for( int j=0; j<this.ListeDesMotFinale.size();j++){
				if(ListeDesMotFinale.get(i).equals(ListeDesMotFinale.get(j))){
					
					compteur++;
				}
		this.listedesfrequence.add(compteur);
			}
			
		}
		
	}
	public static void main(String[] args) {
		
	}
}
