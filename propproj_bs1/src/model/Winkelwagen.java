package model;

import java.util.ArrayList;


import db.BestellingDAO;
import db.TheaterException;

/**
 * Klasse die een virtuele winkelwagen representeert
 * 
 * @author Pieter-Jan Delaruelle
 * 
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
  
  /**
   * Constructor die op basis van een gebruikersnaam en een wachtwoord 
   * een winkelwagen aanmaakt
   * 
   * @param gebruikersnaam De gebruikersnaam van de klant waarvoor een winkelwagen moet worden aangemaakt
   * @param wachtwoord Het wachtwoord van de klant waarvoor een winkelwagen moet worden aangemaakt
   * @throws TheaterException Treedt op wanneer de klant niet kan worden opgehaald uit de databank
   */
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

  /**
   * Methode voor het toevoegen van een boodschap aan de winkelwagen
   * @param b De boodschap die aan de winkelwagen moet worden toegevoegd
   */
  public void boodschapToevoegen(Boodschap b) {
	  boodschappen.add(b);
  }
  
  public double getPrijs() {
	  return this.prijs;
  }
  
 //Klasse BestellingDAO maakt gebruik van onderstaande methode. 
 
  /**
   * Berekent de prijs van deze winkelwagen, inclusief eventuele korting.
   * Indien nodig wordt het attribuut vasteKlant van de klant in kwestie ook aangepast.
   * 
   * @return De berekende prijs
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

  /**
   * Update de winkelwagen aan de hand van de geselecteerde plaats en de uitvoering.
   * Wanneer er voor de geselecteerde uitvoering nog geen Boodschap bestaat wordt deze aangemaakt. 
   * Wanneer de laatse Plaats voor de geselecteerde uitvoering verwijderd wordt, wordt de boodschap voor de geselecteerde uitvoering ook uit de winkelwagen verwijderd.
   * 
   * @param plaatsnr De plaatsnr van de geselecteerde plaats
   * @param uitvoering De geselecteerde uitvoering
   * @throws TheaterException
   */
  public void updateWinkelwagen(int plaatsnr, Uitvoering uitvoering) throws TheaterException {
	 Boodschap boodschap = null;
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
  
  /**
   * Deze methode geeft het aantal gereserveerde plaatsen voor de geselecteerde uitvoering weer.
   * 
   * @param uitvoering De geselecteerde uitvoering
   * @return Het aantal reeds gereserveerde plaatsen voor de geselecteerde uitvoering.
   * @throws TheaterException
   */
  public int aantalGereserveerd(Uitvoering uitvoering) throws TheaterException {
	  int aantalGereserveerd = 0;
	  BestellingDAO bdao = BestellingDAO.getInstance();
	  aantalGereserveerd = bdao.aantalKaarten(uitvoering, klant);
	  for (Boodschap b: boodschappen) {
		  if (b.getUitvoering() == uitvoering) {
			  aantalGereserveerd += b.aantalGereserveerdePlaatsen();
		  }
	  }

	  return aantalGereserveerd;
  }
  
  /**
   * Methode voor het wegschrijven van de winkelwagen in kwestie naar de databank.
   * 
   * @param idealBetaling Geeft aan of het al dan niet over een betaling via iDeal gaat
   */
  public void schrijfWeg(boolean idealBetaling) {
	  double seizoenTotaal = klant.getSeizoentotaal();
	  seizoenTotaal += this.prijs;
	  klant.setSeizoentotaal(seizoenTotaal);
	  try {
		  BestellingDAO bdao = BestellingDAO.getInstance();
		  bdao.schrijf(this, idealBetaling);
	  } catch (TheaterException e) {
		  
	  }
  }
  
  /**
   * Maakt de winkelwagen leeg
   */
  public void maakLeeg() {
	  this.boodschappen = null;
	  this.klant = null;
	  this.korting = 0.0;
	  this.prijs = 0.0;
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
