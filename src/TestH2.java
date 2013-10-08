import java.sql.*;

/**
 * Testing out H2.
 */
public class TestH2 {

    public static void main(String[] args) throws Exception {



	/*
    // conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
	conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
	Statement stmt = conn.createStatement() ;
        // add application code here
	// Execute the query
	ResultSet rs = stmt.executeQuery( "SELECT table_name FROM information_schema.tables" ) ;

	// Loop through the result set
	while( rs.next() ) System.out.println( rs.getString(1) );

	// Close the result set, statement and the connection
	rs.close() ;
	stmt.execute("CREATE TABLE IF NOT EXISTS CUSTOMERS(ID INT PRIMARY KEY,NAME VARCHAR(255))");
	stmt.execute("CREATE USER IF NOT EXISTS TestDB PASSWORD 'abcd'");
	stmt.execute("GRANT ALL ON CUSTOMERS TO TestDB");
	stmt.close() ;
        conn.close();
	*/
                //        conn = DriverManager.getConnection("jdbc:h2:~/test", "TestDB", "abcd");
        conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");

        stmt = conn.createStatement() ;
        stmt.execute("INSERT INTO CUSTOMERS VALUES ('1234567890', 'Rajiv Ramnath')");
    }
}
