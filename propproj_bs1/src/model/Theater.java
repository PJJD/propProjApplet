package model;

/**
 * Klasse met constanten voor de Theater toepassing
 * 
 * @author Medewerker OU
 * 
 */
public class Theater {

  private static int aantalRijen = 15;
  private static int stoelenPerRij = 10;

  private static double minimumbedrag = 250.0;
  private static int vasteklantkorting = 10;

  private static int maxPerUitvoering = 6;

  
  
  //TODO hier vast de getters en setters opgenomen, 
  //anders werkt de constructor van Controller niet. 
  public static int getAantalRijen() {
    return aantalRijen;
  }
  public static void setAantalRijen(int aantalRijen) {
    Theater.aantalRijen = aantalRijen;
  }
  public static int getStoelenPerRij() {
    return stoelenPerRij;
  }
  public static void setStoelenPerRij(int stoelenPerRij) {
    Theater.stoelenPerRij = stoelenPerRij;
  }
  public static double getMinimumbedrag() {
    return minimumbedrag;
  }
  public static void setMinimumbedrag(double minimumbedrag) {
    Theater.minimumbedrag = minimumbedrag;
  }
  public static int getVasteklantkorting() {
    return vasteklantkorting;
  }
  public static void setVasteklantkorting(int vasteklantkorting) {
    Theater.vasteklantkorting = vasteklantkorting;
  }
  public static int getMaxPerUitvoering() {
    return maxPerUitvoering;
  }
  public static void setMaxPerUitvoering(int maxPerUitvoering) {
    Theater.maxPerUitvoering = maxPerUitvoering;
  }
  

}
