package com.example.LearnMySQL_Final;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order.Direction;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.TextField;

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
             
        }
        rows = new ArrayList<>();
        while(rs.next()) {
        	 HashMap<String, String> fakeBean = new HashMap<>();
        	 for(int i = 1; i <= colCount;i++) {
        		 fakeBean.put(list.get(i-1), rs.getString(list.get(i-1)));
        	 }
        
             rows.add(fakeBean);
        }
	
        grid3 = new Grid<>();
        grid3.setItems(rows);

        // Add the columns based on the first row
        if(rows.size() != 0) {
	        HashMap<String, String> q = rows.get(0);
	        for (Map.Entry<String, String> entry : q.entrySet()) {
	            grid3.addColumn(h -> h.get(entry.getKey())).setCaption(entry.getKey());
	        }
	        return grid3;
        }
        return null;
	}
	

	
	public Grid<DescObject> ResultSetToGridForDesc(ResultSet rs) throws SQLException {
		ArrayList<DescObject> ls = new ArrayList<DescObject>();
		
		try {
			while(rs.next()) {
				DescObject DescObject = new DescObject(rs.getString("Field"), rs.getString("type"), rs.getString("null") , rs.getString("key"),rs.getString("Default"), rs.getString("extra"));
				ls.add(DescObject);
	
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		Grid<DescObject> grid = new Grid<>();
		 grid.setWidth("100%");
		 grid.setHeightUndefined();
		 
	        
	        grid.addColumn(DescObject::getField).setCaption("Field");
	        grid.addColumn(DescObject::gettype).setCaption("Type");
			 grid.addColumn(DescObject::getnull).setCaption("Null");
			 grid.addColumn(DescObject::getkey).setCaption("Key");
			 grid.addColumn(DescObject::getdefault).setCaption("Default");
			 grid.addColumn(DescObject::getextra).setCaption("Extra");
			 
			 grid.setItems(ls);
			
			 
			 return (grid);
		
	}
	
	
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Grid<HistoryObject> ResultSetToHIstoryGrid(ResultSet rs2){
		ArrayList<HistoryObject> ls = new ArrayList<HistoryObject>();
		try {
			while(rs2.next()) {
				HistoryObject ho = new HistoryObject(rs2.getString("HISTORY_QUERY"), rs2.getString("HISTORY_DATE"), rs2.getString("HISTORY_TIME"));
				ho.setHistoryID(rs2.getString("HISTORY_ID"));
				ls.add(ho);
				
				//System.out.println(rs2.getString("HISTORY_QUERY")+ rs2.getString("HISTORY_DATE")+ rs2.getString("HISTORY_TIME"));
			
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		 Grid<HistoryObject> grid = new Grid<>();
		 grid.setWidth("100%");
		 grid.setHeightUndefined();
		 
	        grid.setCaption("HISTORY TABLE");
	        grid.addColumn(HistoryObject::getQuery).setCaption("HISTORY_QUERY");
	        grid.addColumn(HistoryObject::getDate).setCaption("HISTORY_DATE").setId("Date");
			 grid.addColumn(HistoryObject::getTime).setCaption("HISTORY_TIME").setId("Time");;
			 grid.sort("Date", SortDirection.DESCENDING);
			 //grid.sort("Time",SortDirection.DESCENDING);
			 grid.setItems(ls);
			
			 
			 return (grid);
	}
	
	
	
	
	
	
	
	
	public TextField GetOutputHeading(String currQuery) {
		TextField outputtemp = new TextField();
		outputtemp.setReadOnly(true);
		outputtemp.setWidth("100%");
		outputtemp.setHeightUndefined();
		outputtemp.setValue(currQuery);
		return outputtemp;
	}
	
	public Grid<savedObject> ResultSetToSavedGrid(ResultSet rs2){
		ArrayList<savedObject> ls = new ArrayList<savedObject>();
		try {
			while(rs2.next()) {
				savedObject ho = new savedObject(rs2.getString("SAVED_QUERY"), rs2.getString("SAVED_QUERY_NAME"), rs2.getString("STUDENT_NO"));

				ho.setSavedQuery(rs2.getString("SAVED_QUERY_ID"));
				ls.add(ho);
				
				//System.out.println(rs2.getString("HISTORY_QUERY")+ rs2.getString("HISTORY_DATE")+ rs2.getString("HISTORY_TIME"));
			
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		 Grid<savedObject> grid = new Grid<>();
		 grid.setWidth("100%");
		 grid.setHeightUndefined();
		 
	        grid.setCaption("SAVED TABLE");
	        grid.addColumn(savedObject::getQueryName).setCaption("SAVED_QUERY_NAME");
	        grid.addColumn(savedObject::getQuery).setCaption("SAVED_QUERY");
	        
			 grid.setItems(ls);
			 return (grid);
	}
	
	
}
