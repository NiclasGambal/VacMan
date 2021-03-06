package vacman.view;

import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GRect;
import vacman.model.VacManModel;

/**
 * this class represents the view of the mvc model and contains different views.
 * 
 *
 */
public abstract class VacManView extends GCompound {

	/** The background of the game. */
	private GRect background;

	/**
	 * the constructor of the view.
	 * 
	 * @param modus the resolution of the view.
	 */
	public VacManView(int modus) {
		int columns = 28;
		int rows = 14;
		background = new GRect(columns * modus, rows * modus);
		background.setFilled(true);
		background.setColor(new Color(255, 99, 71));

	}

	/**
	 * the render method updates the view.
	 * 
	 * @param model the model on which the map/view is created.
	 */
	public void render(VacManModel model) {
		// removing all from the screen
		removeAll();
		// adding the background
		add(background);
		// creating the map depending on the model we use
		createMap(model);
	}

	/**
	 * the createmap method as abstract method which is implemented in different
	 * views differently.
	 * 
	 * @param model the model of the game/map.
	 */
	public abstract void createMap(VacManModel model);
}
