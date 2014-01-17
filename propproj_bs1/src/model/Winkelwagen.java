package model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import db.BestellingDAO;
import db.TheaterException;
import db.KlantDAO;

/**
 * Klasse die een winkelwagen representeert
 * 
 * @author Medewerker OU
 * 
 */
public class Winkelwagen {

 
  private Klant klant = null;
  private ArrayList<Boodschap> boodschappen = new ArrayList<Boodschap>();

  public Winkelwagen(Klant k) {
	  this.klant = k;
  }
  
  public Winkelwagen(String gebruikersnaam, String wachtwoord) throws TheaterException {
	  this.klant = Klant.getKlant(gebruikersnaam, wachtwoord);
  }
  public Klant getKlant() {
	  return klant;
  }

  public void setKlant(Klant klant) {
	  this.klant = klant;
  }

  public ArrayList<Boodschap> getBoodschappen() {
	  return boodschappen;
  }

  public void setBoodschappen(ArrayList<Boodschap> boodschappen) {
	  this.boodschappen = boodschappen;
  }

  
  public void boodschapToevoegen(Boodschap b) {
	  boodschappen.add(b);
  }
  
 //Klasse BestellingDAO maakt gebruik van onderstaande methode. 
 
  /**
   * Berekent de prijs van deze winkelwagen, inclusief eventuele korting
   * @return de prijs
   */

  public int aantalKaarten(Uitvoering uitvoering) {
	  int aantal = 0;
	  try {
	  BestellingDAO bdao = BestellingDAO.getInstance();
	  bdao.aantalKaarten(uitvoering, klant);
	  } catch (TheaterException e) {
		  e.printStackTrace();
	  }
	  aantal += uitvoering.aantalGereserveerd();
	  return aantal;
  }
  
  public double berekenprijs() {
	  double totaal = 0;
	  for (Boodschap b: boodschappen) {
		  totaal += b.getTotaal();
	  }
	  //TODO: Korting berekenen
	  return totaal;
  }
  
 
  

 

  
  

}
