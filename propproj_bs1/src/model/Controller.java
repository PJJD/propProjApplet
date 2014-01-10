package model;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import db.TheaterDAO;
import db.TheaterException;
import db.VoorstellingDAO;


/**
 * Klasse die optreedt als intermediair tussen de UI en het domein
 * 
 * @author Mederwerker OU
 * 
 */
public class Controller  {

  private Uitvoering uitvoering = null; // huidige uitvoering
  private Voorstelling voorstelling = null; // huidige voorstelling
  private TheaterDAO dao = null;
  private Klant klant = null;
   

  public Uitvoering getUitvoering() {
	  return this.uitvoering;
  }
  
  public void setUitvoering(Uitvoering uitvoering) {
	  this.uitvoering = uitvoering;
  }
  
  public Voorstelling getVoorstelling() {
	  return this.voorstelling;
  }
  
  public void setVoorstelling(Voorstelling voorstelling) {
	  this.voorstelling = voorstelling;
  }

  /**
   * Initialiseert 'theater-constanten'
   * @throws TheaterException
   */
  public Controller() throws TheaterException{
      dao = TheaterDAO.getInstance();
      dao.startTheater();
  }
  
  // Test de verbinding  
  public static void main(String[] args) {
    try {
      Controller contr = new Controller();
//      JOptionPane.showMessageDialog(null,  "Verbinding maken gelukt","OK",
//          JOptionPane.INFORMATION_MESSAGE);
      contr.getVoorstellingen();
      System.out.println(contr.getUitvoeringen());
     
    }
    catch (TheaterException e) {
      JOptionPane.showMessageDialog(null,  e.getMessage(),"Fatale fout", 
          JOptionPane.ERROR_MESSAGE);
      e.printStackTrace();
    }
    
    
  }
  
  // Toegevoegde code
  
  public ArrayList<Voorstelling> getVoorstellingen() {
	  ArrayList<Voorstelling> voorstellingen = null;
	  try {
		  VoorstellingDAO vdao = VoorstellingDAO.getInstance();
		  voorstellingen = vdao.getVoorstellingen();
		  voorstelling = (Voorstelling) voorstellingen.toArray()[0];
	  } catch (TheaterException e) {
	      JOptionPane.showMessageDialog(null,  e.getMessage(), "Ophalen voorstellingen mislukt", 
	              JOptionPane.ERROR_MESSAGE);
	          e.printStackTrace();
	  }
	  return voorstellingen;
  }
  
  public ArrayList<Uitvoering> getUitvoeringen() {
	  ArrayList<Uitvoering> uitvoeringLijst = null;
	  ArrayList<Uitvoering> uitvoeringenToekomst = new ArrayList<Uitvoering>();
	  uitvoeringLijst = voorstelling.getUitvoeringen();
	  Date vandaag = new Date();
	  Date datumUitvoering = null;
	  SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");

	  for (Uitvoering u :uitvoeringLijst) {
		  try {
			  datumUitvoering = df.parse(u.toString());
		  } catch (Exception e) {
			  JOptionPane.showMessageDialog(null,  e.getMessage(), "Ophalen uitvoeringen mislukt", 
		              JOptionPane.ERROR_MESSAGE);
			  e.printStackTrace();
		  }
		  
		  if (datumUitvoering.after(vandaag)) {
			  uitvoeringenToekomst.add(u);
		  }
	  }
	  uitvoering = (Uitvoering) uitvoeringenToekomst.toArray()[0];
	  return uitvoeringenToekomst;
  }
  
  public ArrayList<Plaats> getZaalbezetting() {
	  ArrayList<Plaats> zaalbezetting = null;
	  try {
		  zaalbezetting = uitvoering.getZaalbezetting();
	  } catch (TheaterException e) {
		  JOptionPane.showMessageDialog(null, e.getMessage(), "Ophalen plaatsen mislukt",
				  JOptionPane.ERROR_MESSAGE);
		  e.printStackTrace();
	  }
	  return zaalbezetting;
  }
  
  public String getVoorstellingInfo() {
	  return voorstelling.getVoorstellingInfo();
  }
  
  public String getUitvoeringInfo() {
	  return uitvoering.getUitvoeringInfo();
  }
  
  public int getAantalRijen() {
	  int rijen = Theater.getAantalRijen();
	  return rijen;
  }
  
  public int getAantalStoelenPerRij() {
	  int stoelenPerRij = Theater.getStoelenPerRij();
	  return stoelenPerRij;
  }
  
  // Einde toegevoegde code
  
  

}
