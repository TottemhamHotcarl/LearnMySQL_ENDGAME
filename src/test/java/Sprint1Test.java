import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.example.LearnMySQL_Final.GroupObject;
import com.example.LearnMySQL_Final.Person;
import com.example.LearnMySQL_Final.ServerManagementConnection;
import com.example.LearnMySQL_Final.User;

public class Sprint1Test {

	ServerManagementConnection smc = new ServerManagementConnection("127.0.0.1");
	Person p = new Person("Carl Ginster","Carl","Ginster","1606558@students.wits.ac.za","1606558","1");
	
	Person testPerson2 = new Person("Test","Test","Test","Test@students.wits.ac.za","2","1");
	
	
	//Test for first time login
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
	@Test public void Person_getID_Test() {
		assertTrue(p.getId().equals("1606558"));
	}	
	@Test public void Person_getFirstName_Test() {
		assertTrue(p.getFirstname().equals("Carl"));
	}	
	@Test public void Person_getLastName_Test() {
		assertTrue(p.getLastname().equals("Ginster"));
	}
	@Test public void Person_getName_Test() {
		assertTrue(p.getName().equals("Carl Ginster"));
	}
	@Test public void Person_getEmail_Test() {
		assertTrue(p.getEmail().equals("1606558@students.wits.ac.za"));
	}
	@Test public void Person_setID_Test() {
		Person testPerson = new Person("Test","Test","Test","Test@students.wits.ac.za","1","1");
		testPerson.setId("123456");
		assertTrue(testPerson.getId().equals("123456"));
	}	
	@Test public void Person_setFirstName_Test() {
		Person testPerson = new Person("Test","Test","Test","Test@students.wits.ac.za","1","1");
		testPerson.setFirstname("Bob");
		assertTrue(testPerson.getFirstname().equals("Bob"));
	}	
	@Test public void Person_setLastName_Test() {
		Person testPerson = new Person("Test","Test","Test","Test@students.wits.ac.za","1","1");
		testPerson.setLastname("Ginster");
		assertTrue(testPerson.getLastname().equals("Ginster"));
	}
	@Test public void Person_setName_Test() {
		Person testPerson = new Person("Test","Test","Test","Test@students.wits.ac.za","1","1");
		testPerson.setName("Carl Ginster");
		assertTrue(testPerson.getName().equals("Carl Ginster"));
	}
	@Test public void Person_setEmail_Test() {
		Person testPerson = new Person("Test","Test","Test","Test@students.wits.ac.za","1","1");
		testPerson.setEmail("1606558@students.wits.ac.za");
		assertTrue(testPerson.getEmail().equals("1606558@students.wits.ac.za"));
	}
	@Test public void Person_setAccess_Test() {
		Person testPerson = new Person("Test","Test","Test","Test@students.wits.ac.za","1","1");
		testPerson.setAccessLevel("2");
		assertTrue(testPerson.toString().equals("Person [name=" + "Test" + ", email=" + "Test@students.wits.ac.za" + ", id=" + "1" + ", access=" + "2" + "]"));
	}
	
	
	
	
	
	
	

}
