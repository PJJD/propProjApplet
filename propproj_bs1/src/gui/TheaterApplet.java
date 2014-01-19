package gui;


import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JApplet;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import db.TheaterException;





import java.awt.Rectangle;
import model.Controller;

/**
 * GUI-klasse die mogelijkheid biedt online kaarten voor theater te
 * bestellen
 * 
 * @author Medewerker OU
 * 
 */
public class TheaterApplet extends JApplet {

  private Controller contr; 
  // constanten voor positie van de tabbladen
  public static final int LOGIN = 0;
  public static final int TOONVOORSTELLINGEN = 1;
  public static final int TOONZAAL = 2;
  public static final int TOONWINKELWAGEN = 3;

  private JPanel jContentPane = null;
  private JTabbedPane jTabbedPane = null;

  /**
   * This is the xxx default constructor
   */
  public TheaterApplet() {
    super();
  }

  /**
   * This method initializes this
   * 
   */
  public void init() {
    this.setSize(570, 550);
    this.setContentPane(getJContentPane());
    mijnInit();
  }

  private void mijnInit() {
    jTabbedPane.setSelectedIndex(TheaterApplet.TOONVOORSTELLINGEN);
    contr.logKlantIn("testpj", "testpj");
  }

  /**
   * This method initializes jContentPane
   * 
   * @return javax.swing.JPanel
   */
  private JPanel getJContentPane() {
    if (jContentPane == null) {
      jContentPane = new JPanel();
      jContentPane.setLayout(null);
      jContentPane.add(getJTabbedPane(), null);
    }
    return jContentPane;
  }

  /**
   * This method initializes jTabbedPane
   * 
   * @return javax.swing.JTabbedPane
   */
  private JTabbedPane getJTabbedPane() {
    try {
      contr = new Controller();
    }
    catch (TheaterException e) {
      JOptionPane.showMessageDialog(null, e.getMessage() , "Fatale fout",
          JOptionPane.ERROR_MESSAGE);
      e.printStackTrace();
      System.exit(0);
    }
    if (jTabbedPane == null) {
      jTabbedPane = new JTabbedPane();
      jTabbedPane.setBounds(new Rectangle(10, 10, 550, 530));
      
      jTabbedPane.insertTab("login/loguit", null, new LoginPanel(contr), "",
          LOGIN);
      jTabbedPane.insertTab("voorstelling", null, new VoorstellingPanel(contr),"",TOONVOORSTELLINGEN);
      jTabbedPane.insertTab("zaal", null, new ZaalPanel(contr),"",TOONZAAL);
      jTabbedPane.insertTab("winkelwagen", null, new WinkelwagenPanel(contr),"",TOONWINKELWAGEN);
    }
    return jTabbedPane;
  }
}
