import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.*;

import com.example.LearnMySQL_Final.Person;
import com.example.LearnMySQL_Final.ServerManagementConnection;
import com.example.LearnMySQL_Final.StudentQueryHelper;
import com.example.LearnMySQL_Final.triplet;
import com.vaadin.ui.Grid;




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
	
	StudentQueryHelper sqh = new StudentQueryHelper(p, "127.0.0.1");
	//Test for select
	@Test public void The_Query_Box_Valid_Select_Query_Test() {
		triplet t = sqh.querySelectRun("SELECT * FROM TEST");
		System.out.println(t);
		try {
			assertTrue(t.queryOk && t.rs.next() == true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertFalse(false);
		}
	}
	
	@Test public void The_Query_Box_InValid_Select_Query_Test() {
		triplet t = sqh.querySelectRun("SELECT * FR TEST");
		System.out.println(t.queryOK);
		try {
			assertTrue(!t.queryOk && t.rs.next() == false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertFalse(true);
		}
	}
	
	
	// test for grid
	
	
	@Test public void The_Query_Box_Show_Table_Query_Test() {
		try {
			Grid g = sqh.queryShowTable();
			assertTrue(g != null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			assertFalse(true);
		}
		
	}
	
	
	
	
	
}
