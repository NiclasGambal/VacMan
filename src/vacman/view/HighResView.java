package vacman.view;

import java.awt.Color;

import acm.graphics.GArc;
import acm.graphics.GCompound;
import acm.graphics.GOval;
import acm.graphics.GPolygon;
import acm.graphics.GRect;
import vacman.model.Ghost;
import vacman.model.MapTiles;
import vacman.model.VacManModel;

/**
 * the beautiful pacman version that has nothing to do with corona. just pacman
 * with high graphics
 *
 */
public class HighResView extends GCompound implements VacManView<VacManModel> {
	/** The background of the game. */
	private GRect background;

	/**
	 * the constructor of the view.
	 * 
	 * @param modus the resolution of the view.
	 */
	public HighResView() {
		int columns = 28;
		int rows = 14;
		background = new GRect(columns * 50, rows * 50);
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
	 * implementing the create map method for this view.
	 * 
	 * @param model the model of the game.
	 */
	public void createMap(VacManModel model) {
		// saving the current map as local variable
		MapTiles[][] map = model.getCurrentMap();

		// going through each element of the map array
		// r = rows
		// c = columns
		for (int r = 0; r < 14; r++) {
			for (int c = 0; c < 28; c++) {
				// if there is a certain element represented in the map array it is added to the
				// canvas in its array positin *50
				if (map[r][c] == MapTiles.WALL) {
					add(new WallHighRes(), c * 50, r * 50);
				} else if (map[r][c] == MapTiles.COIN) {
					add(new CoinsHighRes(), c * 50, r * 50);
				} else if (map[r][c] == MapTiles.HEART) {
					add(new HeartsHighRes(), c * 50, r * 50);
				}
			}
		}
		// adding the vacman with nice skin at this place
		add(new VacManHighRes(), model.getVacManPos().getX() * 50, model.getVacManPos().getY() * 50);
		// adding every ghost with the high resolution skin
		for (Ghost ghost : model.getGhosts()) {
			add(new GhostHighRes(ghost.getColor()), ghost.getPos().getX() * 50, ghost.getPos().getY() * 50);
		}

	}
}

/** The graphic for the high resolution coins. */
class CoinsHighRes extends GCompound {
	/** the big circle of the coin. */
	private GOval coin;
	/** the inner Circle of the coin. */
	private GOval innerCircle;
	/** the pacman picture on the coin. */
	private GArc pacman;
	/** the eye of pacman. */
	private GOval eye;
	/** the cool shadow of the coin. */
	private GArc shadow;

	/**
	 * creates a coin with high resolution and a cool shadow.
	 */
	public CoinsHighRes() {

		coin = new GOval(20, 20);
		coin.setFilled(true);
		coin.setColor(new Color(255, 215, 0));
		add(coin, 15, 15);

		innerCircle = new GOval(16, 16);
		innerCircle.setColor(new Color(255, 215, 0));
		innerCircle.setFillColor(new Color(127, 127, 127));
		innerCircle.setFilled(true);
		add(innerCircle, 17, 17);

		pacman = new GArc(10, 10, 40, 280);
		pacman.setColor(Color.YELLOW);
		pacman.setFilled(true);
		add(pacman, 20, 20);

		eye = new GOval(1.5, 1.5);
		eye.setColor(Color.black);
		eye.setFilled(true);
		add(eye, 23, 22);

		shadow = new GArc(16, 4, 0, 360);
		shadow.setColor(Color.DARK_GRAY);
		shadow.setFilled(true);
		add(shadow, 18, 38);
	}
}

/**
 * The graphic for the high resolution ghosts.
 */
class GhostHighRes extends GCompound {
	/** the body of the ghost. */
	private GArc ghost;
	/** his left eye. */
	private GOval leftEye;
	/** his right eye. */
	private GOval rightEye;
	/** right pupil. */
	private GOval rightPupil;
	/** left pupil. */
	private GOval leftPupil;
	/** mouth. */
	private GArc mouth;
	/** the hubbles that looks like tentacles. */
	private GArc hubble;
	/** the hubbles that looks like tentacles. */
	private GArc hubbleTwo;
	/** the hubbles that looks like tentacles. */
	private GArc hubbleThree;

