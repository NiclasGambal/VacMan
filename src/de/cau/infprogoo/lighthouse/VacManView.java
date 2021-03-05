package de.cau.infprogoo.lighthouse;

import java.awt.Color;
import java.io.IOException;

import acm.graphics.GArc;
import acm.graphics.GCompound;
import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.graphics.GPolygon;
import acm.graphics.GRect;
import acm.util.RandomGenerator;

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

/**
 * the Lighthouse resolution so we can see and play the game on a really big
 * screen. extending VacManView.
 *
 */
class LightHouseRes extends VacManView {

	/**
	 * calling the constructor of the superclass with modus 1.
	 */
	public LightHouseRes() {
		super(1);
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

/**
 * the second view is a corona version of pacman.
 *
 */
class CoronaRes extends VacManView {
	/**
	 * the constructor that calls the super constructor but with modus 50 so there
	 * is a bigger background
	 */
	public CoronaRes() {
		super(50);
	}

	/** declaring the randomgenerator instance. */
	RandomGenerator rgen = RandomGenerator.getInstance();

	/**
	 * implementing the abstract create map methof for this view.
	 */
	public void createMap(VacManModel model) {

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
					add(new WallHighRes(), c * 50, r * 50);
					// same thing for the coin and heart
				} else if (map[r][c] == MapTiles.COIN) {
					Color color = rgen.nextColor();
					add(new CoinsCoronaRes(color), c * 50, r * 50);
				} else if (map[r][c] == MapTiles.HEART) {
					add(new HeartsHighRes(), c * 50, r * 50);
				}
			}
		}
		// adding the Vacman in his Corona skin at his position on the map
		add(new VacManCoronaRes(), model.getVacManPos().getX() * 50, model.getVacManPos().getY() * 50);
		// adding every ghost with its color
		for (Ghost ghost : model.getGhosts()) {
			add(new GhostCoronaRes(ghost.getColor()), ghost.getPos().getX() * 50, ghost.getPos().getY() * 50);
		}

	}
}

/**
 * the beautiful pacman version that has nothing to do with corona. just pacman
 * with high graphics
 *
 */
