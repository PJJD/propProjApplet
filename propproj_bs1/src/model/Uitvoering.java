package model;

import java.util.ArrayList;

import db.PlaatsDAO;
import db.TheaterException;

/**
 * Klasse die een Uitvoering representeert
 * 
 * @author Medewerker OU
 * 
 */
public class Uitvoering {
  private int uitvoeringnr = 0;
  private String datum = null;
  private String tijd = null;
  
  private Voorstelling voorstelling = null;
  private ArrayList<Plaats> zaalbezetting = new ArrayList<Plaats>();
  
  public Uitvoering() {
	  super();
	  this.setZaalbezetting(new ArrayList<Plaats>());
  }
  
  public void setUitvoeringnr(int nr) {
	  this.uitvoeringnr = nr;
  }
  
  public int getUitvoeringnr() {
	  return this.uitvoeringnr;
  }
 
  public void setDatum(String datum) {
	  this.datum = datum;
  }
  
  public String getDatum() {
	  return this.datum;
  }
  
  public void setTijd(String tijd) {
	  this.tijd = tijd;
  }
  
  public String getTijd() {
	  return this.tijd;
  }
  
  public void setVoorstelling(Voorstelling voorstelling) {
	  this.voorstelling = voorstelling;
  }
  
  public Voorstelling getVoorstelling() {
	  return this.voorstelling;
  }
  
  
  public ArrayList<Plaats> getZaalbezetting() throws TheaterException {
	if (zaalbezetting.isEmpty()) {
		PlaatsDAO pdao = PlaatsDAO.getInstance();
		zaalbezetting = pdao.getZaalbezetting(this);
	}
	return zaalbezetting;
  }

  public void setZaalbezetting(ArrayList<Plaats> zaalbezetting) {
	this.zaalbezetting = zaalbezetting;
  }

  public String toString() {
	  return this.datum + " " + this.tijd;
  }
  
  public String getUitvoeringInfo() {
	  return this.datum + " om " + this.tijd;
  }

  public Plaats getPlaats(int plaatsnr) {
	  Plaats plaats = null;
	  for (Plaats p: zaalbezetting) {
		  if (p.getPlaatsnr() == plaatsnr) {
			  plaats = p;
		  }
	  }
	  return plaats;
  }
  
  public boolean reedsGereserveerd(int plaatsnr) {
	  Plaats p = getPlaats(plaatsnr);
	  if (p.getGereserveerd()) {
		  return true;
	  }
	  return false;
  }
  
  public void reserveerStoel(int plaatsnr) {
	  Plaats p = getPlaats(plaatsnr);
	  p.toggleGereserveerd();
  }
  
  public int aantalGereserveerd() {
	  int aantal = 0;
	  for (Plaats plaats: zaalbezetting) {
		  if (plaats.getGereserveerd()) {
			  aantal += 1;
		  }
	  }
	  return aantal;
  }
  
  public ArrayList<Plaats> getGereserveerdePlaatsen() {
	  ArrayList<Plaats> gereserveerdePlaatsen = new ArrayList<Plaats>();
	  for (Plaats plaats: zaalbezetting) {
		  if (plaats.getGereserveerd()) {
			  gereserveerdePlaatsen.add(plaats);
		  }
	  }
	  return gereserveerdePlaatsen;
  }

  public double getRangPrijs(String rang) {
	  return voorstelling.getRangprijs(rang);
  }
  
}
