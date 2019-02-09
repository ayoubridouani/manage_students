import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Model {
	Statement stmt;
	Model(){
		stmt = new ConnectDB().connection();
	}
	
	public ArrayList<String> getTable() throws SQLException {
		ArrayList<String> tables = new ArrayList<String>();
		ResultSet res = stmt.executeQuery("show tables");
		while(res.next()) {
			tables.add(res.getString(1));
		}
		res.close();
		return tables;
	}
	
	public ArrayList<String> getElementsTable(String table) throws SQLException {
		ArrayList<String> columnsTable = new ArrayList<String>();
		ResultSet res = stmt.executeQuery("desc " + table);
		while(res.next()) {
			columnsTable.add(res.getString(1));
		}
		res.close();
		return columnsTable;
	}
	
	public Object[][] getRows(String request,String table) throws SQLException {
		
		ResultSet res_number_of_rows = stmt.executeQuery("select count(*) from " + table);
		int numberRow = 0;
		while(res_number_of_rows.next())
			numberRow = res_number_of_rows.getInt(1);
		res_number_of_rows.close();
			
		ResultSet res = stmt.executeQuery(request);
		ResultSetMetaData metadata = res.getMetaData();
		int columnCount = metadata.getColumnCount();
		
		
		Object[][] rows = new Object[numberRow][columnCount];
		int j=0;
		while(res.next()) {
			for(int i=1;i<columnCount+1;i++) {
				rows[j][i-1] = res.getObject(i);
			}
			j++;
		}
		res.close();
		return rows;
	}
}
