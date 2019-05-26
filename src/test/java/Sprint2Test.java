import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.*;

import com.example.LearnMySQL_Final.History;
import com.example.LearnMySQL_Final.HistoryObject;
import com.example.LearnMySQL_Final.Person;
import com.example.LearnMySQL_Final.ServerManagementConnection;
import com.example.LearnMySQL_Final.StudentQueryHelper;
import com.example.LearnMySQL_Final.triplet;




public class Sprint2Test {
	
	ServerManagementConnection smc = new ServerManagementConnection("127.0.0.1");
	Person p = new Person("Carl Ginster","Carl","Ginster","1606558@students.wits.ac.za","1606558","1");
	
		//add student to database
	@Test public void SMC_Add_Student_to_database() {
		boolean t = smc.addStudentDatabase(ptest);
		assertTrue(t);
	}
	
	
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
	
	@Test public void History_Tab_History_Delete_Test() {
		boolean t = smc.deleteHistory("1");
		assertTrue(t);
	}
	
	// Test for User Story form Query_box
	
	StudentQueryHelper sqh = new StudentQueryHelper(p, "127.0.0.1");
	
	@Test public void The_Query_Box_Valid_Update_Query_Test() {
		String t = sqh.queryUpdateRun("INSERT INTO TEST VALUES(2, 'hi')");
		System.out.println(t);
		assertTrue(t.equals("Query OK, 1 rows affected"));
	}
	
	@Test public void The_Query_Box_InValid_Update_Query_Test() {
		String t = sqh.queryUpdateRun("CREATE TEST4 (COLUMN_1 INT(6))");
		System.out.println(t);
		assertTrue(t.equals("You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'TEST4 (COLUMN_1 INT(6))' at line 1"));
	}
	
	
	//History Object Test
	
	@Test public void History_Object_Const_Test() {
		HistoryObject ho = new HistoryObject("SELECT * FROM NOW", "1998-01-12", "12:01:90");
		assertTrue(ho.getQuery().equals("SELECT * FROM NOW"));
	}
	
	HistoryObject homain = new HistoryObject("SELECT * FROM NOW", "1998-01-12", "12:01:90");
	
	@Test public void History_Object_getQuery_Test() {
		assertTrue(homain.getQuery().equals("SELECT * FROM NOW"));
	}
	
	@Test public void History_Object_getDate_Test() {
		assertTrue(homain.getDate().equals("1998-01-12"));
	}
	
	@Test public void History_Object_getTime_Test() {
		assertTrue(homain.getTime().equals("12:01:90"));
	}
	
	@Test public void History_Object_setID_Test() {
		homain.setHistoryID("1");
		assertTrue(homain.getHistoryID().equals("1"));
	}
	
	@Test public void History_Test() {
		History h = new History("TEST", "TESTNAME");
		assertTrue(h.QUERY.equals("TEST"));
	}
	
	
	
	
	
	
	
	
}
