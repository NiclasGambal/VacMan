package de.cau.infprogoo.lighthouse;

import java.util.ArrayList;

public class LevelFile {
	public int[][] idMap;
	public int playerPositionX;
	public int playerPositionY;
	public String name;
	public ArrayList<Position> enemyPositions;

}

class Position {
	public int posX;
	public int posY;

}
