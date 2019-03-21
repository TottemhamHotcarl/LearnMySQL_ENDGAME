

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.example.LearnMySQL_Final.LoginTool;

import org.junit.*;

public class LoginToolTest {
	@Test public void loginAdmin() {
		LoginTool lt = new LoginTool("admin","admin");
		assertTrue(lt.verifyStudentDetails());
	}
	
	@Test public void loginStudent() {
		LoginTool lt = new LoginTool("1606558","Solarc1964@");
		assertTrue(lt.verifyStudentDetails());
	}

	
	@Test public void loginWrongDetails() {
		LoginTool lt = new LoginTool("bob","bob");
		assertFalse(lt.verifyStudentDetails());
	}
	@Test public void loginStudentPerson() {
		LoginTool lt = new LoginTool("1606558","Solarc1964@");
		lt.verifyStudentDetails();
		
		assertTrue( "1606558".equals(lt.getPerson().getId()));
	}
	
	
	
}
