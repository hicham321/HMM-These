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
	
	public Classification(File textFile){
		
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

public void categoriser (File corpusSport, File corpusEconomie,File corpusReligion,File corpusLocal,File CorpusInternational, File corpusCulture)throws FileNotFoundException , UnsupportedEncodingException ,IOException{
	String repertoirSport="";
	testerHashmap sport = new testerHashmap(repertoirSport);
	String repertoirCulture="";
	testerHashmap culture = new testerHashmap(repertoirCulture);
	String repertoirInternational="";
	testerHashmap International = new testerHashmap(repertoirInternational);
	String repertoirLocal="";
	testerHashmap Local = new testerHashmap(repertoirLocal);
	String repertoirReligion="";
	testerHashmap Religion = new testerHashmap(repertoirReligion);
	String repertoirEconomie ="";
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
			probacCulture=probacCulture *Religion.LesEtatsFinal.get(i).get(m);
		}
		else{ probacCulture= probaSport*0.00000000001;
		};
		
	}
	
}

    public static void main(String[] args) {
    	
	long startTime = System.nanoTime();
    //code 
	String FichText="C:/Users/Hicham/Desktop/stop-words_arabic_1_ar.txt";
	File fichierAClasser= new File(FichText);
	
	long endTime = System.nanoTime();
	long duration = (endTime - startTime);
    System.out.println(duration/1000000 +" miliseconds");
    }
}
