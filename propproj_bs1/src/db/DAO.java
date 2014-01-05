package db;

import java.sql.*;


/**
 * Data access object superklasse. Maakt en beheert database connection (met
 * behulp van singleton pattern).
 */

public abstract class DAO {

  // database gegevens, zonodig aanpassen 
  
	private static final String DATABASE_URL = "jdbc:firebirdsql://localhost/C:/Users/pjd/Desktop/OUNL/Openstaand/Propedeuseproject/propproj.fdb";
	  private static final String DATABASE_USER = "sysdba";
	  private static final String DATABASE_PASSWORD = "masterkey";
	  private static final String DRIVERNAME = "org.firebirdsql.jdbc.FBDriver";
  
//  private static final String DATABASE_URL = "jdbc:firebirdsql://www.ntwpracticumnet.ou.nl/d:/inetpub/wwwroot/propedeuseproject/databases/propprojXXX.fdb";
//  private static final String DATABASE_USER = "PROPPROJECT";
//  private static final String DATABASE_PASSWORD = "student";
  
  
  // de database connection
  private static Connection dbconnection;

  /**
   * Constructor.
   */
  public DAO() {
  }

  /**
   * Haalt de singleton database connectie op (en maak 'm aan indien nog niet
   * bestaand)
   * 
   * @return een werkende database-connectie
   * @throws TheaterException
   *           indien het opzetten van de verbinding niet goed ging
   */
  protected synchronized Connection getDBConnection() throws TheaterException {
    if (dbconnection == null) {
      try {
        Class.forName(DRIVERNAME).newInstance();
        dbconnection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER,
            DATABASE_PASSWORD);
      } catch (Exception e) {
        throw new TheaterException("Verbinding met Firebird-database mislukt"+ e.getMessage());
      }
    }
    return dbconnection;
  }
}
