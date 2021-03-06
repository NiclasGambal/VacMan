package vacman.model;

import java.awt.Point;
import java.util.List;

public class Level {

	private MapTiles[][] map;
	private Point vacmanStartPos;
	private List<Point> ghostStartPos;
	private String name;

	public Level(int[][] idMap) {

		// Initializes MapTiles map.
		this.map = new MapTiles[14][28];
		// Converts/copies the int map into the MapTiles map.
		for (int r = 0; r < idMap.length; r++) {
			for (int c = 0; c < idMap[r].length; c++) {
				if (idMap[r][c] == 1) {
					map[r][c] = MapTiles.WALL;
				}
				if (idMap[r][c] == 2) {
					map[r][c] = MapTiles.COIN;
				}
				if (idMap[r][c] == 3) {
					map[r][c] = MapTiles.HEART;
				}
				if (idMap[r][c] == 0) {
					map[r][c] = MapTiles.VOID;
				}
				if (idMap[r][c] > 3) {
					throw new IllegalArgumentException("Not included in MapTiles.");
				}
			}
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Point> getGhostStartPos() {
		return ghostStartPos;
	}

	public void setGhostStartPos(List<Point> ghostStartPos) {
		this.ghostStartPos = ghostStartPos;
	}

	public Point getVacmanStartPos() {
		return vacmanStartPos;
	}

	public void setVacmanStartPos(Point vacmanStartPos) {
		this.vacmanStartPos = vacmanStartPos;
	}

	public void setMap(MapTiles[][] map) {
		this.map = map;
	}

	public MapTiles[][] getMap() {
		return map;
	}
}
