import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Classification {
	
private void lire() throws FileNotFoundException,UnsupportedEncodingException,IOException{
	StopWord st =new StopWord("fichier a classer");
	
	ArrayList<String>list=st.EliminerStopWord();
	//etape de victorization
	Map <String,Integer> mapfortext=new HashMap<String,Integer>();
	for(int i=0; i<list.size();i++){
		mapfortext.put(list.get(i), 1);
		
	}
	
	for(int k=0;k<list.size();k++){
		for(int j=0;j<list.size();j++){
			
			if(list.get(k).equals(list.get(j))){
				mapfortext.put(list.get(k), mapfortext.get(list.get(k))+1);
				list.remove(k);
			}
		}
	}
	
	//etape de tri
	
 	
 	
 	
   
}
}
