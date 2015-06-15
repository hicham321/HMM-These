import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Classification {
	
	
private void TreatmentDocument() throws FileNotFoundException,UnsupportedEncodingException,IOException{
	
	File file= new File("fichier a classer");
	StopWord st =new StopWord(file);
	
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
 	
 	
   
}
    public static void main(String[] args) {
	long startTime = System.nanoTime();
    //code 
	long endTime = System.nanoTime();
	long duration = (endTime - startTime);
    System.out.println(duration/1000000 +" miliseconds");
    }
}
