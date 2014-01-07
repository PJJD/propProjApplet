package model;

import java.util.ArrayList;

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
  private ArrayList<Plaats> zaalbezetting = null;
  
  public Uitvoering() {
	  super();
	  this.zaalbezetting = new ArrayList<Plaats>();
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
  
  
  public String toString() {
	  return this.datum + " " + this.tijd;
  }
  
  public String getUitvoeringInfo() {
	  return this.datum + " om " + this.tijd;
  }

  

  
  
}
