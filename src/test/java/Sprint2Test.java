import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.*;

import com.example.LearnMySQL_Final.Person;
import com.example.LearnMySQL_Final.ServerManagementConnection;
import com.example.LearnMySQL_Final.StudentQueryHelper;
import com.example.LearnMySQL_Final.triplet;




public class Sprint2Test {
	
	ServerManagementConnection smc = new ServerManagementConnection("127.0.0.1");
	Person p = new Person("Carl Ginster","Carl","Ginster","1606558@students.wits.ac.za","1606558","1");
	
	// Test for User Story form History_Tab
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
	
	// Test for User Story form Query_box
	
	StudentQueryHelper sqh = new StudentQueryHelper(p, "127.0.0.1");
	
	@Test public void The_Query_Box_Valid_Update_Query_Test() {
		String t = sqh.queryUpdateRun("INSERT INTO TEST VALUES(2, 'hi')");
		System.out.println(t);
		assertTrue(t.equals("Query OK, 0 rows affected"));
	}
	
	@Test public void The_Query_Box_InValid_Update_Query_Test() {
		String t = sqh.queryUpdateRun("CREATE TEST4 (COLUMN_1 INT(6))");
		System.out.println(t);
		assertTrue(t.equals("You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'TEST4 (COLUMN_1 INT(6))' at line 1"));
	}
	
	
	
	
	
	
	
}
