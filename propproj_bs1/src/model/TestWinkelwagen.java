package model;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

public class TestWinkelwagen {




	@Test
	public void testBerekenprijs() {
		
		/* Opzetten klant1 */
		Klant klant1 = new Klant();
		klant1.setSeizoentotaal(0.0);
		klant1.setVasteKlant(true);
		Winkelwagen winkelwagen1 = new Winkelwagen(klant1);
		
		
		/* Opzetten klant2 */ 
		Klant klant2 = new Klant();
		klant2.setSeizoentotaal(0.0);
		klant2.setVasteKlant(false);
		Winkelwagen winkelwagen2 = new Winkelwagen(klant2);
		
		Voorstelling voorstelling = new Voorstelling();
		Uitvoering uitvoering11 = new Uitvoering();
		uitvoering11.setVoorstelling(voorstelling);
		ArrayList<Plaats> zaalbezetting = new ArrayList<Plaats>();
		ArrayList<Rang> prijzen = new ArrayList<Rang>();
		Rang rang1 = new Rang();
		Rang rang2 = new Rang();
		rang1.setNaam("eerste");
		rang1.setPrijs(63.00);
		rang2.setNaam("tweede");
		rang2.setPrijs(35.00);
		
		/* prijzen toevoegen aan de voorstelling */
		prijzen.add(rang1);
		prijzen.add(rang2);
		voorstelling.setPrijzen(prijzen);

		Plaats plaats1 = new Plaats();
		plaats1.setPlaatsnr(1);
		plaats1.setRang(rang1);
		Plaats plaats2 = new Plaats();
		plaats2.setPlaatsnr(2);
		plaats2.setRang(rang1);
		Plaats plaats3 = new Plaats();
		plaats3.setPlaatsnr(3);
		plaats3.setRang(rang2);
		Plaats plaats4 = new Plaats();
		plaats4.setPlaatsnr(4);
		plaats4.setRang(rang2);
		
		zaalbezetting.add(plaats1);
		zaalbezetting.add(plaats2);
		zaalbezetting.add(plaats3);
		zaalbezetting.add(plaats4);
		
		uitvoering11.setZaalbezetting(zaalbezetting);
		Uitvoering uitvoering12 = new Uitvoering();
		uitvoering12.setVoorstelling(voorstelling);
		uitvoering12.setZaalbezetting(zaalbezetting);
		
		
		ArrayList<Plaats> gereserveerdePlaatsen = new ArrayList<Plaats>();
		gereserveerdePlaatsen.add(plaats1);
		gereserveerdePlaatsen.add(plaats2);
		Boodschap boodschap1 = new Boodschap(uitvoering11, gereserveerdePlaatsen);
		//Boodschap boodschap2 = new Boodschap(uitvoering12, gereserveerdePlaatsen);
		
		winkelwagen1.boodschapToevoegen(boodschap1);  

		assertEquals("2 plaatsen van 63.0 moet uitkomen op 126.0", 126.00, winkelwagen1.berekenprijs(),0.0);
		assert(!klant1.isVasteKlant());
		
		rang1.setPrijs(125.00);
		
		winkelwagen2.boodschapToevoegen(boodschap1);
		
		assertEquals("2 x 125.00 = 250.00", 250.00, boodschap1.berekenprijs(), 0.0);
		assertEquals("Seizoentotaal = 0.0", 0.0, klant2.getSeizoentotaal(), 0.0);
		assertEquals("Minimumbedrag = 250.0", 250.0, Theater.getMinimumbedrag(), 0.0);
		
		assertEquals("2 x 125.0 = 250.0 => 250.0 - 25.0 = 225.0", 225.00, winkelwagen2.berekenprijs(), 0.0);
		
    klant1.setSeizoentotaal(150.0);
    rang1.setPrijs(32.0);

    assertEquals("2x32 = 64", 64.0, winkelwagen1.berekenprijs(), 0.0);

    rang1.setPrijs(49.99);

    assertEquals("2x49.99 = 99.98", 99.98, winkelwagen1.berekenprijs(), 0.0); 
		
    rang1.setPrijs(50.0);
    assertEquals("2x50 = 100, min korting = 90", 90.0, winkelwagen1.berekenprijs(), 0.0);
		
	
	}

}
