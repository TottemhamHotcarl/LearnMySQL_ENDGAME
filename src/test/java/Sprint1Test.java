import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.example.LearnMySQL_Final.Person;
import com.example.LearnMySQL_Final.ServerManagementConnection;
import com.example.LearnMySQL_Final.User;

public class Sprint1Test {

	ServerManagementConnection smc = new ServerManagementConnection("127.0.0.1");
	Person p = new Person("Carl Ginster","Carl","Ginster","1606558@students.wits.ac.za","1606558","1");
	Person testPerson = new Person("Test","Test","Test","Test@students.wits.ac.za","1","1");
	Person testPerson2 = new Person("Test","Test","Test","Test@students.wits.ac.za","2","1");
	
	@Test public void Student_Login_Interface_First_Time_login_True() {
		boolean b= smc.isFirstTime("1606558");
		assertTrue(b);
	}
	
	
	@Test public void Student_Login_Interface_First_Time_login_False() {
		boolean b= smc.isFirstTime("1606443");
		assertFalse(b);
	}
	
	
	// test for user
	@Test public void User_Set_Person() {
		User ur = new User();
		ur.setperson(p);
		User ur2 = new User();
		assertTrue(ur2.person.getId().equals("1606558"));
	}
	
	@Test public void User_Set_Person_constructor() {
		User ur = new User(p);
		User ur2 = new User();
		assertTrue(ur2.person.getId().equals("1606558"));
	}
	
	//test for Person
	
	
	
	

}
