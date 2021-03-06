package vacman.view;

import java.io.IOException;

import de.cau.infprogoo.lighthouse.LighthouseDisplay;
import vacman.model.Ghost;
import vacman.model.MapTiles;
import vacman.model.VacManModel;

/**
 * the Lighthouse resolution so we can see and play the game on a really big
 * screen. extending VacManView.
 *
 */
public class LightHouseView implements VacManView<VacManModel> {

	/**
	 * calling the constructor of the superclass with modus 1.
	 */
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
		// saving the current map as a local variable because the map is needed for
		// further operation
		MapTiles[][] map = model.getCurrentMap();
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
					data[index] = 0;
					data[index + 1] = 0;
					data[index + 2] = (byte) 255;
					// if theres a coin we set the following three bytes to the rgb value of the
					// coin
				} else if (map[r][c] == MapTiles.COIN) {
					data[index] = (byte) 238;
					data[index + 1] = (byte) 180;
					data[index + 2] = (byte) 34;
					// for hearts we set the rgb value to red, obviously
				} else if (map[r][c] == MapTiles.HEART) {
					data[index] = (byte) 255;
					data[index + 1] = 0;
					data[index + 2] = 0;
				}
				// increasing the index by 3 because at every index step are three bytes saved
				index += 3;
			}

		}
		// looks complicated, but its not.
		// we look at the current position of pacman and calculate the index where he
		// would be in the byte array for the lighthouse
		// then changing the rgb values at the three positions to the color of pacman
		data[(int) (model.getVacManPos().getY() * 28 * 3 + model.getVacManPos().getX() * 3)] = (byte) 255;
		data[(int) (model.getVacManPos().getY() * 28 * 3 + model.getVacManPos().getX() * 3) + 1] = (byte) 255;
		data[(int) (model.getVacManPos().getY() * 28 * 3 + model.getVacManPos().getX() * 3) + 2] = (byte) 255;
		// same way for every ghost
		for (Ghost ghost : model.getGhosts()) {
			data[(int) (ghost.getPos().getY() * 28 * 3 + ghost.getPos().getX() * 3)] = (byte) 255;
			data[(int) (ghost.getPos().getY() * 28 * 3 + ghost.getPos().getX() * 3) + 1] = (byte) 0;
			data[(int) (ghost.getPos().getY() * 28 * 3 + ghost.getPos().getX() * 3) + 2] = (byte) 255;

		}
		// declaring the display
		LighthouseDisplay display = null;

		// Try connecting to the display
		try {
			display = LighthouseDisplay.getDisplay();
			display.setUsername("ng217779");
			display.setToken("API-TOK_+ogs-IBOm-SjNs-3AOg-VG3a");
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

}