	/**
	 * creates a really cute ghost.
	 * 
	 * @param color the color the ghost should have.
	 */
	public GhostHighRes(Color color) {
		ghost = new GArc(20, 40, 0, 180);
		ghost.setFilled(true);
		ghost.setColor(color);
		add(ghost, 15, 5);

		leftEye = new GOval(3, 3);
		leftEye.setColor(Color.white);
		leftEye.setFillColor(Color.white);
		leftEye.setFilled(true);
		add(leftEye, 22, 12);

		rightEye = new GOval(3, 3);
		rightEye.setColor(Color.white);
		rightEye.setFillColor(Color.white);
		rightEye.setFilled(true);
		add(rightEye, 27, 12);

		mouth = new GArc(10, 3, 180, 190);
		mouth.setFilled(true);
		mouth.setColor(Color.DARK_GRAY);
		add(mouth, 20, 18);

		leftPupil = new GOval(1, 1);
		leftPupil.setFilled(true);
		leftPupil.setColor(Color.black);
		add(leftPupil, 23.5, 13.5);

		rightPupil = new GOval(1, 1);
		rightPupil.setFilled(true);
		rightPupil.setColor(Color.black);
		add(rightPupil, 27.5, 13.5);

		hubble = new GArc(4, 4, 180, 180);
		hubble.setFilled(true);
		hubble.setColor(color);
		add(hubble, 15, 24);

		hubbleTwo = new GArc(4, 4, 180, 180);
		hubbleTwo.setFilled(true);
		hubbleTwo.setColor(color);
		add(hubbleTwo, 23, 24);

		hubbleThree = new GArc(4, 4, 180, 180);
		hubbleThree.setFilled(true);
		hubbleThree.setColor(color);
		add(hubbleThree, 31, 24);

	}
}

/** The graphic for the high resolution hearts. */
class HeartsHighRes extends GCompound {
	/** the little sparcle on the heart. */
	private GRect heartglance;
	/** the heart itself. */
	private GPolygon heartbody;

	/**
	 * creates the hearts.
	 */
	public HeartsHighRes() {
		heartbody = heart();
		heartbody.setFilled(true);
		heartbody.setColor(new Color(150, 0, 0));
		heartbody.setFillColor(new Color(230, 0, 0));
		heartglance = new GRect(2, 2);
		heartglance.setFilled(true);
		heartglance.setColor(new Color(255, 240, 240));
		add(heartbody, 25, 25);
		add(heartglance, 32, 18);
	}

	/**
	 * method that creates a beautiful shape for a heart.
	 * 
	 * @return the GPolygon that is a heart.
	 */
	private GPolygon heart() {
		GPolygon heart = new GPolygon();
		heart.addVertex(-15, 0);
		heart.addVertex(-20, 0);
		heart.addVertex(-20, -10);
		heart.addVertex(-15, -10);
		heart.addVertex(-15, -15);
		heart.addVertex(-5, -15);
		heart.addVertex(-5, -10);
		heart.addVertex(-2.5, -10);
		heart.addVertex(-2.5, -5);
		heart.addVertex(2.5, -5);
		heart.addVertex(2.5, -10);
		heart.addVertex(5, -10);
		heart.addVertex(5, -15);
		heart.addVertex(15, -15);
		heart.addVertex(15, -10);
		heart.addVertex(20, -10);
		heart.addVertex(20, 0);
		heart.addVertex(15, 0);
		heart.addVertex(15, 5);
		heart.addVertex(10, 5);
		heart.addVertex(10, 10);
		heart.addVertex(5, 10);
		heart.addVertex(5, 20);
		heart.addVertex(-5, 20);
		heart.addVertex(-5, 10);
		heart.addVertex(-10, 10);
		heart.addVertex(-10, 5);
		heart.addVertex(-15, 5);
		return heart;
	}
}

/**
 * The graphic for the high resolution version of VacMan.
 */
class VacManHighRes extends GCompound {
	/** its body. */
	private GArc body;
	/** first tooth. */
	private GRect tooth1;
	/** second tooth. */
	private GRect tooth2;
	/** eye. */
	private GOval eye;
	/** pupil. */
	private GOval pupil;

	/**
	 * creates the beautiful vacman skin. it looks like the original one, does it?
	 */
	public VacManHighRes() {

		body = new GArc(40, 40, 45, 315);
		body.setFilled(true);
		body.setColor(new Color(255, 200, 0));

		tooth1 = new GRect(5, 10);
		tooth1.setFilled(true);
		tooth1.setFillColor(Color.WHITE);

		tooth2 = new GRect(5, 10);
		tooth2.setFilled(true);
		tooth2.setFillColor(Color.WHITE);

		eye = new GOval(6, 6);
		eye.setFilled(true);
		eye.setFillColor(Color.WHITE);

		pupil = new GOval(2, 2);
		pupil.setFilled(true);
		pupil.setColor(Color.BLUE);
		pupil.setFillColor(Color.BLACK);

		add(tooth1, 34, 11);
		add(tooth2, 31, 12);
		add(body, 5, 5);
		add(eye, 25, 10);
		add(pupil, 27, 12);

	}

}