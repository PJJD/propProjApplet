package model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

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
  public double berekenprijs()  {
    return -1;
  }


  

 

  
  

}
