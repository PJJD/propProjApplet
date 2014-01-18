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
  
  

 

}
