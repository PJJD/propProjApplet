package model;



import java.util.ArrayList;

import javax.swing.JOptionPane;

import db.PlaatsDAO;
import db.TheaterException;
import db.TheaterDAO;
import db.VoorstellingDAO;
import model.Voorstelling;
import model.Uitvoering;
import model.Plaats;


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
      contr.getUitvoeringen();
      System.out.println(contr.getZaalbezetting());
     
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
	  uitvoeringLijst = voorstelling.getUitvoeringen();
	  uitvoering = (Uitvoering) uitvoeringLijst.toArray()[0];
	  return uitvoeringLijst;
  }
  
  public ArrayList<Plaats> getZaalbezetting() {
	  ArrayList<Plaats> zaalbezetting = null;
	  try {
		  PlaatsDAO pdao = PlaatsDAO.getInstance();
		  zaalbezetting = pdao.getZaalbezetting(uitvoering);
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
	  int rijen = 15; // Dit moet op termijn door TheaterDAO opgehaald worden.
	  return rijen;
  }
  
  public int getAantalStoelenPerRij() {
	  int stoelenPerRij = 10; // Dit moet op termijn door TheaterDAO opgehaald worden.
	  return stoelenPerRij;
  }
  
  // Einde toegevoegde code
  
  

}
