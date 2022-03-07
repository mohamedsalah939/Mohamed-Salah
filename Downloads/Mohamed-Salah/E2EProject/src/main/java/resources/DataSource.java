package resources;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.testng.annotations.DataProvider;

import Common.DataProvidersQueries;

public class DataSource extends base {

	public String getTestScope() throws IOException {
		Properties prop = uploadProp();
		String testScope = (String) prop.get("testscope");
		return testScope;

	}

	@DataProvider(name = "genericDataProviderMethod")
	public Object[][] dataProviderMethod(Method m) throws SQLException, IOException {
		DBConnection db = new DBConnection();
		Statement stmt = db.dbConnect();
		String countQuery = "";
		String query = "";
		int count = 0;
		DataProvidersQueries dpq = new DataProvidersQueries();

		if (m.getName().equalsIgnoreCase("FacebookLoginPostiveFlow")) {
			countQuery = dpq.FacebookLoginPostiveFlowCountQuery;
			query = dpq.FacebookLoginPostiveFlowQuery;
			count = dpq.FacebookLoginPostiveFlowCount;
		}
		
		else if (m.getName().equalsIgnoreCase("FacebookLoginNegativeFlow")) {
			countQuery = dpq.FacebookLoginNegativeFlowCountQuery;
			query = dpq.FacebookLoginNegativeFlowQuery;
			count = dpq.FacebookLoginNegativeFlowCount;
		}
		
		else if (m.getName().equalsIgnoreCase("FacebookSignupPositiveFlow")) {
			countQuery = dpq.FacebookSignUpPositiveFlowCountQuery;
			query = dpq.FacebookSignUpPositiveFlowQuery;
			count = dpq.FacebookSignUpPositiveFlowCount;
		}
		
		else if (m.getName().equalsIgnoreCase("FacebookSignupNegativeFlow")) {
			countQuery = dpq.FacebookSignUpNegativeFlowCountQuery;
			query = dpq.FacebookSignUpNegativeFlowQuery;
			count = dpq.FacebookSignUpNegativeFlowCount;
		}
		
		// Executing the query
		ResultSet rs = stmt.executeQuery(countQuery);
		// Retrieving the result
		rs.next();
		int rowCount = rs.getInt(1);
		// Get the test case data
		ResultSet res = stmt.executeQuery(query);

		// convert the results coming from query to object array to be used by TestNG
		Object[][] data = new Object[rowCount][count];
		int row = 0;
		while (res.next()) {
			for (int i = 0; i < count; i++) {
				if ((res.getObject(i + 1)).equals("")) {
					data[row][i] = "null";
				} else {
					data[row][i] = res.getObject(i + 1);
				}
			}
			row++;
		}
		return data;

	}

}
