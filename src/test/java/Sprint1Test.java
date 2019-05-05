import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.example.LearnMySQL_Final.Person;
import com.example.LearnMySQL_Final.ServerManagementConnection;

public class Sprint1Test {
	
	
	ServerManagementConnection smc = new ServerManagementConnection("127.0.0.1");
	Person p = new Person("Carl Ginster","Carl","Ginster","1606558@students.wits.ac.za","1606558","1");
	Person testPerson = new Person("Test","Test","Test","Test@students.wits.ac.za","1","1");
	@Test public void Student_Login_Interface_First_Time_login_True() {
		boolean b= smc.isFirstTime("1606558");
		assertTrue(b);
	}
	
	
	@Test public void Student_Login_Interface_First_Time_login_False() {
		boolean b= smc.isFirstTime("1606443");
		assertFalse(b);
	}
	@Test public void Student_Login_Interface_Add_To_Databases() {
		if(smc.isFirstTime("1")) {
			boolean b= smc.addStudentToStudentTableInDatabase(testPerson);
			assertTrue(b);
		}
		else if(smc.isFirstTime("2")) {
			boolean b= smc.addStudentToStudentTableInDatabase(testPerson2);
			assertTrue(b);
		}
		
		
	}

}
