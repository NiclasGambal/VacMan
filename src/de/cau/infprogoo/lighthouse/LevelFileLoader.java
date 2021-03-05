package de.cau.infprogoo.lighthouse;

import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LevelFileLoader {
	private ArrayList<Level> maps;

	public LevelFileLoader() throws JsonParseException, JsonMappingException, IOException {
		String[] pathnames;
		File f = new File("static\\levels");
		pathnames = f.list();

		for (String path : pathnames) {
			ObjectMapper mapper = new ObjectMapper();
			LevelFile obj = mapper.readValue(new File(path), LevelFile.class);
			Level level = new Level(obj.idMap);
			level.setVacmanStartPos(new Point(obj.playerPositionX, obj.playerPositionY));
			level.setName(obj.name);
			level.setGhostStartPos(
					obj.enemyPositions.stream().map(pos -> new Point(pos.posX, pos.posY)).collect(Collectors.toList()));
			maps.add(level);
		}
	}

}
