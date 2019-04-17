package com.example.LearnMySQL_Final;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vaadin.ui.Grid;

public class LayoutHelper {

	public Grid ResultSetToGrid(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		Grid<HashMap<String, String>> grid3 = null;
		List<HashMap<String, String>> rows = new ArrayList<>();
        //getting number of columns in 'rs'
         
         int colCount= rsmd.getColumnCount();
         
        System.out.println("Number Of Columns : "+colCount);
         
        System.out.println("column Details :");
         
        ArrayList<String> list = new ArrayList<String>();
        
        for (int i = 1; i <= colCount; i++)
        {
            //getting column name of index 'i'
             
            String colName = rsmd.getColumnName(i);
            list.add(colName);
             
            //getting column's data type of index 'i'
             
            String colType = rsmd.getColumnTypeName(i);
             
            System.out.println(colName+" is of type "+colType);
        }
        rows = new ArrayList<>();
        while(rs.next()) {
        	System.out.println((rs.toString()));
        	 HashMap<String, String> fakeBean = new HashMap<>();
        	 for(int i = 1; i <= colCount;i++) {
        		 fakeBean.put(list.get(i-1), rs.getString(list.get(i-1)));
        	 }
        
             rows.add(fakeBean);
        }
	
        grid3 = new Grid<>();
        grid3.setItems(rows);

        // Add the columns based on the first row
        HashMap<String, String> q = rows.get(0);
        for (Map.Entry<String, String> entry : q.entrySet()) {
            grid3.addColumn(h -> h.get(entry.getKey())).setCaption(entry.getKey());
        }
        return grid3;
	}
	
	
}
