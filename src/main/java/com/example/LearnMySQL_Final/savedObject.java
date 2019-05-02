package com.example.LearnMySQL_Final;

public class savedObject {
		
		private final String SAVED_QUERY;
		private final String SAVED_QUERY_NAME;
		String STUDENT_NO;
		String SAVED_QUERY_ID;
		public savedObject(String query,String query_name,String studentNo) {
			this.SAVED_QUERY = query;
			this.STUDENT_NO = studentNo;
			this.SAVED_QUERY_NAME = query_name;
			
		}
		
		public String getQuery() {
			return SAVED_QUERY;
		}
		public String getQueryName() {
			return SAVED_QUERY_NAME;
		}

		public void setSavedQuery(String ID) {
			SAVED_QUERY_ID= ID;
		}
		
		public String getSavedQueryID() {
			return SAVED_QUERY_ID;
		}
		
}

	
	


