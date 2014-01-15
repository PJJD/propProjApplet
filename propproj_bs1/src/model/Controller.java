package model;



import java.util.ArrayList;
import java.util.Date;
import java.util.Observer;
import java.util.Observable;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import db.PlaatsDAO;
import db.TheaterException;
import db.TheaterDAO;
import db.VoorstellingDAO;
import model.Voorstelling;
import model.Uitvoering;
import model.Plaats;
import model.Theater;


/**
 * Klasse die optreedt als intermediair tussen de UI en het domein
 * 
 * @author Mederwerker OU
 * 
 */
public class Controller extends Observable  {

  private Uitvoering uitvoering = null; // huidige uitvoering
  private Voorstelling voorstelling = null; // huidige voorstelling
  private Winkelwagen winkelwagen = null;
  private TheaterDAO dao = null;
   

  public Uitvoering getUitvoering() {
	  return this.uitvoering;
  }
  
  public void setUitvoering(Uitvoering uitvoering) {
	  this.uitvoering = uitvoering;
	  setChanged();
	  notifyObservers();
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
			JOptionPane.showMessageDialog(null, e.getMessage(),
					"Ophalen plaatsen mislukt", JOptionPane.ERROR_MESSAGE);
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
  
  public boolean logKlantIn(String gebruikersnaam, String wachtwoord) {
	  try {
		  winkelwagen = new Winkelwagen(gebruikersnaam, wachtwoord);
	  } catch (TheaterException e) {
		  JOptionPane.showMessageDialog(null, e.getMessage(), "Inloggen mislukt",
				  JOptionPane.ERROR_MESSAGE);
		  e.printStackTrace();
	  }
	  if (winkelwagen.getKlant() != null) {
		  setChanged();
		  notifyObservers();
		  return true;
	  }
	  JOptionPane.showMessageDialog(null, "Mogelijk is er een foute gebruikersnaam en/of wachtwoord opgegeven", "Inloggen mislukt",
			  JOptionPane.ERROR_MESSAGE);
	  return false;
  }
  
  public String getKlantNaam() {
	  String klantnaam ="";
	  try {
		  klantnaam = winkelwagen.getKlant().getNaam();
	  } catch (NullPointerException e) {
		  //e.printStackTrace();
	  }
	  return klantnaam;
  }
  
  public void reserveerStoel(int plaatsnr) {
	  boolean gereserveerd = false;
	  int aantal = 0;
	  int maximum = Theater.getMaxPerUitvoering();
	  if (winkelwagen != null) {
		  gereserveerd = uitvoering.reedsGereserveerd(plaatsnr);
		  aantal = winkelwagen.aantalKaarten(uitvoering);
		  if (gereserveerd || (aantal < maximum)) {
			  uitvoering.reserveerStoel(plaatsnr);
		  } else {
			  JOptionPane.showMessageDialog(null, "Per klant kan er een maximum van " + maximum + " kaarten per uitvoering gereserveerd worden", "Maximum overschreden",
					  JOptionPane.ERROR_MESSAGE);
		  }
	  } else {
		  JOptionPane.showMessageDialog(null, "U dient eerst in te loggen om plaatsen te kunnen reserveren", "Niet ingelogd",
				  JOptionPane.ERROR_MESSAGE);
	  }
	  setChanged();
	  notifyObservers();
  }
  
  public void bevestigSelectie() {
	  ArrayList<Plaats> gereserveerdePlaatsen = uitvoering.getGereserveerdePlaatsen();
	  Boodschap boodschap = new Boodschap(uitvoering, gereserveerdePlaatsen);
	  winkelwagen.boodschapToevoegen(boodschap);
	  setChanged();
	  notifyObservers();
  }
  
  public String getBoodschappenlijst() {
	  String boodschappenlijst = "";
	  for (Boodschap b: winkelwagen.getBoodschappen()) {
		  boodschappenlijst += b.toString() + "\n";
	  }
	  return boodschappenlijst;
  }
  // Einde toegevoegde code
  
  

}
