package gui;


import javax.swing.JPanel;


import model.Controller;


import javax.swing.JButton;

import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JScrollPane;

import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JCheckBox;

import java.util.Observable;
import java.util.Observer;


/**
 * GUI-Klasse die inhoud van de bestelling toont en 
 * die mogelijkheid biedt de bestelling definitief te maken
 * @author Medewerker OU
 *
 */
public class WinkelwagenPanel extends JPanel implements Observer {

  private Controller contr = null;
  private static final long serialVersionUID = 1L;
  private JLabel infoLabel = null;
  private JScrollPane jScrollPane = null;
  private JTextArea bestelArea = null;
  private JButton bevestigKnop = null;
  private JCheckBox idealBox = null;

  /**
   * This is the default constructor
   */
  public WinkelwagenPanel() {
    super();
    initialize();
  }

  public WinkelwagenPanel(Controller contr) {
    this();
    this.contr = contr;
    contr.addObserver(this);
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize() {
    infoLabel = new JLabel();
    infoLabel.setBounds(new Rectangle(12, 6, 278, 27));
    infoLabel.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
    infoLabel.setText("Uw bestelling: ");
    this.setSize(300, 294);
    this.setLayout(null);
    this.setBackground(new Color(255, 255, 204));
    this.add(infoLabel, null);
    this.add(getJScrollPane(), null);
    this.add(getBevestigKnop(), null);
    this.add(getIdealBox(), null);
  }

  /**
   * This method initializes jScrollPane
   * 
   * @return javax.swing.JScrollPane
   */
  private JScrollPane getJScrollPane() {
    if (jScrollPane == null) {
      jScrollPane = new JScrollPane();
      jScrollPane.setBounds(new Rectangle(14, 41, 276, 121));
      jScrollPane.setViewportView(getBestelArea());
    }
    return jScrollPane;
  }

  /**
   * This method initializes besteltArea
   * 
   * @return javax.swing.JTextArea
   */
  private JTextArea getBestelArea() {
    if (bestelArea == null) {
      bestelArea = new JTextArea();
    }
    return bestelArea;
  }

 
 

  /**
   * This method initializes bevestigKnop	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getBevestigKnop() {
    if (bevestigKnop == null) {
      bevestigKnop = new JButton();
      bevestigKnop.setBounds(new Rectangle(11, 241, 274, 33));
      bevestigKnop.setText("Bevestig uw bestelling");
      bevestigKnop.addActionListener(new KlikBevestigKnop());
    }
    return bevestigKnop;
  }
  
  class KlikBevestigKnop implements ActionListener {
	  public void actionPerformed(ActionEvent e) {
		  boolean idealBetaling = idealBox.isSelected();
		  contr.schrijfWinkelwagenWeg(idealBetaling);
	  }
  }

  /**
   * This method initializes idealBox	
   * 	
   * @return javax.swing.JCheckBox	
   */
  private JCheckBox getIdealBox() {
    if (idealBox == null) {
      idealBox = new JCheckBox();
      idealBox.setBounds(new Rectangle(16, 206, 170, 21));
      idealBox.setText("Betalen via Ideal");
    }
    return idealBox;
  }

  public void update(Observable obs, Object obj) {
	  String rekening = "";
	  String klant = contr.getKlantNaam();
	  infoLabel.setText("Uw bestelling, " + klant);
	  rekening += contr.toonWinkelwagen();
	  bestelArea.setText(rekening);
  }


}  
