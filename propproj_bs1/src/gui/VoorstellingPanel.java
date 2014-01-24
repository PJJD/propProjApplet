package gui;


import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JScrollPane;

import java.awt.Rectangle;

import javax.swing.JList;
import javax.swing.JLabel;

import model.Controller;
import model.Uitvoering;

import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.KeyEvent;

import java.util.Observer;
import java.util.Observable;

import model.Voorstelling;

/**
 * GUI-klasse, die voorstellingen met bijbehorende uitvoeringen toont 
 * en mogelijkheid biedt zaalbezetting te zien bij een geslecteerde uitvoering 
 * 
 * @author Mederwerker OU
 * 
 */
public class VoorstellingPanel extends JPanel implements Observer {

  private Controller contr = null;



  private static final long serialVersionUID = 1L;
  private JScrollPane voorstellingScrollPane = null;
  private JList voorstellingList = null;
  private JScrollPane uitvoeringScrollPane = null;
  private JList uitvoeringList = null;
  private JLabel infoLabel = null;

  private JLabel naamLabel = null;

  /**
   * This is the default constructor
   */
  public VoorstellingPanel() {
    super();
    initialize();
  }

  public VoorstellingPanel(Controller contr) {
    this();
    this.contr = contr;
    mijnInit();
  }

  void mijnInit() {
	  contr.addObserver(this);
	  voorstellingList.setListData(contr.getVoorstellingen().toArray());
	  uitvoeringList.setListData(contr.getUitvoeringen().toArray());
  }
  

  /*
   * This method initializes this
   * 
   * @return void
   */
  private void initialize() {
    naamLabel = new JLabel();
    naamLabel.setBounds(new Rectangle(30, 12, 359, 30));
    naamLabel.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
    naamLabel.setText("");
    infoLabel = new JLabel();
    infoLabel.setBounds(new Rectangle(29, 55, 362, 30));
    infoLabel.setVerticalAlignment(SwingConstants.TOP);
    infoLabel.setText("Selecteer een voorstelling en een datum");
    this.setSize(425, 475);
    this.setLayout(null);
    this.setBackground(new Color(255, 255, 204));
    this.add(getVoorstellingScrollPane(), null);
    this.add(getUitvoeringScrollPane(), null);
    this.add(infoLabel, null);
    this.add(naamLabel, null);
  }

  /**
   * This method initializes voorstellingScrollPane
   * 
   * @return javax.swing.JScrollPane
   */
  private JScrollPane getVoorstellingScrollPane() {
    if (voorstellingScrollPane == null) {
      voorstellingScrollPane = new JScrollPane();
      voorstellingScrollPane.setBounds(new Rectangle(27, 100, 179, 230));
      voorstellingScrollPane.setViewportView(getVoorstellingList());
    }
    return voorstellingScrollPane;
  }

  /**
   * This method initializes voorstellingList
   * 
   * @return javax.swing.JList
   */
  private JList getVoorstellingList() {
    if (voorstellingList == null) {
      voorstellingList = new JList<Voorstelling>();
      voorstellingList.addListSelectionListener(new VoorstellingSelectionListener());
    }
    return voorstellingList;
  }

  class VoorstellingSelectionListener implements ListSelectionListener {
	  public void valueChanged(ListSelectionEvent e) {
		  if(!e.getValueIsAdjusting()) {
			  Voorstelling voorstelling = (Voorstelling) voorstellingList.getSelectedValue();
			  contr.setVoorstelling(voorstelling);
			  updateUitvoeringList();
		  }
	  }
  }
  

 

  /**
   * This method initializes uitvoeringScrollPane
   * 
   * @return javax.swing.JScrollPane
   */
  private JScrollPane getUitvoeringScrollPane() {
    if (uitvoeringScrollPane == null) {
      uitvoeringScrollPane = new JScrollPane();
      uitvoeringScrollPane.setBounds(new Rectangle(222, 100, 179, 95));
      uitvoeringScrollPane.setViewportView(getUitvoeringList());
    }
    return uitvoeringScrollPane;
  }

  /**
   * This method initializes uitvoeringList
   * 
   * @return javax.swing.JList
   */
  private JList getUitvoeringList() {
    if (uitvoeringList == null) {
      uitvoeringList = new JList();
      uitvoeringList.addListSelectionListener(new UitvoeringSelectionListener());
    }
    return uitvoeringList;
  }

  class UitvoeringSelectionListener implements ListSelectionListener {
	  public void valueChanged(ListSelectionEvent e) {
		  if(!e.getValueIsAdjusting()) {
			  Uitvoering uitvoering = (Uitvoering) uitvoeringList.getSelectedValue();
			  contr.setUitvoering(uitvoering);
		  }
	  }
  }
 
  private void updateUitvoeringList() {
	  uitvoeringList.setListData(contr.getUitvoeringen().toArray());
  }
  
  public void update(Observable obs, Object obj) {
	  String klantnaam = contr.getKlantNaam();
	  naamLabel.setText("Welkom, " + klantnaam);
  }

} // @jve:decl-index=0:visual-constraint="10,10"
