import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class StopWord   {
	
	
	private FileInputStream fichtxt;
	
	private InputStreamReader lecteur;
	//the next two fields could be dropped .
	private FileOutputStream ecritureFich;
	
	private OutputStreamWriter k;
	
	//
	
	private ArrayList<String> ListeDesMots;
		
	private InputStreamReader lecteurStopWord;
	
	private ArrayList<String> ListeDesStopWord;
	
	public int nombredemotvides=0;
	
	
	public StopWord(File fichiertext) throws IOException,FileNotFoundException,UnsupportedEncodingException{
		
		this.fichtxt= new FileInputStream(fichiertext);
		
		this.lecteur= new InputStreamReader(this.fichtxt, "UTF-8");
		//for reading the stop words via the input stream.
		
		this.lecteurStopWord= this.LireStopWordFichier();
		
		this.LectureStopWords();
		this.LectureText();
		
		
		//we can add here that the constructor reads the stop words array list directly via the constructor.
		
		
	}
	
	
	
//	public void LectureFichier (String  fichierLecture) throws UnsupportedEncodingException,FileNotFoundException  {
//		
//		this.fichtxt = new FileInputStream(fichierLecture); 
//		
//		this.lecteur= new InputStreamReader(this.fichtxt, "UTF-8");
//		
//	}
	
	
	public void LectureText() throws IOException{
		
		BufferedReader br = new BufferedReader(this.lecteur);
		
	    ArrayList<String> ListeDesMots = new ArrayList<String>();
	    
		String ligne = null;

		while( (ligne = br.readLine())!= null ){
		        // \\s+ means any number of whitespaces between tokens
		    String [] tokens = ligne.split("\\s+");
		    for(int i=0;i< tokens.length;i++){
		    	
		    ListeDesMots.add(tokens[i]);
		    
		    }
//		    String var_1 = tokens[0];
//		    String var_2 = tokens[1];
//		    String var_3 = tokens[2];
		    }
	this.ListeDesMots=ListeDesMots;
		}
	
	public InputStreamReader LireStopWordFichier() throws FileNotFoundException,UnsupportedEncodingException{
		
		// needs a field outside for the stop words ArrayListe
		
		//must insert the direct file path in the FileInputStream
        FileInputStream fichierTextStopWord = new FileInputStream("C:/Users/Hicham/Desktop/stop-words_arabic_1_ar.txt");
		
		InputStreamReader LecteurFichierStopWord = new InputStreamReader(fichierTextStopWord, "UTF-8");
		
		return LecteurFichierStopWord;
		
	}
	
	public void LectureStopWords() throws IOException{
		
        BufferedReader br = new BufferedReader(this.lecteurStopWord);
		
	    ArrayList<String> ListeDesStopWord = new ArrayList<String>();
	    
		String ligne = null;

		while( (ligne = br.readLine())!= null ){
		        // \\s+ means any number of whitespaces between tokens
		    String [] tokens = ligne.split("\\s+");
		    for(int i=0;i< tokens.length;i++){
		    	
		    ListeDesStopWord.add(tokens[i]);
		    
		    }

		    }
		this.ListeDesStopWord= ListeDesStopWord;
	}
	
	
	public ArrayList<String> EliminerStopWord(){
		
		ArrayList<String> ListeDesMotsFinale= new ArrayList<String>();
		
		for(int i=0; i<this.ListeDesMots.size();i++){
			Boolean StopWordTrouver=false;

		    for(int j=0; j<this.ListeDesStopWord.size();j++){
				//la condition d'elemination
				if(this.ListeDesStopWord.get(j).equals(ListeDesMots.get(i)) ) {
				    	StopWordTrouver=true;
				    	this.nombredemotvides++;
				    	break;
				}
				
			
			}
		    
		    if(StopWordTrouver==false){
		    	ListeDesMotsFinale.add(ListeDesMots.get(i));
		    }
			
		}
	
		return ListeDesMotsFinale ;
	}
	
	
	public void EcritureFichier  (String fichierEcriture ) throws FileNotFoundException,UnsupportedEncodingException{
		
		this.ecritureFich= new FileOutputStream(fichierEcriture);
		
	}
	
	
    public ArrayList<String> getListedesmot(){
    	
    	return this.ListeDesMots ;
    }
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException,IOException {
		
      // l'utilistateur programeur peut ajouter un lien vers le fichier text qu'il veut faire eleminer les stop words 
	  // l'utilisateur doit pas modifier ou manipuler les stop words .
		// la resultat finale doit affiche le texte avec l'elimination des Stop words .
		File file= new File("C:/Users/Hicham/Desktop/stop-words_arabic_1_ar.txt");
		File file2= new File("liens vers la ripertoire des fichiers dans le stock ");
		
		StopWord StopWordObject= new StopWord(file);
		
		ArrayList<String> list=  StopWordObject.EliminerStopWord();
		// affichage des mot finale
		for(int i=0; i<list.size();i++){
			
		System.out.print(list.get(i));	
		}
	   
	}

}
