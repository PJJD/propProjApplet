package gui;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import model.Controller;


/**
 * GUI-klasse die de mogelijkheid om in/uit te loggen
 * @author Medewerker OU
 *
 */
public class LoginPanel extends JPanel {

  private Controller contr = null;

  private static final long serialVersionUID = 1L;
  private JLabel userLabel = null;
  private JLabel passwordLabel = null;
  private JTextField userTextField = null;
  private JPasswordField passwordField = null;
  private JButton loginOKButton = null;
  private JPanel loginInfoPanel = null;

  private JButton loguitKnop = null;

  /**
   * This is the default constructor
   */
  public LoginPanel() {
    super();
    initialize();
  }

  public LoginPanel(Controller contr) {
    this();
    this.contr = contr;
  }

  /**
   * This method initializes this
   * 
   * @return void
   */
  private void initialize() {
    passwordLabel = new JLabel();
    passwordLabel.setText("Password");
    passwordLabel.setBounds(new Rectangle(7, 68, 70, 24));
    userLabel = new JLabel();
    userLabel.setText("User");
    userLabel.setBounds(new Rectangle(8, 22, 58, 24));
    this.setSize(300, 253);
    this.setLayout(null);
    this.setBackground(new Color(255, 255, 204));
    this.add(getLoginInfoPanel(), null);

  }

  /**
   * This method initializes userTextField
   * 
   * @return javax.swing.JTextField
   */
  private JTextField getUserTextField() {
    if (userTextField == null) {
      userTextField = new JTextField();
      userTextField.setBounds(new Rectangle(94, 22, 134, 24));
      userTextField.setText("");
    }
    return userTextField;
  }

  /**
   * This method initializes passwordField
   * 
   * @return javax.swing.JPasswordField
   */
  private JPasswordField getPasswordField() {
    if (passwordField == null) {
      passwordField = new JPasswordField();
      passwordField.setBounds(new Rectangle(94, 68, 134, 24));
      passwordField.setText("");
    }
    return passwordField;
  }

  /**
   * This method initializes loginOKButton
   * 
   * @return javax.swing.JButton
   */
  private JButton getLoginOKButton() {
    if (loginOKButton == null) {
      loginOKButton = new JButton();
      loginOKButton.setText("Log in ");
      loginOKButton.setBounds(new Rectangle(94, 114, 134, 25));
      loginOKButton.addActionListener(new LoginListener()); 
    }
    return loginOKButton;
  }
  
  class LoginListener implements ActionListener {
	  public void actionPerformed(ActionEvent e) {
		  String gebruikersnaam = userTextField.getText();
		  String wachtwoord = passwordField.getText();
		  userTextField.setText("");
		  passwordField.setText("");
		  if (contr.logKlantIn(gebruikersnaam, wachtwoord)) {
			  toggleLoginLoguitKnop();
		  }
	  }
  }

  
  /**
   * Maakt loginknop disabled of enabled
   */
  public  void toggleLoginLoguitKnop() {
    //public want is ook nodig in registreerpanel,
    //na registreren ben je direct ingelogd 
    loginOKButton.setEnabled(!loginOKButton.isEnabled());
    loguitKnop.setEnabled(!loguitKnop.isEnabled());
  }

  /**
   * This method initializes loginInfoPanel
   * 
   * @return javax.swing.JPanel
   */
  private JPanel getLoginInfoPanel() {
    if (loginInfoPanel == null) {
      userTextField = getUserTextField();
      passwordField = getPasswordField();
      loginInfoPanel = new JPanel();
      loginInfoPanel.setLayout(null);
      loginInfoPanel.setBounds(new Rectangle(32, 67, 240, 164));
      loginInfoPanel.setVisible(true);
      loginInfoPanel.setEnabled(false);
      loginInfoPanel.add(userTextField, null);
      loginInfoPanel.add(userLabel, null);
      loginInfoPanel.add(passwordLabel, null);
      loginInfoPanel.add(passwordField, null);
      loginInfoPanel.add(getLoginOKButton(), null);
      loginInfoPanel.add(getLoguitKnop(), null);
    }
    return loginInfoPanel;
  }

  /**
   * This method initializes loguitKnop	
   * 	
   * @return javax.swing.JButton	
   */
  private JButton getLoguitKnop() {
    if (loguitKnop == null) {
      loguitKnop = new JButton();
      loguitKnop.setBounds(new Rectangle(9, 114, 73, 25));
      loguitKnop.setText("Log uit");
      loguitKnop.setEnabled(false);
      loguitKnop.setToolTipText("");
    }
    return loguitKnop;
  }
  
  

} // @jve:decl-index=0:visual-constraint="10,8"
