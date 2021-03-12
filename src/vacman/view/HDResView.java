package vacman.view;

import acm.graphics.GCompound;
import acm.graphics.GImage;
import vacman.model.Ghost;
import vacman.model.MapTiles;
import vacman.model.Screen;
import vacman.model.VacManModel;

public class HDResView extends GCompound implements VacManView<VacManModel> {
	private GImage background;
	private int res = 70;

	public HDResView() {
		background = new GImage("HDResBackground.png");
	}

	public void render(VacManModel model) {
		removeAll();
		// adding the background
		add(background);
		// creating the map depending on the model we use
		createMap(model);

		sendToFront();

	}

	@Override
	public void createMap(VacManModel model) {
		if (model.getCurrentScreen() == Screen.CURRENTLEVEL) {
			// grabbing the current map from the model
			MapTiles[][] map = model.getCurrentMap();

			// going through each element of that array that represents the map
			// r = rows
			// c = columns
			for (int r = 0; r < 14; r++) {
				for (int c = 0; c < 28; c++) {
					// if there is a wall at that current pos
					if (map[r][c] == MapTiles.WALL) {
						// adding the GCompund of the wall at the position c, r and multiplied with 50
						// because every graphic obj in this view is 50*50
						add(new GImage("HDResWall.png"), c * res, r * res);
						// same thing for the coin and heart
					} else if (map[r][c] == MapTiles.COIN) {
						add(new GImage("HDResCoin.png"), c * res, r * res);
					} else if (map[r][c] == MapTiles.HEART) {
						add(new GImage("HDResHeart.png"), c * res, r * res);
					}
				}
			}
			// adding the Vacman in his Corona skin at his position on the map
			add(new GImage("HDResVacman.png"), model.getVacManPos().getX() * res, model.getVacManPos().getY() * res);
			// adding every ghost with its color
			for (Ghost ghost : model.getGhosts()) {
				GImage neu = new GImage("HDResGhost.png");
				add(neu, ghost.getPos().getX() * res, ghost.getPos().getY() * res);
			}

		} else if (model.getCurrentScreen() == Screen.GAMEOVER) {
			add(new GImage("HDResBackground.png"));
		} else if (model.getCurrentScreen() == Screen.WIN) {
			add(new GImage("HDResBackground.png"));
		} else if (model.getCurrentScreen() == Screen.START) {
			add(new GImage("HDResBackground.png"));
		} else if (model.getCurrentScreen() == Screen.SELECTION) {
			add(new GImage("HDResLevelSelection.png"));
		} else if (model.getCurrentScreen() == Screen.SETTINGS) {
			if (model.getLightHouseOn()) {
				add(new GImage("HDResBackground.png"));
			} else {
				add(new GImage("HDResBackground.png"));

			}
		}
	}

	@Override
	public int getRes() {
		return res;
	}

}
