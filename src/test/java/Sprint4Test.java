import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

import com.example.LearnMySQL_Final.DescObject;
import com.example.LearnMySQL_Final.GroupObject;
import com.example.LearnMySQL_Final.GroupServerManagementConnection;
import com.example.LearnMySQL_Final.Person;

public class Sprint4Test {
	
	DescObject DoTest = new DescObject("STUDENT_NO", "varchar(10)", "NOT NULL", "PRI", "a", "auto_increment");
	Random rm = new Random();
	
	//Test for DescObject

	
	@Test public void DescObject_getField_Test() {
		
		
		assertTrue(DoTest.getField().equals("STUDENT_NO"));
	}
	
	@Test public void DescObject_getType_Test() {
		assertTrue(DoTest.gettype().equals("varchar(10)"));
	}
	
	@Test public void DescObject_getNull_Test() {
		assertTrue(DoTest.getnull().equals("NOT NULL"));
	}
	
	@Test public void DescObject_getKey_Test() {
		assertTrue(DoTest.getkey().equals("PRI"));
	}
	
	@Test public void DescObject_getDefault_Test() {
		assertTrue(DoTest.getdefault().equals("a"));
	}
	
	@Test public void DescObject_getExtra_Test() {
		assertTrue(DoTest.getextra().equals("auto_increment"));
	}
	
	
	
	@Test public void DescObject_Const_Test() {
		
		DescObject Do = new DescObject("STUDENT_NO", "varchar(10)", "NOT NULL", "PRI", "", "auto_increment");
		assertTrue(DoTest.gettype().equals(Do.gettype()));
	}
	
	
	//test for GroupObject
	
	GroupObject GoTest = new GroupObject("34", "1606558", "Edoc", "d1606558");
			
	@Test public void GroupObject_getID_Test() {
		assertTrue(GoTest.getID().equals("34"));
	}	
	@Test public void GroupObject_getAdmin_Test() {
		assertTrue(GoTest.getAdmin().equals("1606558"));
	}
	@Test public void GroupObject_getName_Test() {
		assertTrue(GoTest.getName().equals("Edoc"));
	}
	@Test public void GroupObject_getDatabase_Test() {
		assertTrue(GoTest.getDatabase().equals("d1606558"));
	}
	
	
	@Test public void GroupObject_Const_Test() {
		
		GroupObject Go = new GroupObject("34", "1606558", "Edoc", "d1606558");
		assertTrue(GoTest.getID().equals(Go.getID()));
	}
	
	// Test for Group Server Management 
	
	GroupServerManagementConnection gsmc = new GroupServerManagementConnection("127.0.0.1");
	Person p = new Person("Carl Ginster","Carl","Ginster","1606558@students.wits.ac.za","1606558","1");
	Person ptrue = new Person("Carl Ginster","Carl","Ginster","1606558@students.wits.ac.za","2000000","1");
	Person pfalse = new Person("Carl Ginster","Carl","Ginster","1606558@students.wits.ac.za","1606559","1");
	
	@Test public void GSMC_alreadyCreatedGroup_True() {
		assertTrue(gsmc.alreadyCreatedGroup(p).queryOk);
	}
	
	
	
	@Test public void GSMC_GetMember_True() {
		assertTrue(gsmc.getGroupMembers("27").queryOk);
	}
	
	@Test public void GSMC_GetMember_False() {
		assertFalse(gsmc.getGroupMembers("28").queryOk);
	}
	
	@Test public void GSMC_addGroup_True() {
		int t = rm.nextInt();
		assertTrue(gsmc.addGroup(new Person("Carl Ginster","Carl","Ginster","1606558@students.wits.ac.za",Integer.toString(t),"1"), "test Group"+t));
	}
	
	
	
	
	
}
