

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.*;
import com.example.LearnMySQL_Final.LoginTool;
import com.example.LearnMySQL_Final.Person;
import com.example.LearnMySQL_Final.User;
import com.vaadin.ui.Button;


public class LoginToolTest {
	@Test public void loginAdmin() {
		LoginTool lt = new LoginTool("admin","admin");
		assertTrue(lt.verifyStudentDetails());
	}
	

	@Test public void loginWrongDetails() {
		LoginTool lt = new LoginTool("bob","bob");
		assertFalse(lt.verifyStudentDetails());
	}
	
	@Test public void userTest() {
		Person p = new Person("Carl Ginster","Carl","Ginster","1606558@students.wits.ac.za","1606558","1");
		User u = new User();
		u.setperson(p);
		u = new User(p);
		
		
		assertTrue(p.toString().equals(u.person.toString()));
	}
	

	@Test public void welcomeUI() {
		/*com.example.LearnMySQL_Final.welcomeUI wu = new com.example.LearnMySQL_Final.welcomeUI();
		com.example.LearnMySQL_Final.practiceUI wu2 = new com.example.LearnMySQL_Final.practiceUI();
		com.example.LearnMySQL_Final.MyUI myui = new com.example.LearnMySQL_Final.MyUI();
		//com.example.LearnMySQL_Final.saveUI msyui = new com.example.LearnMySQL_Final.saveUI();
		myui.checkFirstTimeLogin();
		
		Button b = (Button)wu2.getComponent(0);
		b.click();
		assertTrue(1 == 1);*/
	}
	
	

	
}
