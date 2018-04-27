package Memory;

import Memory.Color;
import Memory.Form;

public class DrawableObjects {

	private Color color;

	private Form form;



	public DrawableObjects(final Color pColor, final Form pForm) {
		color = pColor;
		form = pForm;
	}
	

	public Color getColor() {
		return color;
	}

	public Form getForm() {
		return form;
	}

}
