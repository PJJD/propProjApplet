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
  private double prijs = 0.0;
  private double korting = 0.0;

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
  
  public double getPrijs() {
	  return this.prijs;
  }
  
 //Klasse BestellingDAO maakt gebruik van onderstaande methode. 
 
  /**
   * Berekent de prijs van deze winkelwagen, inclusief eventuele korting
   * @return de prijs
   */
  public double berekenprijs()  {
	  double kortingspercentage = Theater.getVasteklantkorting();
	  double minimumBedrag = Theater.getMinimumbedrag();
	  double seizoentotaal = klant.getSeizoentotaal();
	  double uitgegeven = 0.0;
	  double prijs = 0.0;
	  for (Boodschap b: boodschappen) {
		  prijs += b.berekenprijs();
	  }
	  uitgegeven = seizoentotaal + prijs;
	  if (uitgegeven >= minimumBedrag) {
		  klant.setVasteKlant(true);
	  } else {
		  klant.setVasteKlant(false);
	  }
	  
	  if (klant.isVasteKlant()) {
		  korting = (prijs * kortingspercentage / 100.0);
	  } else {
		  korting = 0.0;
	  }
	  this.prijs = prijs;
	  return prijs - korting;
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
	 if (boodschap.getGereserveerdePlaatsen().isEmpty()) {
		 boodschappen.remove(boodschap);
	 }
	 berekenprijs();
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
  
  public void schrijfWinkelwagenWeg(boolean idealBetaling) {
	  klant.setSeizoentotaal(klant.getSeizoentotaal() + this.prijs);
	  try {
		  BestellingDAO bdao = BestellingDAO.getInstance();
		  bdao.schrijf(this, idealBetaling);
	  } catch (TheaterException e) {
		  
	  }
  }
  
  public String toString() {
	  String winkelwagen = "";
	  for (Boodschap b: boodschappen) {
		  winkelwagen += b.toString() + "\n";
	  }
	  if (winkelwagen.length() > 0) {
		winkelwagen += "________________________________________";
		winkelwagen += "\n\nSubtotaal:\t\t" + prijs;
		winkelwagen += "\nKorting:\t\t" + korting;
		winkelwagen += "\nTotaal:\t\t" + berekenprijs();
	}
	return winkelwagen;
  }

}
