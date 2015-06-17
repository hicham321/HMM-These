import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Classification {
	
	private ArrayList<String> listFinalDocument= new ArrayList<>();
	
	private ArrayList<Double> listProba= new ArrayList<Double>();

	
	public Classification()throws FileNotFoundException,UnsupportedEncodingException,IOException{
		File textFile=new File("C:/Users/Hicham/Desktop/ffff.txt");
		TreatmentDocument(textFile);
		categoriser();
		System.out.println(listProba);
	    Double maxValue=Collections.max(listProba);
         for(int i=0;i<listProba.size();i++){
        	 if(maxValue==listProba.get(i)){
        		 if(i==0){
        			System.out.println("la document est de la category : culture"); 
        		 }
        		 if(i==1){
         			System.out.println("la document est de la category : Economie"); 
         		 }
        		 if(i==2){
         			System.out.println("la document est de la category : politique international"); 
         		 }
        		 if(i==3){
         			System.out.println("la document est de la category : politique local"); 
         		 }
        		 if(i==4){
         			System.out.println("la document est de la category : religion"); 
         		 }
        		 if(i==5){
         			System.out.println("la document est de la category : sport"); 
         		 }       		 
        		 
        		 
        	 }
         }
		
	}
	
private void TreatmentDocument(File textFile) throws FileNotFoundException,UnsupportedEncodingException,IOException{
	
	StopWord st =new StopWord(textFile);
	
	ArrayList<String>list=st.EliminerStopWord();
	//etape de victorization
	Map <String,Integer> mapfortext=new HashMap<String,Integer>();
	
	
	for(int k=0;k<list.size();k++){
		int compteur=0;
		for(int j=0;j<list.size();j++){
			
			if(list.get(k).equals(list.get(j))){
				
				compteur++;
				
			}
		}

		mapfortext.put(list.get(k), compteur);
	}
	

	
	//etape de tri
	
	ArrayList<String> ListTrie=new ArrayList<String>();

	while(mapfortext.size() !=0){	 
	    Integer maxValue=Collections.max(mapfortext.values());
	    for(String mot :mapfortext.keySet()){
	    	if(mapfortext.get(mot)==maxValue){
	    		ListTrie.add(mot);
	    		mapfortext.remove(mot, maxValue);
	    		break;
	    	}
	    }
	}
 	
 	this.listFinalDocument=ListTrie;
   
}

public void categoriser ()throws FileNotFoundException , UnsupportedEncodingException ,IOException{
	String repertoirSport="C:/Users/Hicham/loukam1/sport";
	testerHashmap sport = new testerHashmap(repertoirSport);
	String repertoirCulture="C:/Users/Hicham/loukam1/Culture";
	testerHashmap culture = new testerHashmap(repertoirCulture);
	String repertoirInternational="C:/Users/Hicham/loukam1/International";
	testerHashmap International = new testerHashmap(repertoirInternational);
	String repertoirLocal="C:/Users/Hicham/loukam1/Local";
	testerHashmap Local = new testerHashmap(repertoirLocal);
	String repertoirReligion="C:/Users/Hicham/loukam1/Religion";
	testerHashmap Religion = new testerHashmap(repertoirReligion);
	String repertoirEconomie ="C:/Users/Hicham/loukam1/economie";
	testerHashmap Economie = new testerHashmap(repertoirEconomie);
	double probaSport=1;
	double probaEcono=1;
	double probaInterna=1;
	double probaLocal=1;
	double probaReligion=1;
	double probacCulture=1;

    


	for(int i=0; i<10;i++){
		
		if (sport.LesEtatsFinal.get(i).containsKey(this.listFinalDocument.get(i))){
			String m =this.listFinalDocument.get(i);
			probaSport=probaSport *sport.LesEtatsFinal.get(i).get(m);
		}
		else{ probaSport= probaSport*0.00000000001;
		};
		if (culture.LesEtatsFinal.get(i).containsKey(this.listFinalDocument.get(i))){
			String m =this.listFinalDocument.get(i);
			probacCulture=probacCulture *culture.LesEtatsFinal.get(i).get(m);
		}
		else{ probacCulture= probaSport*0.00000000001;
		};
		if (International.LesEtatsFinal.get(i).containsKey(this.listFinalDocument.get(i))){
			String m =this.listFinalDocument.get(i);
			probaInterna=probaInterna *International.LesEtatsFinal.get(i).get(m);
		}
		else{ probaInterna= probaInterna*0.00000000001;
		};
		if (Local.LesEtatsFinal.get(i).containsKey(this.listFinalDocument.get(i))){
			String m =this.listFinalDocument.get(i);
			probaLocal=probaLocal *Local.LesEtatsFinal.get(i).get(m);
		}
		else{ probaLocal= probaLocal*0.00000000001;
		};
		if (Religion.LesEtatsFinal.get(i).containsKey(this.listFinalDocument.get(i))){
			String m =this.listFinalDocument.get(i);
			probaReligion=probaReligion *Religion.LesEtatsFinal.get(i).get(m);
		}
		else{ probaReligion= probaReligion*0.00000000001;
		};
		if (Economie.LesEtatsFinal.get(i).containsKey(this.listFinalDocument.get(i))){
			String m =this.listFinalDocument.get(i);
			probaEcono=probaEcono *Economie.LesEtatsFinal.get(i).get(m);
		}
		else{ probaEcono= probaEcono*0.00000000001;
		};
	}
	listProba.add(probacCulture);
	listProba.add(probaEcono);
	listProba.add(probaInterna);
	listProba.add(probaLocal);
	listProba.add(probaReligion);
	listProba.add(probaSport);
	

}

    public static void main(String[] args) throws FileNotFoundException , UnsupportedEncodingException ,IOException{
    	
	long startTime = System.nanoTime();
    //code 
	Classification cl =new Classification();
	long endTime = System.nanoTime();
	long duration = (endTime - startTime);
    System.out.println(duration/1000000 +" miliseconds");
    }
}
