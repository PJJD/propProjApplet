package model;

/**
 * Klasse die een rang representeert
 * Prijs van een plaats in het theater is afhankelijk 
 * van de rang van de plaats
 * @author Medewerker OU
 *
 */
public class Rang {
  private String naam = null;
  private double prijs = 0;
  
  public Rang() {
	  super();
  }
  
  public String getNaam() {
    return naam;
  }
  public void setNaam(String naam) {
    this.naam = naam;
  }
  public double getPrijs() {
    return prijs;
  }
  public void setPrijs(double prijs) {
    this.prijs = prijs;
  }
  
  
 
}
