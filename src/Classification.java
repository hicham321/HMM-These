import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;


public class Classification extends JPanel implements ActionListener{
	
	private FileOutputStream ecritureFich;
	
	private OutputStreamWriter redacteur;
	
	private ArrayList<String> listFinalDocument= new ArrayList<>();
	
	private ArrayList<Double> listProba= new ArrayList<Double>();
	
	private ArrayList<String>listClasse= new ArrayList<String>();
	
	
	//GUI
	private JFileChooser filechooser;
	
	private JButton ouvrir;
	
	private JButton classifie;
	
	private JButton stats;
	
	private JTextArea EspaceTxt;
	
	private JScrollPane jp;
	
	private File file;
	
	
	
	int returnVal;
	
	private int nbrMotVide;
	
	private long duration;
	
	public Classification(){
		this.filechooser= new JFileChooser();
		this.ouvrir= new JButton("Ouvrir");
		this.classifie= new JButton("classifie");
		this.stats= new JButton("Statistiques");
		this.EspaceTxt= new JTextArea();
		//
		this.jp = new JScrollPane(EspaceTxt);
		
        setPreferredSize(new Dimension(600, 400));
        setLayout(null);
        
        add(ouvrir);
        add(classifie);
        add(stats);
        add(EspaceTxt);
        ouvrir.setBounds(30, 30, 100, 25);
        classifie.setBounds(200, 30, 100, 25);
        stats.setBounds(370, 30, 100, 25);
        EspaceTxt.setBounds(120, 90, 350, 250);
        ouvrir.addActionListener(this);

		classifie.addActionListener(this);
		
		stats.addActionListener(this);
	}

	
	public void ClassificationDocument(File textFile)throws FileNotFoundException,UnsupportedEncodingException,IOException{
		//File textFile=new File("C:/Users/Hicham/Desktop/ffff.txt");
		TreatmentDocument(textFile);
		categoriser();
		System.out.println(listProba);
	    Double maxValue=Collections.max(listProba);
	    for(int i=0;i<listProba.size();i++){
	    	if(i==0){
    			System.out.println("la probabilité de la category culture est : "+listProba.get(i)); 
    		 }
    		 if(i==1){
     			System.out.println("la probabilité de la category  Economie est : "+listProba.get(i)); 
     		 }
    		 if(i==2){
     			System.out.println("la probabilité de la category  politique : "+listProba.get(i)); 
     		 }
    		 if(i==3){
     			System.out.println("la probabilité de la category  santé : "+listProba.get(i)); 
     		 }
    		 if(i==4){
     			System.out.println("la probabilité de la category  science est : "+listProba.get(i)); 
     		 }
    		 if(i==5){
     			System.out.println("la probabilité de la category  sport est : "+listProba.get(i)); 
     		 }      
	    }
         for(int i=0;i<listProba.size();i++){
        	 if(maxValue==listProba.get(i)){
        		 if(i==0){
        			System.out.println("la document est de la category : culture"); 
        		 }
        		 if(i==1){
         			System.out.println("la document est de la category : Economie"); 
         		 }
        		 if(i==2){
         			System.out.println("la document est de la category : politique "); 
         		 }
        		 if(i==3){
         			System.out.println("la document est de la category : santé"); 
         		 }
        		 if(i==4){
         			System.out.println("la document est de la category : science"); 
         		 }
        		 if(i==5){
         			System.out.println("la document est de la category : sport"); 
         		 }       		 
        		 
        		 
        	 }
         }
		
	}
	
private void TreatmentDocument(File textFile) throws FileNotFoundException,UnsupportedEncodingException,IOException{
	
	StopWord st =new StopWord(textFile);
	
	
	ArrayList<String>list=st.EliminerStopWord();
	this.nbrMotVide= st.nombredemotvides;
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
 	
 	this.listFinalDocument=ListTrie;
   
}

public void categoriser ()throws FileNotFoundException , UnsupportedEncodingException ,IOException{
	String repertoirSport="C:/Users/Hicham/loukam1/sport";
	File sportFichier= new File("le fichier de donn�e");
	testerHashmap sport = new testerHashmap(repertoirSport);
	// pour lire les donn� directement apres la lecture
	// site : http://stackoverflow.com/questions/16111496/java-how-can-i-write-my-arraylist-to-a-file-and-read-load-that-file-to-the
	this.ecritureFich= new FileOutputStream(sportFichier) ;
	
	
	
	String repertoirCulture="C:/Users/Hicham/loukam1/Culture";
	testerHashmap culture = new testerHashmap(repertoirCulture);
	String repertoirpolitique="C:/Users/Hicham/loukam1/politique";
	testerHashmap politique = new testerHashmap(repertoirpolitique);
	String repertoirSante="C:/Users/Hicham/loukam1/Santé";
	testerHashmap sante = new testerHashmap(repertoirSante);
	String repertoirscience="C:/Users/Hicham/loukam1/Science";
	testerHashmap science = new testerHashmap(repertoirscience);
	String repertoirEconomie ="C:/Users/Hicham/loukam1/economie";
	testerHashmap Economie = new testerHashmap(repertoirEconomie);
	
	
	    for(HashMap<String, Double> etat: sport.LesEtatsFinal){
	    	
	    	
	    }
	
	
	
	
	
	double probaSport=1;
	double probaEcono=1;
	double probapolitique=1;
	double probasante=1;
	double probaScience=1;
	double probacCulture=1;

    


	for(int i=0; i<10;i++){
		
		if (sport.LesEtatsFinal.get(i).containsKey(this.listFinalDocument.get(i))){
			String m =this.listFinalDocument.get(i);
			probaSport=probaSport *sport.LesEtatsFinal.get(i).get(m);
		}
		else{ probaSport= probaSport*0.00000000001;
		};
		if (culture.LesEtatsFinal.get(i).containsKey(this.listFinalDocument.get(i))){
			String m =this.listFinalDocument.get(i);
			probacCulture=probacCulture *culture.LesEtatsFinal.get(i).get(m);
		}
		else{ probacCulture= probaSport*0.00000000001;
		};
		if (politique.LesEtatsFinal.get(i).containsKey(this.listFinalDocument.get(i))){
			String m =this.listFinalDocument.get(i);
			probapolitique=probapolitique *politique.LesEtatsFinal.get(i).get(m);
		}
		else{ probapolitique= probapolitique*0.00000000001;
		};
		if (sante.LesEtatsFinal.get(i).containsKey(this.listFinalDocument.get(i))){
			String m =this.listFinalDocument.get(i);
			probasante=probasante *sante.LesEtatsFinal.get(i).get(m);
		}
		else{ probasante= probasante*0.00000000001;
		};
		if (science.LesEtatsFinal.get(i).containsKey(this.listFinalDocument.get(i))){
			String m =this.listFinalDocument.get(i);
			probaScience=probaScience *science.LesEtatsFinal.get(i).get(m);
		}
		else{ probaScience= probaScience*0.00000000001;
		};
		if (Economie.LesEtatsFinal.get(i).containsKey(this.listFinalDocument.get(i))){
			String m =this.listFinalDocument.get(i);
			probaEcono=probaEcono *Economie.LesEtatsFinal.get(i).get(m);
		}
		else{ probaEcono= probaEcono*0.00000000001;
		};
	}
	listProba.add(probacCulture);
	listProba.add(probaEcono);
	listProba.add(probapolitique);
	listProba.add(probasante);
	listProba.add(probaScience);
	listProba.add(probaSport);
	

}

