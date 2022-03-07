package Common;

public class DataProvidersQueries {

	public String FacebookLoginPostiveFlowCountQuery = "select count(id) from login_positive_testcases ";
	public String FacebookLoginPostiveFlowQuery = "select name, email from login_positive_testcases ";
	public int FacebookLoginPostiveFlowCount = 2;

	public String FacebookLoginNegativeFlowCountQuery = "select count(id) from login_negative_testcases ";
	public String FacebookLoginNegativeFlowQuery = "select email, password from login_negative_testcases ";
	public int FacebookLoginNegativeFlowCount = 2;

	public String FacebookSignUpPositiveFlowCountQuery = "select count(id) from signup_positive_flow ";
	public String FacebookSignUpPositiveFlowQuery = "SELECT firstname, lastname, email, password, gender, year FROM signup_positive_flow ";
	public int FacebookSignUpPositiveFlowCount = 6;

	public String FacebookSignUpNegativeFlowCountQuery = "select count(id) from signup_negative_flow ";
	public String FacebookSignUpNegativeFlowQuery = "SELECT firstname, lastname, email, password, gender, year FROM signup_negative_flow ";
	public int FacebookSignUpNegativeFlowCount = 6;

}
