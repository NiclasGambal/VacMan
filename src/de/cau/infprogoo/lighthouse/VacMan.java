package de.cau.infprogoo.lighthouse;

import acm.graphics.GObject;
import acm.program.GraphicsProgram;
import vacman.controller.VacManController;
import vacman.model.VacManModel;

/**
 * The main game class.
 */
public class VacMan extends GraphicsProgram {
	/** The period time of the game loop. */
	private static final int GAME_LOOP = 200;

	// The init method of the game.
	public void init() {
		// Sets the canvas size.
		setSize(1415, 740);
		// Creates a Timer object to create a game loop.
		Timer timer = new Timer(GAME_LOOP);
		// Creates a model, with specified start map and view.
		VacManModel model = new VacManModel();
		// Creates the controller and adds a KeyListener to it.
		VacManController controller = new VacManController(model);
		addKeyListeners(controller);
		addMouseListeners(controller);
		// Adds the view to the canvas.
		for (int i = 1; i < model.getViews().size(); i++) {
			add((GObject) model.getViews().get(i));
		}

		// The game loop, which is repeated endlessly.
		while (true) {
			model.update();
			timer.pause();
		}

	}

	public static void main(String[] args) {
		new VacMan().start();

	}

}
