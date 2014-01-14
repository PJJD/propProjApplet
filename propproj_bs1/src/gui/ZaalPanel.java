package gui;

import java.awt.Color;

import javax.swing.JPanel;

import model.Controller;

import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.SwingConstants;

import db.TheaterException;

import java.awt.Button;

import model.Plaats;
import java.util.Observer;
import java.util.Observable;

/**
 * GUI-klasse, toont zaalbezetting bij een uitvoering en biedt mogelijkheid om
 * plaatsen voor deze uitvoering te selecteren en te bestellen
 * 
 * @author Mederwerker OU
 * 
 */
public class ZaalPanel extends JPanel implements Observer {

  private int HGAP = 2;
  private int VGAP = 4;
  private Controller contr = null;
 
  private static final long serialVersionUID = 1L;
  private JPanel plaatsenPanel = null;
  private JLabel infoLabel = null;
  private JButton bevestigSelectieKnop = null;
  private JLabel uitvoeringnaamLabel = null;

  private JLabel podiumLabel = null;
  private JLabel rang1Label = null;
  private JLabel rang2Label = null;
  private JLabel klantLabel = null;

  /**
   * This is the default constructor
   */
  public ZaalPanel() {
    super();
    try {
    	this.contr = new Controller();
    } catch (TheaterException e) {
    	e.printStackTrace();
    }
    initialize();
  }

  public ZaalPanel(Controller contr) {
    this();
    this.contr = contr;
    contr.addObserver(this);
  }
  
  public void mijnInit() {
	mijnUpdate();
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize() {

    rang2Label = new JLabel();
    rang2Label.setBounds(new Rectangle(6, 240, 50, 19));
    rang2Label.setText("rang 2");
    rang1Label = new JLabel();
    rang1Label.setBounds(new Rectangle(8, 147, 51, 15));
    rang1Label.setText("rang 1");
    podiumLabel = new JLabel();
    podiumLabel.setBounds(new Rectangle(128, 50, 352, 85));
    podiumLabel.setBackground(new Color(255, 255, 204));
    podiumLabel.setFont(new Font("Wide Latin", Font.BOLD, 24));
    podiumLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    podiumLabel.setHorizontalAlignment(SwingConstants.CENTER);
    podiumLabel.setText("PODIUM");
    uitvoeringnaamLabel = new JLabel();
    uitvoeringnaamLabel.setBounds(new Rectangle(13, 13, 525, 49));
    uitvoeringnaamLabel.setFont(new Font("Dialog", Font.BOLD, 14));
    uitvoeringnaamLabel
        .setText("Zaalbezetting voor <naam en datum>  (dus de geselecteerde uitvoering)");
    klantLabel = new JLabel();
    klantLabel.setBounds(new Rectangle(20, 10, 525, 15));
    klantLabel.setFont(new Font("Dialog", Font.PLAIN, 11));
    infoLabel = new JLabel();
    infoLabel.setBounds(new Rectangle(13, 453, 235, 30));
    infoLabel.setText("Selecteer de gewenste plaatsen");
    this.setLayout(null);
    this.setSize(600, 600);
    this.setBackground(new Color(255, 255, 204));
    this.add(getPlaatsenPanel(), null);
    this.add(infoLabel, null);
    this.add(klantLabel);
    this.add(getBevestigSelectieKnop(), null);
    this.add(uitvoeringnaamLabel, null);
    this.add(podiumLabel, null);
    this.add(rang1Label, null);
    this.add(rang2Label, null);
  }

  /**
   * This method initializes rang1Panel
   * 
   * @return javax.swing.JPanel
   */
  private JPanel getPlaatsenPanel() {
    if (plaatsenPanel == null) {
      GridLayout gridLayout1 = new GridLayout();
      // geef zelf het aantal rijen en stoelen op 
      gridLayout1.setRows(contr.getAantalRijen());
      gridLayout1.setColumns(contr.getAantalStoelenPerRij());
      gridLayout1.setHgap(HGAP);
      gridLayout1.setVgap(VGAP);
      plaatsenPanel = new JPanel();
      plaatsenPanel.setPreferredSize(new Dimension(-1, 4));
      plaatsenPanel.setLayout(gridLayout1);
      plaatsenPanel.setBounds(new Rectangle(63, 123, 470, 324));
    }
    return plaatsenPanel;
  }

  /**
   * This method initializes bevestigSelectieKnop
   * 
   * @return javax.swing.JButton
   */
  private JButton getBevestigSelectieKnop() {
    if (bevestigSelectieKnop == null) {
      bevestigSelectieKnop = new JButton();
      bevestigSelectieKnop.setBounds(new Rectangle(267, 453, 163, 30));
      bevestigSelectieKnop.setText("bevestig selectie");
      }
    return bevestigSelectieKnop;
  }
  
  public void update(Observable obs, Object obj) {
	  klantLabel.setText("Welkom, " + contr.getKlantNaam());
  }
  
  
  public void mijnUpdate() {
	  uitvoeringnaamLabel.setText("Zaalbezetting voor " + contr.getVoorstellingInfo() + " op " + contr.getUitvoeringInfo());
	  plaatsenPanel.removeAll();
	  for (Plaats p: contr.getZaalbezetting()) {
		  Button b = new Button(""+p.getPlaatsnr());
		  if (p.getBezet()) {
			  b.setBackground(Color.RED);
		  } else if (p.getGereserveerd()) {
			  b.setBackground(Color.YELLOW);
		  } else {
			  b.setBackground(Color.GREEN);
		  }
		  plaatsenPanel.add(b);
	  }
  }

} // @jve:decl-index=0:visual-constraint="10,7"
