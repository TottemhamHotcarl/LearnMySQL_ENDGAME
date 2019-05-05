import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.example.LearnMySQL_Final.ServerManagementConnection;

public class Sprint1Test {
	
	@Test public void Student_Login_Interface_First_Time_login_True() {
		ServerManagementConnection smc = new ServerManagementConnection("127.0.0.1");
		boolean b= smc.isFirstTime("1606558");
		assertTrue(b);
	}

}
