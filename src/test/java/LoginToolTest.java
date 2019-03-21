

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.example.LearnMySQL_Final.LoginTool;

import org.junit.*;

public class LoginToolTest {
	@Test public void loginAdmin() {
		LoginTool lt = new LoginTool("admin","admin");
		assertTrue(lt.verifyStudentDetails());
	}
	
	@Test public void loginWrongDetails() {
		LoginTool lt = new LoginTool("bob","bob");
		assertFalse(lt.verifyStudentDetails());
	}
	
	
	
	
}
