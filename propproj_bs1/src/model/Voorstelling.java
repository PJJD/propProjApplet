package model;

import java.util.ArrayList;

/**
 * KLasse die een voorstelling representeert
 * 
 * @author Medewerker OU
 * 
 */
public class Voorstelling {

  private String naam = null;
  private String soort;
  private int voorstellingnr = 0;
 
 
  ArrayList<Uitvoering> uitvoeringen = null;
  ArrayList<Rang> prijzen = null;
  
  public Voorstelling() {
	  super();
	  this.uitvoeringen = new ArrayList<Uitvoering>();
  }
  
  public void setNaam(String naam) {
	  this.naam = naam;
  }
  
  public String getNaam() {
	  return this.naam;
  }
  
  public void setSoort(String soort) {
	  this.soort = soort;
  }
  
  public void setVoorstellingnr(int nr) {
	  this.voorstellingnr = nr;
  }
  
  public void setPrijzen(ArrayList<Rang> prijzen) {
	  this.prijzen = prijzen;
  }
  
  public int getVoorstellingnr() {
	  return this.voorstellingnr;
  }
  
  public ArrayList<Uitvoering> getUitvoeringen() {
	  return this.uitvoeringen;
  }
  
  //De klasse PlaatsDAO maakt gebruik van de onderstaande methode:
  /**
   * Geeft de prijs van deze voorstelling bij de gegeven rang
   * 
   * @param rang  de rang
   * @return prijs 
   */
  public double getRangprijs(String rang) {
    //TODO implementeer deze methode zodra je die nodig hebt 
    return 0; 
   }
  
  // De klasse VoorstellingsDAO maakt gebruik van de onderstaande methode:
  /**
   * Voegt een uitvoering toe aan deze voorstelling
   * 
   * @param u
   *          de uitvoering
   */
  public void addUitvoering(Uitvoering u) {
    uitvoeringen.add(u);
  }
  
  public String getVoorstellingInfo() {
	  return this.naam + " (" + this.soort + ")";
  }

 
  public String toString() {
	  return this.naam;
  }
  
  
}
