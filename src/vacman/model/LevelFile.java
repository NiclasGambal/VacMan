package vacman.model;

import java.util.List;

public class LevelFile {

	public int[][] idMap;
	public int playerPositionX;
	public int playerPositionY;
	public String name;
	public List<Position> enemyPositions;

}

class Position {

	public int posX;
	public int posY;

}
