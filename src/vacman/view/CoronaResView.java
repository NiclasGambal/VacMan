package vacman.view;

import java.awt.Color;

import acm.graphics.GArc;
import acm.graphics.GCompound;
import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.graphics.GPolygon;
import acm.util.RandomGenerator;
import vacman.model.Ghost;
import vacman.model.MapTiles;
import vacman.model.VacManModel;

/**
 * the second view is a corona version of pacman.
 *
 */
public class CoronaResView extends VacManView {
	/**
	 * the constructor that calls the super constructor but with modus 50 so there
	 * is a bigger background
	 */
	public CoronaResView() {
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
