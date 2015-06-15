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
	
	private ArrayList<Map<String, Integer>> map=new ArrayList<>();
	 
	 //list des mots tries
	
	private ArrayList<ArrayList<String>> listDesMotsTries=new ArrayList<ArrayList<String>>();
	 
	 //liste de tout les mot du corpus(pas de duplication)
	
	private ArrayList<String> listeFinal =new ArrayList<String>();
	
	//liste des occurences des mot dans chaque niveau d'impostance (inferieur au nombre d'etat:nbrEtat)
	
	private ArrayList<HashMap<String, Integer>> listeDesoccurenceParNiveau=new ArrayList<HashMap<String,Integer>>();
	
	//la somme de nombre d'occurence de tout les mots dans le corpus
	
	private int SommeFrequence;
	
	private HashMap<String, Integer> hashForFrequencies=new HashMap<>();
	
	private int NombreDeDocuments;

	
	 
	 //Adding another list that contains all the possible words in every document (should...
	 //shoose a data structure that eliminates duplicates by itself 
	 //this structure will be used to build the states .

	public HMMT (String repertoire) throws FileNotFoundException , UnsupportedEncodingException ,IOException{
		ArrayList<ArrayList<String>> list=FichierAuVecteur(repertoire);
		
	}
	

	 private ArrayList<ArrayList<String>>   FichierAuVecteur (String LienVersRepertoire) throws FileNotFoundException , UnsupportedEncodingException ,IOException{
 	    
		 //ArrayList<ArrayList<String>> v = new ArrayList<>();

 	
 		File dir = new File(LienVersRepertoire);
 		
 		  File[] ListeDeRepertoire = dir.listFiles();
 		  
 		  int compteur =0;
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
 		        compteur++;
 		      
 		    }
 		  } else {
 		    // Handle the case where dir is not really a directory.
 		    // Checking dir.isDirectory() above would not be sufficient
 		    // to avoid race conditions with another process that deletes
 		    // directories.
 			  System.out.println(" Ce n'est pas une repertoire de fichiers ");
 		  }
 		
 	this.NombreDeDocuments=compteur;
 	
 	
 	return VecteurDesFichiers;
 }
	 

	 
	 // le vectorisation (assigner un frequence a chaque mot de document :
	 
	 private ArrayList<Map<String,Integer>> Vectorisation( ArrayList<ArrayList<String>> listeDesVecteurs){
		 
		 //iterating in the list of vectors 
		 
		 for(int i =0; i<listeDesVecteurs.size();i++){
		 //iterating in each vector to determin the frequency
			 //each time we iterate a vector we create a new hash map to store the frequencies 
			 
			 Map<String ,Integer> vecteurDesFrequences= new HashMap<String,Integer>();
			 /*for(int z=0;z<listeDesVecteurs.get(i).size();z++){
				 
				 vecteurDesFrequences.put(listeDesVecteurs.get(i).get(z), 1);
			 }*/

			 for(int j=0;j<listeDesVecteurs.get(i).size();j++){
				 int compteur=0;
				 for(int k=0; k<listeDesVecteurs.get(i).size();k++){
					 
					 //testing if the strings are equal
					 
					 if(listeDesVecteurs.get(i).get(j).equals(listeDesVecteurs.get(i).get(k))){
						 
						 /*//this is a little bit complicated but it helps to consider that the key to the hash map is the value we are testing for (lisdesVecteur.get(i).get(j)
						 vecteurDesFrequences.put(listeDesVecteurs.get(i).get(j)  ,  vecteurDesFrequences.get(listeDesVecteurs.get(i).get(j)) +1 );
						 
						 //*****note that this step might rise some problems when testing ****
						 listeDesVecteurs.get(i).remove(j);*/
						 compteur++;
					 }

				 }
				 vecteurDesFrequences.put(listeDesVecteurs.get(i).get(j),compteur);

			 }
			 //adding the newHash map to the array list	 

			 this.map.add(vecteurDesFrequences);
			 }
		 return map;
		 }
		 
	 
	 
	 //tri descendant des vecteurs
	 // to sort the hash map we can put the contents(keys) of the hash map in an array list, 
	 private void tri(){
		 
		 //once we put the sorted content of the hash map in an array list we can then use the list directly .
		 // iterate through the hash map and put it in an array and sort it 
		 
		 
          //in case of undefined behaviour use the iterator for removing hashmap or list enteries as suggested in the oracle documentations
		 for(int i =0; i<this.map.size();i++){
			 
			 ArrayList<String> sousListTrie=new ArrayList<String>();
			 while(this.map.get(i).size() !=0){
				 
				    Integer maxValue=Collections.max(this.map.get(i).values());
				    for(String mot :this.map.get(i).keySet()){
				    	if(this.map.get(i).get(mot)==maxValue){
				    		sousListTrie.add(mot);
				    		this.map.get(i).remove(mot, maxValue);
				    		break;
				    	}
				    }
			 }
			this.listDesMotsTries.add(sousListTrie);
		 }
		 
		 
	 }
	 
	 private ArrayList<HashMap<String, Integer>> Etats(int nbrEtat){
		 //we need each word in the lists 
		 //iterate through the liste of arrays and each array and make a global...
		 //arraylist that has no duplicates
		 
		 ArrayList<ArrayList<String>> biggerDummyArrayList=new ArrayList<ArrayList<String>>();
		 
		 for(int i=0; i<nbrEtat;i++){
			 ArrayList<String> dummyArrayList=new ArrayList<>();

		     for(ArrayList<String> m:this.listDesMotsTries){
		    	 
				dummyArrayList.add(m.get(i)) ;

		     }
		    biggerDummyArrayList.add(dummyArrayList);
		 }
		 //this will only count the frequence of the words in the interval of nbretat , the rest of words..
		 //..will be added next when checking with the hashmap(we will ad 1)
		 for(int k=0; k<biggerDummyArrayList.size();k++){
			 HashMap<String, Integer> OccurenceDansNiveau=new HashMap<>();

			 for(int z=0;z<biggerDummyArrayList.get(k).size();k++){
				 int compteur=0;
				 for(int l=0;l<biggerDummyArrayList.get(k).size();l++){
					 if(biggerDummyArrayList.get(k).get(z).equals(biggerDummyArrayList.get(k).get(l))){
						 
						 compteur++;
					 }
				 }
				 OccurenceDansNiveau.put(biggerDummyArrayList.get(k).get(k), compteur);
			 }
			 this.listeDesoccurenceParNiveau.add(OccurenceDansNiveau);
		 }
		 // Make another dummy list of all the words and count the frequency of words based on those occurences
		 ArrayList<String> DummylistOfAllDocuments =new ArrayList<String>();
		 for(ArrayList<String> m:this.listDesMotsTries){
			 for(int j=0;j<m.size();j++){
	    	 
				DummylistOfAllDocuments.add(m.get(j)) ;
			 }

		 }
		  this.SommeFrequence=DummylistOfAllDocuments.size();
		 //calculating the frequency of every word in all of the corpus
		 for (int i=0; i< DummylistOfAllDocuments.size();i++){
			 int compteur=0;
			 for(int j=0;j<DummylistOfAllDocuments.size();j++){
				 
				 if(DummylistOfAllDocuments.get(i).equals(DummylistOfAllDocuments.get(j))){
					compteur++; 
				 }
			 }
			 this.hashForFrequencies.put(DummylistOfAllDocuments.get(i), compteur);
			 //here you can add DummyListOfAllDocuments.Remove(DummyListOfAllDocuments.get(i)) to increase performance
		 }
				 
			 
		 
		 
		 ArrayList<HashMap<String, Integer>> k =new ArrayList<>();
		 return k; 
	 }
	 private void calcProbabilite(double f,int nbrEtat){
		 
		 ArrayList<HashMap<String, Integer>> listDeMapDeProba= new ArrayList();
		 for(int i=0;i<nbrEtat;i++){
			 HashMap<String, Double> Etat= new HashMap<>();

			 for(int j =0; j<this.listeFinal.size();j++){
				String MOT= this.listeFinal.get(j) ;
				
				int FrequenceDuMot=this.hashForFrequencies.get(MOT);
				
				int frequenceParNiveau=0;
				if(listeDesoccurenceParNiveau.get(i).containsKey(MOT)){
					 frequenceParNiveau =listeDesoccurenceParNiveau.get(i).get(MOT);
				}
				double ProbabiliteDeMot= f * frequenceParNiveau/this.NombreDeDocuments +(1-f)*FrequenceDuMot/this.SommeFrequence;
					
				 Etat.put(MOT, ProbabiliteDeMot);
 
			 }
		 }
		 
		 
		 
	 }
	 
}

