package vacman.view;

import java.io.IOException;

import de.cau.infprogoo.lighthouse.LighthouseDisplay;
import vacman.model.Ghost;
import vacman.model.MapTiles;
import vacman.model.Screen;
import vacman.model.VacManModel;

/**
 * the Lighthouse resolution so we can see and play the game on a really big
 * screen. extending VacManView.
 *
 */
public class LightHouseView implements VacManView<VacManModel> {

	private int res = 1;

	/** Constructor. */
	public LightHouseView() {

	}

	/**
	 * the render method updates the view.
	 * 
	 * @param model the model on which the map/view is created.
	 */
	public void render(VacManModel model) {
		createMap(model);
	}

	/**
	 * the create Map method implemented in this view.
	 * 
	 * @param model the model which represents the game.
	 */
	public void createMap(VacManModel model) {

		MapTiles[][] map = new MapTiles[14][28];
		if (model.getCurrentScreen() == Screen.CURRENTLEVEL) {
			map = model.getCurrentMap();
		} else if (model.getCurrentScreen() == Screen.GAMEOVER) {
			int[][] gameoverscreen = {
					// row 0
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					// row 1
					{ 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
					// row 2
					{ 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					// row 3
					{ 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					// row 4
					{ 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					// row 5
					{ 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
					// row 6
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					// row 7
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					// row 8
					{ 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
					// row 9
					{ 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0 },
					// row 10
					{ 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
					// row 11
					{ 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0 },
					// row 12
					{ 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0 },
					// row 13
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
			// This map also will be converted.
			map = VacManModel.intToMapTiles(gameoverscreen);
		} else if (model.getCurrentScreen() == Screen.WIN) {
			int[][] winScreen = { // row 0
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					// row 1
					{ 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 2, 2, 2, 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 2
					{ 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 2, 0, 0, 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 3
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 4
					{ 0, 3, 3, 0, 3, 3, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 5
					{ 3, 3, 3, 0, 3, 3, 3, 0, 0, 2, 0, 0, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0 },
					// row 6
					{ 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					// row 7
					{ 0, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					// row 8
					{ 0, 0, 3, 3, 3, 0, 0, 2, 0, 0, 0, 2, 0, 2, 0, 2, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 9
					{ 0, 0, 0, 3, 0, 0, 0, 2, 0, 0, 0, 2, 0, 2, 0, 2, 2, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 10
					{ 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 11
					{ 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
					// row 12
					{ 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 2, 0, 2, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 13
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
			map = VacManModel.intToMapTiles(winScreen);
		} else if (model.getCurrentScreen() == Screen.START) {
			// Missing.

			int[][] startScreen = { // row 0
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					// row 1
					{ 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 2, 2, 2, 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 2
					{ 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 2, 0, 0, 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 3
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 4
					{ 0, 3, 3, 0, 3, 3, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 5
					{ 3, 3, 3, 0, 3, 3, 3, 0, 0, 2, 0, 0, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0 },
					// row 6
					{ 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					// row 7
					{ 0, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					// row 8
					{ 0, 0, 3, 3, 3, 0, 0, 2, 0, 0, 0, 2, 0, 2, 0, 2, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 9
					{ 0, 0, 0, 3, 0, 0, 0, 2, 0, 0, 0, 2, 0, 2, 0, 2, 2, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 10
					{ 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 11
					{ 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
					// row 12
					{ 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 2, 0, 2, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 13
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
			map = VacManModel.intToMapTiles(startScreen);
		} else if (model.getCurrentScreen() == Screen.SELECTION) {
			// Missing.

			int[][] selectionScreen = { // row 0
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					// row 1
					{ 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 2, 2, 2, 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 2
					{ 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 2, 0, 0, 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 3
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 4
					{ 0, 3, 3, 0, 3, 3, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 5
					{ 3, 3, 3, 0, 3, 3, 3, 0, 0, 2, 0, 0, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0 },
					// row 6
					{ 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					// row 7
					{ 0, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					// row 8
					{ 0, 0, 3, 3, 3, 0, 0, 2, 0, 0, 0, 2, 0, 2, 0, 2, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 9
					{ 0, 0, 0, 3, 0, 0, 0, 2, 0, 0, 0, 2, 0, 2, 0, 2, 2, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 10
					{ 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 11
					{ 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
					// row 12
					{ 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 2, 0, 2, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 13
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
			map = VacManModel.intToMapTiles(selectionScreen);
		} else if (model.getCurrentScreen() == Screen.SETTINGS) {
			// Missing.

			int[][] settingsScreen = { // row 0
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					// row 1
					{ 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 2, 2, 2, 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 2
					{ 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 2, 0, 0, 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 3
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 4
					{ 0, 3, 3, 0, 3, 3, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 2, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 5
					{ 3, 3, 3, 0, 3, 3, 3, 0, 0, 2, 0, 0, 0, 2, 2, 2, 2, 0, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0 },
					// row 6
					{ 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					// row 7
					{ 0, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
					// row 8
					{ 0, 0, 3, 3, 3, 0, 0, 2, 0, 0, 0, 2, 0, 2, 0, 2, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 9
					{ 0, 0, 0, 3, 0, 0, 0, 2, 0, 0, 0, 2, 0, 2, 0, 2, 2, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 10
					{ 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 11
					{ 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0 },
					// row 12
					{ 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 2, 0, 2, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0 },
					// row 13
					{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
			map = VacManModel.intToMapTiles(settingsScreen);
		}
		// saving the current map as a local variable because the map is needed for
		// further operation

		// creating the byte array we want to send to the light House
		byte[] data = new byte[14 * 28 * 3];
		// declaring the index which represents the index-position of the lighthouse
		// array
		int index = 0;
		// going through every element of the maptiles array
		for (int r = 0; r < 14; r++) {
			for (int c = 0; c < 28; c++) {
				// if there is a wall the next three bytes are set to the rgb value we want to
				// use for the wall
				if (map[r][c] == MapTiles.WALL) {
					data[index] = (byte) 198;
					data[index + 1] = (byte) 226;
					data[index + 2] = (byte) 255;
					// if theres a coin we set the following three bytes to the rgb value of the
					// coin
				} else if (map[r][c] == MapTiles.COIN) {
					data[index] = (byte) 255;
					data[index + 1] = (byte) 215;
					data[index + 2] = (byte) 0;
					// for hearts we set the rgb value to red, obviously
				} else if (map[r][c] == MapTiles.HEART) {
					data[index] = (byte) 255;
					data[index + 1] = 0;
					data[index + 2] = 0;
				} else if (map[r][c] == MapTiles.VOID) {
					data[index] = (byte) 0;
					data[index + 1] = (byte) 0;
					data[index + 2] = (byte) 0;
				}
				// increasing the index by 3 because at every index step are three bytes saved
				index += 3;
			}

		}
		// looks complicated, but its not.
		// we look at the current position of pacman and calculate the index where he
		// would be in the byte array for the lighthouse
		// then changing the rgb values at the three positions to the color of pacman
		if (model.getCurrentScreen() == Screen.CURRENTLEVEL) {
			data[(int) (model.getVacManPos().getY() * 28 * 3 + model.getVacManPos().getX() * 3)] = (byte) 255;
			data[(int) (model.getVacManPos().getY() * 28 * 3 + model.getVacManPos().getX() * 3) + 1] = (byte) 127;
			data[(int) (model.getVacManPos().getY() * 28 * 3 + model.getVacManPos().getX() * 3) + 2] = (byte) 36;
			// same way for every ghost
			for (Ghost ghost : model.getGhosts()) {
				data[(int) (ghost.getPos().getY() * 28 * 3 + ghost.getPos().getX() * 3)] = (byte) ghost.getColor()
						.getRed();
				data[(int) (ghost.getPos().getY() * 28 * 3 + ghost.getPos().getX() * 3) + 1] = (byte) ghost.getColor()
						.getGreen();
				data[(int) (ghost.getPos().getY() * 28 * 3 + ghost.getPos().getX() * 3) + 2] = (byte) ghost.getColor()
						.getBlue();

			}
		}
		// declaring the display
		LighthouseDisplay display = null;

		// Try connecting to the display
		try {
			display = LighthouseDisplay.getDisplay();
			display.setUsername("ng217779");
			display.setToken("API-TOK_8zkq-b5oL-ojmS-LtDQ-zev1");
		} catch (Exception e) {
			System.out.println("Connection failed: " + e.getMessage());
			e.printStackTrace();
		}

		// Send data to the display
		try {
			// This array contains for every window (14 rows, 28 columns) three
			// bytes that define the red, green, and blue component of the color
			// to be shown in that window. See documentation of LighthouseDisplay's
			// send(...) method.

			// sending the data array to the display
			display.sendImage(data);
		} catch (IOException e) {
			System.out.println("Connection failed: " + e.getMessage());
			e.printStackTrace();
		}

	}

	public int getRes() {
		return res;
	}

}