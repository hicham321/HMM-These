import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class testerHashmap {
	public static void main(String[] args) throws UnsupportedEncodingException,FileNotFoundException,IOException {
		// 3afssa ta3 time 
		long startTime = System.nanoTime();

		StopWord st= new StopWord("C:/Users/Hicham/Desktop/ffff.txt");
		
		ArrayList<String> list=st.EliminerStopWord();
		

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



		 ArrayList<String> sousListTrie=new ArrayList<String>();

		while(mapfortext.size() !=0){	 
		    Integer maxValue=Collections.max(mapfortext.values());
		    for(String mot :mapfortext.keySet()){
		    	if(mapfortext.get(mot)==maxValue){
		    		sousListTrie.add(mot);
		    		mapfortext.remove(mot, maxValue);
		    		break;
		    	}
		    }
		}
	
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
        System.out.println(duration/1000000 +" miliseconds");
        
        
        } 
}



