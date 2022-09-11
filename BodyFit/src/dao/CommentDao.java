package dao;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import beans.Comment;

public class CommentDao   extends GenericDao<String, Comment, CommentDao>{

	@Override
	protected String getKey(Comment entity) {
		// TODO Auto-generated method stub
		return entity.getId();
	}

	@Override
	protected String getFilePath() {
		// TODO Auto-generated method stub
		return "comments.json";
	}
	public Map<String,Comment> getAllToMap(){
		String json = "";
		try {
			json = new String(Files.readAllBytes(Paths.get(getPath())));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Type empMapType = new TypeToken<Map<String, Comment>>() {
		}.getType();

		Map<String, Comment> map = gs.fromJson(json, empMapType);
		return map;
		
	}

	@Override
	public ArrayList<Comment> getAllToList() {
		// TODO Auto-generated method stub
		return new ArrayList<Comment>(getAllToMap().values());
	}



}
