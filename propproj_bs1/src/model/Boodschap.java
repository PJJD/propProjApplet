package model;

import java.util.ArrayList;
import db.TheaterException;


/**
 * Klasse die een boodschap in de winkelwagen representeert .
 * Een boodschap is een reservering van plaatsen voor een uitvoering   
 * 
 * @author Mederwerker OU
 * 
 */
public class Boodschap {
  
  private Uitvoering uitvoering = null;
  private ArrayList<Plaats> gereserveerdePlaatsen = new ArrayList<Plaats>();

  public Boodschap(Uitvoering u, ArrayList<Plaats> plaatsen) {
	  this.uitvoering = u;
	  this.gereserveerdePlaatsen = plaatsen;
  }
  
  public Uitvoering getUitvoering() {
	  return this.uitvoering;
  }
  
  public ArrayList<Plaats> getGereserveerdePlaatsen() {
	  return this.gereserveerdePlaatsen;
  }
  
  public void setGereserveerdePlaatsen(ArrayList<Plaats> gereserveerdePlaatsen) {
	  this.gereserveerdePlaatsen = gereserveerdePlaatsen;
  }
  
  public void update(int plaatsnr) {
	  Plaats plaats = null;
	  ArrayList<Plaats> zaalbezetting = new ArrayList<Plaats>();
	  try {
		  zaalbezetting = uitvoering.getZaalbezetting();
	  } catch  (TheaterException e){
		  e.printStackTrace();
	  }
	  for (Plaats p: zaalbezetting) {
		  if (p.getPlaatsnr() == plaatsnr) {
			  plaats = p;
		  }
	  }
	  if (plaats.getGereserveerd()) {
		  gereserveerdePlaatsen.add(plaats);
	  } else {
		  gereserveerdePlaatsen.remove(plaats);
	  }
  }
  
  public String toString() {
	  String voorstelling = uitvoering.getVoorstelling().getNaam();
	  String datum = uitvoering.getDatum();
	  return voorstelling + "\t" + datum + " (" + gereserveerdePlaatsen.size() + " plaatsen)";
  }
  

 

}
