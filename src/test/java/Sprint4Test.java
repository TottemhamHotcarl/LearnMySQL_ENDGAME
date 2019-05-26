import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.example.LearnMySQL_Final.DescObject;

public class Sprint4Test {
	
	DescObject DoTest = new DescObject("STUDENT_NO", "varchar(10)", "NOT NULL", "PRI", "", "auto_increment");
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
		assertTrue(DoTest.getdefault().equals(""));
	}
	
	@Test public void DescObject_getExtra_Test() {
		assertTrue(DoTest.getextra().equals("auto_increment"));
	}
	
	
	
	@Test public void DescObject_Const_Test() {
		
		DescObject Do = new DescObject("STUDENT_NO", "varchar(10)", "NOT NULL", "PRI", "", "auto_increment");
		assertTrue(DoTest.gettype().equals(Do.gettype()));
	}
	
	
}
