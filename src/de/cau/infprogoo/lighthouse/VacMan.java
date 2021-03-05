package de.cau.infprogoo.lighthouse;

import acm.program.GraphicsProgram;

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
		// Invoke a view for from the VacManView class.
		CoronaRes low = new CoronaRes();
		// Creates a model, with specified start map and view.
		VacManModel model = new VacManModel(1, low);
		// Creates the controller and adds a KeyListener to it.
		VacManController controller = new VacManController(model);
		addKeyListeners(controller);
		// Adds the view to the canvas.
		add(model.getVacManView());
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

/**
 * Enum that keeps track with the Direction something is moving.
 */
enum Direction {
	RIGHT, LEFT, UP, DOWN
}

/**
 * Enum that keeps track with the tile set of the maps.
 */
enum MapTiles {
	HEART, COIN, WALL, VOID
}