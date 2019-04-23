package com.example.LearnMySQL_Final;

import java.sql.ResultSet;

import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;

public class TheQueryBox   extends VerticalLayout implements View {
	
	public TheQueryBox() {
		removeAllComponents();
		setHeight("100%");
		setWidth("100%");
		TextArea area = new TextArea("The Query Box");
		area.setWidth("100%");
		TextArea outputArea = new TextArea("Output Area");
		outputArea.setReadOnly(true);
		outputArea.setWidth("100%");
		final HorizontalLayout layout = new HorizontalLayout();
		
		
		Button execute = new Button("Execute");
		Button save = new Button("Save");
		
		FileUploader receiver = new FileUploader(area);
		Upload upload = new Upload("Please upload file here", null);
		upload.setReceiver(receiver);
		upload.addSucceededListener(receiver);
		upload.setImmediateMode(false);
		upload.setButtonCaption("Upload now");
		
		save.addClickListener(e -> {
			String Query = area.getValue();
			saveQuery sq = new saveQuery(Query);
			removeAllComponents();
			addComponent(new saveUI());
		});
		HorizontalLayout tableLayout = new HorizontalLayout();
		execute.addClickListener(e->{
			String querys = area.getValue();
			String[] query = querys.split(";");
			String output = "";
			tableLayout.removeAllComponents();
			for(int i =  0; i < query.length;i++) {
				String currQuery = query[i];
				if(!currQuery.toUpperCase().contains("USES")) {
					if(!currQuery.toUpperCase().contains("SELECT")) {
						User u = new User();
						Person p = u.person;
						StudentQueryHelper sqh = new StudentQueryHelper(p);
						String S = sqh.queryUpdateRun(currQuery);
						output = output + currQuery +":" + "\n";
						output = output + S + "\n";
					}
					else if(currQuery.toUpperCase().contains("SELECT")) {
						User u = new User();
						Person p = u.person;
						StudentQueryHelper sqh = new StudentQueryHelper(p);
						triplet t = sqh.querySelectRun(currQuery);
						if(!t.queryOk) {
							output = output + currQuery +":" + "\n";
							output = output + t.error + "\n";
						}
						else {
							ResultSet rs = t.rs;
							removeAllComponents();
							addComponent(new TableUI(rs));
						}
					}
					
					
					outputArea.setValue(output);
				}
			}
		});
		
		

		execute.setWidth("100%");
		save.setWidth("100%");
	
		layout.addComponents(execute,save);
		layout.setExpandRatio(execute, .5f);
		layout.addComponents(upload);
		layout.setExpandRatio(save, .5f);
		layout.setSizeFull();
		addComponents(area,layout,outputArea,tableLayout);
		/*setExpandRatio(area, .4f);
		setExpandRatio(layout, .1f);
		setExpandRatio(outputArea, .4f);*/
		setSizeFull();
		

	}
	
	
	public TheQueryBox(String query2) {
		removeAllComponents();
		setHeight("100%");
		setWidth("100%");
		TextArea area = new TextArea("The Query Box");
		area.setWidth("100%");
		area.setValue(query2);
		TextArea outputArea = new TextArea("Output Area");
		outputArea.setReadOnly(true);
		outputArea.setWidth("100%");
		final HorizontalLayout layout = new HorizontalLayout();
		
		
		Button execute = new Button("Execute");
		Button save = new Button("Save");
		
	
		save.addClickListener(e -> {
			String Query = area.getValue();
			saveQuery sq = new saveQuery(Query);
			removeAllComponents();
			addComponent(new saveUI());
		});
		
		execute.addClickListener(e->{
			String querys = area.getValue();
			String[] query = querys.split(";");
			String output = "";
			for(int i =  0; i < query.length;i++) {
				String currQuery = query[i];
				if(!currQuery.toUpperCase().contains("USES")) {
					if(!currQuery.toUpperCase().contains("SELECT")) {
						User u = new User();
						Person p = u.person;
						StudentQueryHelper sqh = new StudentQueryHelper(p);
						String S = sqh.queryUpdateRun(currQuery);
						output = output + currQuery +":" + "\n";
						output = output + S + "\n";
						
					}
					outputArea.setValue(output);
				}
			}
		});
		
		

		execute.setWidth("100%");
		save.setWidth("100%");
		layout.addComponents(execute,save);
		layout.setExpandRatio(execute, .5f);
		layout.setExpandRatio(save, .5f);
		layout.setSizeFull();
		addComponents(area,layout,outputArea);
		setExpandRatio(area, .4f);
		setExpandRatio(layout, .1f);
		setExpandRatio(outputArea, .4f);
		setSizeFull();
		
	}
	
	
	
}
