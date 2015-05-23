import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Vector;

// first we need to read the files of the corpus 
	
	//secondly we need to eliminate stop words and do the stemming (this step can be delayed for programming)
	
    // thirdly we need to make a vector of vectors of each document in the corpus 
	
	//forthly we need to assign frequency for each word in each document 
	
	//Fifthly we need need to extract the most important words , we need to classify it from the most
	  //....important to the least important depending on their frequency
      //after this stage we have a collection of words in each vector representng each document from  
	  //...the most important to the least important word
	
    //6th depending on the level of importance and the number of states we make a vector of 
public class HMMT {
	private File RepertoireDesFichiersCorpus;
	
	
	
	 private ArrayList<ArrayList<String>>   FichierAuVecteur () throws FileNotFoundException , UnsupportedEncodingException ,IOException{
 	    ArrayList<ArrayList<String>> v = new ArrayList<>();

 	
 		File dir = new File("repertoire des fichiers");
 		
 		  File[] ListeDeRepertoire = dir.listFiles();
 		  
 		  if (ListeDeRepertoire != null) {
 		    for (File child : ListeDeRepertoire) {
 		      // Do something with child
 		    	
 		    	ArrayList<String> VecteurFichier =new ArrayList<>();
 		    	
 		    	FileInputStream FichierALire= new FileInputStream(child);
 				
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
// 				    String var_1 = tokens[0];
// 				    String var_2 = tokens[1];
// 				    String var_3 = tokens[2];
 				    }
 		    	
 		   
 		    	
 		    	
 		    	
 		        v.add(VecteurFichier);
 		      
 		    }
 		  } else {
 		    // Handle the case where dir is not really a directory.
 		    // Checking dir.isDirectory() above would not be sufficient
 		    // to avoid race conditions with another process that deletes
 		    // directories.
 			  System.out.println(" Ce n'est pas une repertoire de fichiers ");
 		  }
 		
 	
 	
 	
 	
 	return v;
 }
}

