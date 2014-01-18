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
  public double berekenprijs()  {
    return -1;
  }


  public void updateWinkelwagen(int plaatsnr, Uitvoering uitvoering) throws TheaterException {
	 Boodschap boodschap = null;
	 ArrayList<Plaats> gereserveerdePlaatsen = null;
	 Plaats plaats = null;
	 // Als er al een boodschap voor de huidige uitvoering in de winkelwagen zit halen we deze op
	 for (Boodschap b: boodschappen) {
		 if (b.getUitvoering() == uitvoering) {
			 boodschap = b;
		 }
	 }
	 // Als er nog geen boodschap voor de huidige uitvoering in de winkelwagen zit maken we deze aan
	 if (boodschap == null) {
		 boodschap = new Boodschap(uitvoering, new ArrayList<Plaats>());
		 boodschappen.add(boodschap);
	 }
	 boodschap.update(plaatsnr);
  }

  public int aantalGereserveerd(Uitvoering uitvoering) throws TheaterException {
	  int aantalGereserveerd = 0;
	  Boodschap boodschap = null;
	  BestellingDAO bdao = BestellingDAO.getInstance();
	  aantalGereserveerd = bdao.aantalKaarten(uitvoering, klant);
	  for (Boodschap b: boodschappen) {
		  if (b.getUitvoering() == uitvoering) {
			  aantalGereserveerd += b.getGereserveerdePlaatsen().size();
		  }
	  }

	  return aantalGereserveerd;
  }
  
  public String toString() {
	  String winkelwagen = "";
	  for (Boodschap b: boodschappen) {
		  winkelwagen += b.toString() + "\n";
	  }
	  winkelwagen += berekenprijs();
	  return winkelwagen;
  }

}
