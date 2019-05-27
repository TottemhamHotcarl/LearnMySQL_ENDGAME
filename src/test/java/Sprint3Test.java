import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.*;

import com.example.LearnMySQL_Final.History;
import com.example.LearnMySQL_Final.HistoryObject;
import com.example.LearnMySQL_Final.Person;
import com.example.LearnMySQL_Final.QueryObject;
import com.example.LearnMySQL_Final.ServerManagementConnection;
import com.example.LearnMySQL_Final.StudentQueryHelper;
import com.example.LearnMySQL_Final.saveQuery;
import com.example.LearnMySQL_Final.savedObject;
import com.example.LearnMySQL_Final.triplet;
import com.vaadin.ui.Grid;




public class Sprint3Test {
	
	ServerManagementConnection smc = new ServerManagementConnection("127.0.0.1");
	Person p = new Person("Carl Ginster","Carl","Ginster","1606558@students.wits.ac.za","1606558","1");
	
	
	//Saved Query
	@Test public void Saved_Tab_Get_SAVED_QUERY_Valid_Test() {
		triplet t = smc.getStudentSavedQuery(p);
		assertTrue(t.queryOk);
	}
	
	@Test public void Saved_Tab_Get_Saved_Query_With_Search_Valid_Test() {
		triplet t = smc.getStudentSavedQueryWithSearch(p, "*");
		assertTrue(t.queryOk);
	}
	
	
	
	@Test public void Saved_Tab_Add_Saved_Query_Valid_Test() {
		boolean t = smc.addStudentSavedQuery(p, "TEST_NAME","Test");
		assertTrue(t);
	}
	
	@Test public void Saved_Tab_Saved_Query_Delete_Test() {
		boolean t = smc.deleteSaved_Query("1");
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
		triplet t = sqh.querySelectRun("SELECT * FM TEST");
		System.out.println(t.queryOk);
		assertTrue(!t.queryOk);
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
	
	
	//Saved Object Test
	
		@Test public void savedObject_Const_Test() {
			savedObject ho = new savedObject("SELECT * FROM NOW", "SPECIAL SELECT", "1606558");
			assertTrue(ho.getQuery().equals("SELECT * FROM NOW"));
		}
		
		savedObject homain = new savedObject("SELECT * FROM NOW", "SPECIAL SELECT", "1606558");
		
		@Test public void savedObject_getQuery_Test() {
			assertTrue(homain.getQuery().equals("SELECT * FROM NOW"));
		}
		
		@Test public void savedObject_getQueryName_Test() {
			saveQuery so = new saveQuery();
			assertTrue(homain.getQueryName().equals("SPECIAL SELECT"));
		}
		
		
		@Test public void savedObject_setID_Test() {
			homain.setSavedQuery("1");
			assertTrue(homain.getSavedQueryID().equals("1"));
		}
	
	
		// test for savedQuery
		
		@Test public void savedQuery_const_Test() {
			saveQuery sq = new saveQuery("TEST");
			assertTrue(sq.returnQuery().equals("TEST"));
		}
		
		//test for QueryObject
		
		@Test public void QueryObject_Const_Test() {
			QueryObject ho = new QueryObject("SELECT * FROM NOW", "1998-01-12", "12:01:90","23");
			assertTrue(ho.getQuery().equals("SELECT * FROM NOW"));
		}
		
		QueryObject homain2 = new QueryObject("SELECT * FROM NOW", "1998-01-12", "12:01:90","23");
		
		@Test public void QueryObject_getQuery_Test() {
			assertTrue(homain2.getQuery().equals("SELECT * FROM NOW"));
		}
		
		@Test public void QueryObject_getDate_Test() {
			assertTrue(homain2.getDate().equals("1998-01-12"));
		}
		
		@Test public void QueryObject_getTime_Test() {
			assertTrue(homain2.getTime().equals("12:01:90"));
		}
		
		@Test public void QueryObject_setID_Test() {
			assertTrue(homain2.getQueryName().equals("23"));
		}
		
		
		
	
}