class HighRes extends VacManView {
	/**
	 * calling the super constructor with 50 in this constructor.
	 */
	public HighRes() {
		super(50);
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

/**
 * The graphic for the corona resolution version of VacMan.
 */
class VacManCoronaRes extends GCompound {

	/** the head of corona vacman. */
	private GOval head;
	/** the left hair. */
	private GLine hairOne;
	/** the middle hair. */
	private GLine hairTwo;
	/** the right hair. */
	private GLine hairThree;
	/** left Eye. */
	private GOval leftEye;
	/** left pupil. */
	private GOval leftPupil;
	/** right Eye. */
	private GOval rightEye;
	/** right pupil. */
	private GOval rightPupil;
	/** the mouth. */
	private GArc mouth;
	/** the body. */
	private GOval body;
	/** left arm. */
	private GOval leftArm;
	/** right arm . */
	private GOval rightArm;
	/** left leg. */
	private GOval leftLeg;
	/** right leg. */
	private GOval rightLeg;

	/**
	 * constructor of the coronaVacMan
	 */
	public VacManCoronaRes() {
		// adding the head and so on
		head = new GOval(15, 15);
		head.setColor(new Color(255, 211, 155));
		head.setFilled(true);
		add(head, 15, 5);

		hairOne = new GLine(17, 5, 16, 1);
		add(hairOne);

		hairTwo = new GLine(22, 5, 22, 1);
		add(hairTwo);

		hairThree = new GLine(27, 5, 29, 1);
		add(hairThree);

		leftEye = new GOval(3, 3);
		leftEye.setColor(Color.white);
		leftEye.setFilled(true);
		add(leftEye, 17.5, 10);
		leftPupil = new GOval(1, 1);
		leftPupil.setColor(Color.black);
		leftPupil.setFilled(true);
		add(leftPupil, 18, 11);

		rightEye = new GOval(3, 3);
		rightEye.setColor(Color.white);
		rightEye.setFilled(true);
		add(rightEye, 23, 10);

		rightPupil = new GOval(1, 1);
		rightPupil.setColor(Color.black);
		rightPupil.setFilled(true);
		add(rightPupil, 24, 11);

		mouth = new GArc(9, 3, 180, 190);
		mouth.setColor(Color.RED);
		add(mouth, 16, 13);

		body = new GOval(17, 20);
		body.setColor(new Color(255, 211, 155));
		body.setFilled(true);
		add(body, 15, 18);

		leftArm = new GOval(12, 4);
		leftArm.setColor(new Color(255, 211, 155));
		leftArm.setFilled(true);
		add(leftArm, 5, 20);

		rightArm = new GOval(12, 4);
		rightArm.setColor(new Color(255, 211, 155));
		rightArm.setFilled(true);
		add(rightArm, 29, 20);

		leftLeg = new GOval(6, 15);
		leftLeg.setColor(new Color(255, 211, 155));
		leftLeg.setFilled(true);
		add(leftLeg, 17, 35);

		rightLeg = new GOval(6, 15);
		rightLeg.setColor(new Color(255, 211, 155));
		rightLeg.setFilled(true);
		add(rightLeg, 26, 35);
	}
}

/**
 * the Corona skin for the ghosts.
 *
 */
class GhostCoronaRes extends GCompound {
	/** the body of the ghost. */
	private GOval body;
	/** the left Eye . */
	private GOval leftEye;
	/** right eye. */
	private GOval rightEye;
	/** left pupil . */
	private GOval leftPupil;
	/** right one. */
	private GOval rightPupil;
	/** the left eyebrow. */
	private GPolygon leftEyebrow;
	/** right eyebrow. */
	private GPolygon rightEyebrow;

	/**
	 * constructor of the viruses(ghosts). creates a new virus.
	 * 
	 * @param color the color of the virus.
	 */
	public GhostCoronaRes(Color color) {
		body = new GOval(36, 36);
		body.setFilled(true);
		body.setColor(color);

		leftEye = new GOval(4, 4);
		leftEye.setFilled(true);
		leftEye.setFillColor(new Color(255, 230, 230));

		rightEye = new GOval(4, 4);
		rightEye.setFilled(true);
		rightEye.setFillColor(new Color(255, 230, 230));

		leftPupil = new GOval(2, 2);
		leftPupil.setFilled(true);
		leftPupil.setColor(Color.RED);

		rightPupil = new GOval(2, 2);
		rightPupil.setFilled(true);
		rightPupil.setColor(Color.RED);

		leftEyebrow = lEyebrow();
		leftEyebrow.setFilled(true);
		leftEyebrow.setColor(Color.BLACK);

		rightEyebrow = rEyebrow();
		rightEyebrow.setFilled(true);
		rightEyebrow.setColor(Color.BLACK);

		add(body, 7, 7);
		add(leftEye, 13, 14);
		add(rightEye, 27, 13);
		add(leftPupil, 14, 15);
		add(rightPupil, 28, 14);
		add(leftEyebrow, 19, 15);
		add(rightEyebrow, 25, 14);

	}

	/**
	 * creating the gpolygon for the eyebrow.
	 * 
	 * @return the polygon that represents the eyebrow.
	 */
	private GPolygon lEyebrow() {
		GPolygon poly = new GPolygon();
		poly.addVertex(0, 0);
		poly.addVertex(-10, -5);
		poly.addVertex(-8, -7.5);
		return poly;

	}

	/**
	 * creating the gpolygon for the eyebrow.
	 * 
	 * @return the polygon that represents the eyebrow.
	 */
	private GPolygon rEyebrow() {
		GPolygon poly = new GPolygon();
		poly.addVertex(0, 0);
		poly.addVertex(10, -5);
		poly.addVertex(8, -7.5);
		return poly;
	}

}

/**
 * the skin for the coins of the corona version. a.k.a. pills.
 *
 */
class CoinsCoronaRes extends GCompound {
	/** upper half of the pill. */
	private GArc pillOne;
	/** lower half. */
	private GArc pillTwo;
	/** the shadow of the pill. */
	private GArc shadow;

	/**
	 * constructor that creates a pill.
	 * 
	 * @param color the color of the upper half of the pill.
	 */
	public CoinsCoronaRes(Color color) {

		pillOne = new GArc(10, 35, 0, 180);
		pillOne.setColor(Color.white);
		pillOne.setFilled(true);
		add(pillOne, 20, 5);

		pillTwo = new GArc(10, 35, 180, 180);
		pillTwo.setColor(color);
		pillTwo.setFilled(true);
		add(pillTwo, 20, 5);

		shadow = new GArc(8, 3, 0, 360);
		shadow.setColor(Color.DARK_GRAY);
		shadow.setFilled(true);
		add(shadow, 21.5, 43);

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

/**
 * The graphic for the high resolution walls.
 */
class WallHighRes extends GCompound {
	/** the background of the wall. */
	private GRect wall;
	/** the bricks inside the wall. */
	private GRect bigBrick;
	/** the bricks inside the wall. */
	private GRect bigBrickTwo;
	/** the bricks inside the wall. */
	private GRect bigBrickThree;
	/** the bricks inside the wall. */
	private GRect bigBrickFour;
	/** the bricks inside the wall. */
	private GRect bigBrickFive;
	/** the bricks inside the wall. */
	private GRect bigBrickSix;

	/**
	 * creates a 50x50 wall that looks like a cool wall
	 */
	public WallHighRes() {
		wall = new GRect(50, 50);
		wall.setFilled(true);
		wall.setFillColor(new Color(205, 0, 0));
		wall.setColor(Color.white);
		add(wall, 0, 0);

		bigBrick = new GRect(25, 12);
		bigBrick.setColor(Color.white);
		add(bigBrick, 0, 37.5);

		bigBrickTwo = new GRect(25, 12);
		bigBrickTwo.setColor(Color.white);
		add(bigBrickTwo, 25, 37.5);

		bigBrickThree = new GRect(25, 12);
		bigBrickThree.setColor(Color.white);
		add(bigBrickThree, 0, 12.5);

		bigBrickFour = new GRect(25, 12);
		bigBrickFour.setColor(Color.white);
		add(bigBrickFour, 25, 12.5);

		bigBrickFive = new GRect(25, 13);
		bigBrickFive.setColor(Color.white);
		add(bigBrickFive, 12.5, 0);

		bigBrickSix = new GRect(25, 13);
		bigBrickSix.setColor(Color.white);
		add(bigBrickSix, 12.5, 25);
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