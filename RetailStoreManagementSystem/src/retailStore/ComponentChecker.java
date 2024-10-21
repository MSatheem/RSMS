package retailStore;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class ComponentChecker {
	
	
	public static  boolean isFieldsEmpty(JPanel panel) { //checking whether components is empty
		Component[] component = panel.getComponents();
		for(Component comp : component) {
			JTextField field = (JTextField) comp;
			if(field.getText().isEmpty()) {
				return true;
			}
		}
		return false;
		
	}
}
