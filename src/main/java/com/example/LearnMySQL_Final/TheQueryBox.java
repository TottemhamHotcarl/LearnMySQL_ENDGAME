package com.example.LearnMySQL_Final;

import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class TheQueryBox   extends VerticalLayout implements View {
	
	public TheQueryBox() {
		removeAllComponents();
		setHeight("100%");
		setWidth("100%");
		TextArea area = new TextArea("The Query Box");
		area.setWidth("100%");
		
		final HorizontalLayout layout = new HorizontalLayout();
		
		
		Button execute = new Button("Execute");
		Button save = new Button("Save");
		
		
		save.addClickListener(e -> {
			String Query = area.getValue();
			saveQuery sq = new saveQuery(Query);
			removeAllComponents();
			addComponent(new saveUI());
		});
		

	    Label outcome = new Label("");
		outcome.setHeight("100%");
		outcome.setWidth("100%");
		
		layout.addComponents(execute,save);
		layout.setComponentAlignment(save, Alignment.MIDDLE_RIGHT);
		setSpacing(false);
		
		addComponents(area,layout,outcome);
		
			
		

	}
	
	
	public TheQueryBox(String query) {
		removeAllComponents();
		setHeight("100%");
		setWidth("100%");
		TextArea area = new TextArea("The Query Box");
		area.setWidth("100%");
		area.setValue(query);
		
		final HorizontalLayout layout = new HorizontalLayout();
		
		
		Button execute = new Button("Execute");
		Button save = new Button("Save");
		
		
		save.addClickListener(e -> {
			String Query = area.getValue();
			saveQuery sq = new saveQuery(Query);
			removeAllComponents();
			addComponent(new saveUI());
		});
		
		Label outcome = new Label("-----------------------------------------------------------------aSGDJRNDFJGNFSDKJGBKSFGBKSDGBADSLG KJSERTGBJSDBGKJADNGKDABGKJADBFKJADHFA");
		
		layout.addComponents(execute,save);
			layout.setComponentAlignment(save, Alignment.MIDDLE_RIGHT);
			setSpacing(false);
		addComponents(area,layout,outcome);
		
	}
	
	
	
}
