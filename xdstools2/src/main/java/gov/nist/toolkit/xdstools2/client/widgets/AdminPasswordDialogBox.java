package gov.nist.toolkit.xdstools2.client.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.*;
import gov.nist.toolkit.xdstools2.client.PasswordManagement;

public class AdminPasswordDialogBox extends DialogBox {
	PasswordTextBox passBox;
	
	public AdminPasswordDialogBox(FlowPanel parent) {
		
		setPopupPosition(200, 200);
		
		HorizontalPanel topPanel = new HorizontalPanel();
		
		Label instructions = new Label("Admin Password: ");
		topPanel.add(instructions);
		
		passBox = new PasswordTextBox();
		passBox.addKeyPressHandler(new KeyPressHandler() {

			public void onKeyPress(KeyPressEvent event) {
				if (event.getCharCode() == '\r')
					new OkButtonClicked().onClick(null);
			}
			
		});
		topPanel.add(passBox);
		
		Button okButton = new Button("Ok");
		okButton.addClickHandler(new OkButtonClicked());
						
		topPanel.add(okButton);
		
		setWidget(topPanel);
		
		parent.add(this);
		
		show();
		
		passBox.setFocus(true);
	}
	
	class OkButtonClicked implements ClickHandler {

		public void onClick(ClickEvent unused) {
			String password = passBox.getText();
			AdminPasswordDialogBox.this.hide();
			PasswordManagement.comparePassword(password);
			
			if (PasswordManagement.isSignedIn) {
				PasswordManagement.callSignInCallbacks();
			}
			else
				new PopupMessage("Sorry");
		}
		
	}
	
}
