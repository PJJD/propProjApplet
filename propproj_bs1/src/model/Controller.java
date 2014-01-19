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
	  }
	  return klantnaam;
  }
  
  public void reserveerStoel(int plaatsnr) {
	  boolean reedsGereserveerd = false;
	  boolean reedsBezet = false;
	  int aantal = 0;
	  reedsGereserveerd = uitvoering.reedsGereserveerd(plaatsnr);
	  reedsBezet = uitvoering.reedsBezet(plaatsnr);
	  int maximum = Theater.getMaxPerUitvoering();
	  if (winkelwagen == null) {
		  JOptionPane.showMessageDialog(null, "U dient ingelogd te zijn als klant om plaatsen te kunnen reserveren", "Niet ingelogd",
				  JOptionPane.ERROR_MESSAGE);
		  return;
	  }
	  if (reedsBezet) {
		  JOptionPane.showMessageDialog(null, "Deze plaats is helaas reeds bezet en kan niet meer gereserveerd worden", "Reeds bezet",
				  JOptionPane.ERROR_MESSAGE);
		  return;
	  }
	  try {
		  aantal = winkelwagen.aantalGereserveerd(uitvoering);
		  if (reedsGereserveerd == true || aantal <= 5) {
			  uitvoering.reserveerStoel(plaatsnr);
		  }
		  if (aantal > 5 && reedsGereserveerd == false) {
			  JOptionPane.showMessageDialog(null, "Per klant kunnen er maximaal " + maximum + " plaatsen per uitvoering gerserveerd worden", "Maximum reservaties bereikt",
					  JOptionPane.ERROR_MESSAGE);
		  }
		  winkelwagen.updateWinkelwagen(plaatsnr, uitvoering);
		  setChanged();
		  notifyObservers();
	  } catch (TheaterException e) {
		  e.printStackTrace();
	  } catch (NullPointerException e) {
		  e.printStackTrace();
	  }
  }
  
  public String toonWinkelwagen() {
	  try {
		  return winkelwagen.toString();
	  } catch (NullPointerException e) {
		  return "";
	  }
  }
  
  public void schrijfWinkelwagenWeg(boolean idealBetaling) {
	  winkelwagen.schrijfWinkelwagenWeg(idealBetaling);
	  winkelwagen = new Winkelwagen(this.winkelwagen.getKlant());
	  setChanged();
	  notifyObservers();
  }
  

  
  // Einde toegevoegde code
  
//Test de controller 
 public static void main(String[] args) {
   try {
     Controller contr = new Controller();
//     JOptionPane.showMessageDialog(null,  "Verbinding maken gelukt","OK",
//         JOptionPane.INFORMATION_MESSAGE);
     contr.getVoorstellingen();
     contr.getUitvoeringen();
     contr.uitvoering.getZaalbezetting();
     contr.logKlantIn("testpj", "testpj");
   }
   catch (TheaterException e) {
     JOptionPane.showMessageDialog(null,  e.getMessage(),"Fatale fout", 
         JOptionPane.ERROR_MESSAGE);
     e.printStackTrace();
   }
   
   
 }

}
