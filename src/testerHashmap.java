import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;



public class testerHashmap {
private File RepertoireDesFichiersCorpus;
	
	
	// list des vecteur de chaque document
    public ArrayList<ArrayList<String>> VecteurDesFichiers=new ArrayList<ArrayList<String>>();

	
	//Hashmap pour memoriser les frequeces de chaque mot 
	
	private ArrayList<Map<String, Integer>> map=new ArrayList<Map<String,Integer>>();
	 
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
	
	private Set<String> test = new HashSet<>();

	public ArrayList<HashMap<String, Double>> LesEtatsFinal=new ArrayList<HashMap<String,Double>>();

	public  testerHashmap(String repertoire) throws FileNotFoundException , UnsupportedEncodingException ,IOException{
		

		
		//reading method works
		ArrayList<ArrayList<String>> list=FichierAuVecteur(repertoire);
		
		System.out.println("list des mots apres elimination des mots vides :");
		for (int i=0;i<list.size();i++){
			System.out.println(" Document " +(i+1)	+ " "+list.get(i));
		}
		//Vectorization method works
		ArrayList<Map<String, Integer>> hash= Vectorisation(VecteurDesFichiers);
		
		System.out.println("liste des document apres la phase du vectorisation :");
		for (int i=0;i<list.size();i++){
			
			System.out.println("vecteur "+(i+1)+" "+hash.get(i));
		}
		//tri method works
		tri();
		System.out.println("list des document trie par ordre d'impotance des mots : ");

		for(int i=0; i<this.listDesMotsTries.size();i++){
			
		System.out.println("Ducument "+(i+1)+" "+ this.listDesMotsTries.get(i));	
		}
		//this method works 
		apprentissage(10);
		// occurence par niveau works
		/*for(int i=0;i<listeDesoccurenceParNiveau.size();i++){
			
			System.out.println(listeDesoccurenceParNiveau.get(i));
		}
			System.out.println(hashForFrequencies);
		*/
		
		//System.out.println(this.listeFinal);
		//testing for the probability method 
		calcProbabilite(0.25, 10);
		//System.out.println("les Etats du HMM :");
		/*for(HashMap<String, Double> m: this.LesEtatsFinal ){
			System.out.println(m.get("أسعار")); 
			double somme=0;
			for(String m: this.LesEtatsFinal.get(i).keySet()){
				somme= somme +this.LesEtatsFinal.get(i).get(m);
			}
			System.out.println("Somme Etat "+(i+1)+" "+somme );

		}*/
		
		
		
	}
	
	
	private ArrayList<ArrayList<String>>   FichierAuVecteur (String LienVersRepertoire) throws FileNotFoundException , UnsupportedEncodingException ,IOException{
 	    

	 	
 		File dir = new File(LienVersRepertoire);
 		
 		  File[] ListeDeRepertoire = dir.listFiles();
 		  
 		  int compteur =0;
 		  if (ListeDeRepertoire != null) {
 		    for (File child : ListeDeRepertoire) {
 		      // Do something with child
 		    	
 		    	ArrayList<String> VecteurFichier =new ArrayList<>();
 		    	
 		    	StopWord st =new StopWord(child);
 		    	VecteurFichier= st.EliminerStopWord();
 		    	
 		    	
 		   
 		    	
 		    	
 		    	
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
	 
	private ArrayList<Map<String,Integer>> Vectorisation( ArrayList<ArrayList<String>> listeDesVecteurs){
		 
		 //iterating in the list of vectors 
		 
		 for(int i =0; i<listeDesVecteurs.size();i++){
			 
			 Map<String ,Integer> vecteurDesFrequences= new HashMap<String,Integer>();
			 

			 for(int j=0;j<listeDesVecteurs.get(i).size();j++){
				 int compteur=0;
				 for(int k=0; k<listeDesVecteurs.get(i).size();k++){
					 
					 //testing if the strings are equal
					 
					 if(listeDesVecteurs.get(i).get(j).equals(listeDesVecteurs.get(i).get(k))){
						 
						
						 compteur++;
					 }

				 }
				 vecteurDesFrequences.put(listeDesVecteurs.get(i).get(j),compteur);

			 }

			 this.map.add(vecteurDesFrequences);
			 }
		 return map;
		 }
	
 private void tri(){
		 
		 
		 
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
 
 
 private void apprentissage(int nbrEtat){
	
	 ArrayList<ArrayList<String>> biggerDummyArrayList=new ArrayList<ArrayList<String>>();
	 
	 for(int i=0; i<nbrEtat;i++){
		 ArrayList<String> dummyArrayList=new ArrayList<>();

	     for(ArrayList<String> m:this.listDesMotsTries){
	    	 
			dummyArrayList.add(m.get(i)) ;

	     }
	    biggerDummyArrayList.add(dummyArrayList);
	 }
	 for(int k=0; k<biggerDummyArrayList.size();k++){
		 HashMap<String, Integer> OccurenceDansNiveau=new HashMap<>();

		 for(int z=0;z<biggerDummyArrayList.get(k).size();z++){
			 int compteur=0;
			 for(int l=0;l<biggerDummyArrayList.get(k).size();l++){
				 if(biggerDummyArrayList.get(k).get(z).equals(biggerDummyArrayList.get(k).get(l))){
					 
					 compteur++;
				 }
			 }
			 //possible mistake
			 OccurenceDansNiveau.put(biggerDummyArrayList.get(k).get(z), compteur);
		 }
		 this.listeDesoccurenceParNiveau.add(OccurenceDansNiveau);
	 }
	 
	 
	 ArrayList<String> DummylistOfAllDocuments =new ArrayList<String>();
	 for(ArrayList<String> m:this.listDesMotsTries){
		 for(int j=0;j<m.size();j++){
    	 
			DummylistOfAllDocuments.add(m.get(j)) ;
		 }

	 }
	  this.SommeFrequence=DummylistOfAllDocuments.size();
	  Set<String> foo = new HashSet<String>(DummylistOfAllDocuments);
      this.test=foo;
      
      for(String m: foo){
    	  this.listeFinal.add(m);
      }

	  for (int i=0; i< DummylistOfAllDocuments.size();i++){
		 int compteur=0;
		 for(int j=0;j<DummylistOfAllDocuments.size();j++){
			 
			 if(DummylistOfAllDocuments.get(i).equals(DummylistOfAllDocuments.get(j))){
				compteur++; 
			 }
		 }
		 this.hashForFrequencies.put(DummylistOfAllDocuments.get(i), compteur);
	 }
			 
		 
	 
	 
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
		 this.LesEtatsFinal.add(Etat);
	 }
 }
	public static void main(String[] args) throws UnsupportedEncodingException,FileNotFoundException,IOException {
		
		String repertoire= "C:/Users/Hicham/JazeeraEC";
		testerHashmap test = new testerHashmap(repertoire);
		
}
}