    public static void main(String[] args) throws FileNotFoundException , UnsupportedEncodingException ,IOException{
    	
	long startTime = System.nanoTime();
    //code 
	//Classification cl =new Classification();
	JFrame frame = new JFrame("Interface De Classification");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(new Classification());
    frame.pack();
    frame.setVisible(true);
	
	
	long endTime = System.nanoTime();
	long duration = (endTime - startTime);
    System.out.println("le temps de traitement est :"+duration/1000000 +" miliseconds");
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ouvrir){
            returnVal = filechooser.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION){
            	    EspaceTxt.setText(null);
                    file = filechooser.getSelectedFile();
                    try {
						BufferedReader br = new BufferedReader(new FileReader(file));
						String currentLine;
						 while((currentLine = br.readLine()) != null){
							 EspaceTxt.append(currentLine +"\n");
                     }
						 
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            }
            }
            
            
		if(e.getSource() == classifie){
			try {
				long startTime = System.nanoTime();

				ClassificationDocument(file);
				
				listClasse.add("Culture");
				listClasse.add("Economie");
				listClasse.add("Politique");
				listClasse.add("Santé");
				listClasse.add("Science");
				listClasse.add("Sport");
				
			    Double maxValue=Collections.max(listProba);
                int index=0;
				for(int i=0;i<listProba.size();i++){
					if(maxValue==listProba.get(i)){
					index =i;	
					}
				}
				JOptionPane.showMessageDialog(null, "la classe du document choisis est :"+"  "+listClasse.get(index));
				long endTime = System.nanoTime();
				 duration = (endTime - startTime);
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource() == stats){
			JOptionPane.showMessageDialog(null,"quelque statistique sur la classification :"+"\n"+"\n"+ "Mot vide eliminé: "+nbrMotVide +"\n"+"\n"+"la probabilité de la category culture est : "+listProba.get(0)+"\n"+"la probabilité de la category Economie est : "+listProba.get(1)+"\n"+"la probabilité de la category Politique est : "+listProba.get(2)+"\n"+"la probabilité de la category Santé est : "+listProba.get(3)+"\n"+"la probabilité de la category Science est : "+listProba.get(4)+"\n"+"la probabilité de la category Sport est : "+listProba.get(5)+"\n"+"\n"+"Le temp de la classification est : "+this.duration/1000000+" milliseconde");
			
		}
		
                   
	}
}
