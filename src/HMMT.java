import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// first we need to read the files of the corpus 
	
	//secondly we need to eliminate stop words and do the stemming (this step can be delayed for programming)
	
    // thirdly we need to make a vector of vectors of each document in the corpus 
	
	//forthly we need to assign frequency for each word in each document 
	
	//Fifthly we need  to extract the most important words , we need to classify it from the most
	  //....important to the least important depending on their frequency
      //after this stage we have a collection of words in each vector representing each document from  
	  //...the most important to the least important word
	
    //6th depending on the level of importance and the number of states we make a vector of 
public class HMMT {
	// LA REPERTOIRE DES FICHIERS DE CORPUS 
	private File RepertoireDesFichiersCorpus;
	
	
	// list des vecteur de chaque document
	private ArrayList<ArrayList<String>> VecteurDesFichiers;
	
	//Hashmap pour memoriser les frequeces de chaque mot 
	
	 ArrayList<Map<String, Integer>> map=new ArrayList<>();
	 
	 //list des mots tries
	 ArrayList<ArrayList<String>> listDesMotsTries=new ArrayList<ArrayList<String>>();

	public HMMT (String repertoire) throws FileNotFoundException , UnsupportedEncodingException ,IOException{
		ArrayList<ArrayList<String>> list=FichierAuVecteur(repertoire);
		
	}
	

	 private ArrayList<ArrayList<String>>   FichierAuVecteur (String LienVersRepertoire) throws FileNotFoundException , UnsupportedEncodingException ,IOException{
 	    
		 //ArrayList<ArrayList<String>> v = new ArrayList<>();

 	
 		File dir = new File(LienVersRepertoire);
 		
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
 		    	
 		   
 		    	
 		    	
 		    	
 		        this.VecteurDesFichiers.add(VecteurFichier);
 		      
 		    }
 		  } else {
 		    // Handle the case where dir is not really a directory.
 		    // Checking dir.isDirectory() above would not be sufficient
 		    // to avoid race conditions with another process that deletes
 		    // directories.
 			  System.out.println(" Ce n'est pas une repertoire de fichiers ");
 		  }
 		
 	
 	
 	
 	return VecteurDesFichiers;
 }
	 

	 
	 // le vectorisation (assigner un frequence a chaque mot de document :
	 
	 private ArrayList<Map<String,Integer>> Vectorisation( ArrayList<ArrayList<String>> listeDesVecteurs){
		 
		 //iterating in the list of vectors 
		 
		 for(int i =0; i<listeDesVecteurs.size();i++){
		 //iterating in each vector to determin the frequency
			 //each time we iterate a vector we create a new hash map to store the frequencies 
			 
			 Map<String ,Integer> vecteurDesFrequences= new HashMap<String,Integer>();
			 for(int z=0;z<listeDesVecteurs.get(i).size();z++){
				 
				 vecteurDesFrequences.put(listeDesVecteurs.get(i).get(z), 1);
			 }

			 for(int j=0;j<listeDesVecteurs.get(i).size();j++){
				 
				 for(int k=0; k<listeDesVecteurs.get(i).size();k++){
					 
					 //testing if the strings are equal
					 
					 if(listeDesVecteurs.get(i).get(j).equals(listeDesVecteurs.get(i).get(k))){
						 
						 //this is a little bit complicated but it helps to consider that the key to the hash map is the value we are testing for (lisdesVecteur.get(i).get(j)
						 vecteurDesFrequences.put(listeDesVecteurs.get(i).get(j)  ,  vecteurDesFrequences.get(listeDesVecteurs.get(i).get(j)) +1 );
						 
						 //*****note that this step migh erase some problems when testing ****
						 listeDesVecteurs.get(i).remove(j);
					 }
					 

				 }
				 
			 }
			 //adding the newHash map to the array list	 

			 this.map.add(vecteurDesFrequences);
			 }
		 return map;
		 }
		 
	 
	 
	 //tri descendant des vecteurs
	 // to sort the hash map we can put the contents(keys) of the hash map in an array list, 
	 private void tri(ArrayList<HashMap<String, Integer>> map){
		 
		 //once we put the sorted content of the hash map in an array list we can then use the list directly .
		 // iterate through the hash map and put it in an array and sort it 
		 
		 
          //in case of undefined behaviour use the iterator for removing hashmap or list enteries as suggested in the oracle documentations
		 for(int i =0; i<map.size();i++){
			 
			 ArrayList<String> sousListTrie=new ArrayList<String>();
			 for(int j =0;j<map.get(i).size();j++){
				 
				    Integer maxValue=Collections.max(map.get(i).values());
				    for(String mot :map.get(i).keySet()){
				    	if(map.get(i).get(mot)==maxValue){
				    		sousListTrie.add(mot);
				    		map.get(i).remove(mot, maxValue);
				    		break;
				    	}
				    }
			 }
			this.listDesMotsTries.add(sousListTrie);
		 }
		 
		 
	 }
	 
	 // this method will make states (without probability) from the previous data 
	 //this method willl generate the states depending on the parameter number n
	 private ArrayList<HashMap<String, Integer>> Etats(int nbrEtat){
		 //we need each word in the lists 
		 //iterate through the liste of arrays and each array and make a global...
		 //arraylist that has no duplicates   
		 
		 ArrayList<HashMap<String, Integer>> k =new ArrayList<>();
		 return k;
	 }
	 
}

