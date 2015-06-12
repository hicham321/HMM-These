import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class Classification {
	
private void lire() throws FileNotFoundException,UnsupportedEncodingException,IOException{
	//read the text file to be classified 
	//
	ArrayList<String> VecteurFichier =new ArrayList<>();
 	
 	FileInputStream FichierALire= new FileInputStream("lien");
		
 	InputStreamReader LecteurDeFichier= new InputStreamReader(FichierALire, "UTF-8");
 	
 	
 	//lecture de fichier:
 	
 	BufferedReader br = new BufferedReader(LecteurDeFichier);
		
	    
	    
		String ligne = null;

		while( (ligne = br.readLine())!= null ){
		        // \\s+ means any number of whitespaces between tokens
		    String [] tokens = ligne.split("\\s+");
		    for(int i=0;i< tokens.length;i++){
		    	
		    	VecteurFichier.add(tokens[i]);
		    
		    }
//		    String var_1 = tokens[0];
//		    String var_2 = tokens[1];
//		    String var_3 = tokens[2];
		    }
 	

 	
 	
 	
   
}
}
