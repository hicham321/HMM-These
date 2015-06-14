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
		
//		for(int i =0; i<list.size();i++){
//			
//			System.out.println(" "+list.get(i));
//		}
		//use hash map here to test:
		Map <String,Integer> mapfortext=new HashMap<String,Integer>();
		/*for(int i=0; i<list.size();i++){
			mapfortext.put(list.get(i), 1);
			
		}*/
		//System.out.println(mapfortext);
		
		for(int k=0;k<list.size();k++){
			int compteur=0;
			for(int j=0;j<list.size();j++){
				
				if(list.get(k).equals(list.get(j))){
					
					compteur++;
					//mapfortext.put(list.get(k), mapfortext.get(list.get(k))+1);
					// list.remove(k);
				}
			}

			mapfortext.put(list.get(k), compteur);
		}
		//System.out.println(mapfortext);



		 ArrayList<String> sousListTrie=new ArrayList<String>();

		//for(int j =0;j<mapfortext.size();j++){
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
	// }
		//System.out.println(list);
		//System.out.println(sousListTrie);
		
		
		//System.out.println("list"+list.size()+"map"+mapfortext.size()+"trie"+sousListTrie.size());
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
        System.out.println(duration/1000000 +" miliseconds");
        
        
        } 
}



