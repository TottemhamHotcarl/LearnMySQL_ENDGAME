import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.*;

import com.example.LearnMySQL_Final.Person;
import com.example.LearnMySQL_Final.ServerManagementConnection;
import com.example.LearnMySQL_Final.triplet;




public class Sprint3Test {
	
	ServerManagementConnection smc = new ServerManagementConnection("127.0.0.1");
	Person p = new Person("Carl Ginster","Carl","Ginster","1606558@students.wits.ac.za","1606558","1");
	
	
	@Test public void History_Tab_Get_History_Valid_Test() {
		triplet t = smc.getStudentHistoryQuery(p);
		assertTrue(t.queryOk);
	}
	
	@Test public void History_Tab_Get_History_With_Search_Valid_Test() {
		triplet t = smc.getStudentHistoryQueryWithSearch(p, "*");
		assertTrue(t.queryOk);
	}
	
	
	
	@Test public void History_Tab_Add_History_Valid_Test() {
		boolean t = smc.addStudentHistoryQuery(p, "Test");
		assertTrue(t);
	}
	
	@Test public void History_Tab_Add_History_Delete_Test() {
		boolean t = smc.deleteHistory("1");
		assertTrue(t);
	}
	
	
	
	
	
}
