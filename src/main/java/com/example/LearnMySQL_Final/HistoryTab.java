package com.example.LearnMySQL_Final;



import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class HistoryTab  extends VerticalLayout implements View {

	public HistoryTab() {
		final HorizontalLayout hl = new HorizontalLayout();
		TextField searchBox = new TextField();
		Button search = new Button("search");
		hl.addComponents(searchBox,search);
		addComponent(hl);
		
		
	}
	
}
