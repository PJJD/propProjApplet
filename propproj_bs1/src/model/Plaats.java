package model;

/**
 * Klasse die een plaats voor een uitvoering representeert
 * @author medewerker OU
 *
 */
public class Plaats {
  private int plaatsnr = 0;
  private int rijnr = 0;
  private int stoelnr = 0;
  private Rang rang = null;
  private boolean bezet = false;
  private boolean gereserveerd = false;
  
  public Plaats() {
	  super();
  }
  
  public int getPlaatsnr() {
	  return this.plaatsnr;
  }
  
  public void setPlaatsnr(int nr) {
	  this.plaatsnr = nr;
  }
  
  public int getRijnr() {
	  return this.rijnr;
  }
  
  public void setRijnr(int nr) {
	  this.rijnr = nr;
  }
  
  public int getStoelnr() {
	  return this.stoelnr;
  }
  
  public void setStoelnr(int nr) {
	  this.stoelnr = nr;
  }
  
  public Rang getRang() {
	  return this.rang;
  }
  
  public void setRang(Rang rang) {
	  this.rang = rang;
  }
  
  public boolean getBezet() {
	  return this.bezet;
  }
  
  public void setBezet(boolean bezet) {
	  this.bezet = bezet;
  }
  
  public boolean getGereserveerd() {
	  return this.gereserveerd;
  }
  
  public void setGereserveerd(boolean gereserveerd) {
	  this.gereserveerd = gereserveerd;
  }
  
  public String toString() {
	  return "\nRijnr: " + this.rijnr 
			  + "\nStoelnr: " + this.stoelnr 
			  + "\nPlaatsnr: " + this.plaatsnr 
			  + "\nRang: " + this.rang.getNaam() + "\n";
  }
  
  public void toggleGereserveerd() {
	  boolean nieuweWaarde = !this.gereserveerd;
	  this.gereserveerd = nieuweWaarde;
  }
}
