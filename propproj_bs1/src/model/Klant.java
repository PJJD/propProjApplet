package model;

import javax.swing.JOptionPane;

import db.KlantDAO;
import db.TheaterException;



/**
 * Klasse die een klant representeert
 * 
 * @author Medewerker OU
 * 
 */
public class Klant {
  


  private int klantnr = 0;
  private String naam = null;
  private String straat = null;
  private String huisnr = null;
  private String postcode = null;
  private String telefoon = null;
  private String woonplaats = null;
  private String mobiel = null;
  private String email = null;
  private String gebruikersnaam = null;
  private String wachtwoord = null;
  private boolean isVasteKlant = false;
  private double seizoentotaal = 0;
  
  public int getKlantnr() {
	  return klantnr;
  }
  public void setKlantnr(int klantnr) {
	  this.klantnr = klantnr;
  }
  public String getNaam() {
	  return naam;
  }
  public void setNaam(String naam) {
	  this.naam = naam;
  }
  public String getStraat() {
	  return straat;
  }
  public void setStraat(String straat) {
	  this.straat = straat;
  }
  public String getHuisnr() {
	  return huisnr;
  }
  public void setHuisnr(String huisnr) {
	  this.huisnr = huisnr;
  }
  public String getPostcode() {
	  return postcode;
  }
  public void setPostcode(String postcode) {
	  this.postcode = postcode;
  }
  public String getTelefoon() {
	  return telefoon;
  }
  public void setTelefoon(String telefoon) {
	  this.telefoon = telefoon;
  }
  public String getWoonplaats() {
	  return woonplaats;
  }
  public void setWoonplaats(String woonplaats) {
	  this.woonplaats = woonplaats;
  }
  public String getMobiel() {
	  return mobiel;
  }
  public void setMobiel(String mobiel) {
	  this.mobiel = mobiel;
  }
  public String getEmail() {
	  return email;
  }
  public void setEmail(String email) {
	  this.email = email;
  }
  public String getGebruikersnaam() {
	  return gebruikersnaam;
  }
  public void setGebruikersnaam(String gebruikersnaam) {
	  this.gebruikersnaam = gebruikersnaam;
  }
  public String getWachtwoord() {
	  return wachtwoord;
  }
  public void setWachtwoord(String wachtwoord) {
	  this.wachtwoord = wachtwoord;
  }
  public boolean isVasteKlant() {
	  return isVasteKlant;
  }
  public void setVasteKlant(boolean isVasteKlant) {
	  this.isVasteKlant = isVasteKlant;
  }
  public double getSeizoentotaal() {
	  return seizoentotaal;
  }
  public void setSeizoentotaal(double seizoentotaal) {
	  this.seizoentotaal = seizoentotaal;
  } 
  
  public static Klant getKlant(String gebruikersnaam, String wachtwoord) throws TheaterException {
	  Klant klant = null;
	  KlantDAO kdao = KlantDAO.getInstance();
	  klant = kdao.logIn(gebruikersnaam, wachtwoord);
	  return klant;
  }
}
