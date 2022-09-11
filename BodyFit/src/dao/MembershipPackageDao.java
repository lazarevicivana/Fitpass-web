package dao;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import beans.MembershipPackage;

public class MembershipPackageDao extends GenericDao<String,MembershipPackage, MembershipPackageDao>{

	@Override
	protected String getKey(MembershipPackage entity) {
		// TODO Auto-generated method stub
		return entity.getId();
	}

	@Override
	protected String getFilePath() {
		// TODO Auto-generated method stub
		return "membershipPackages.json";
	}
	public Map<String,MembershipPackage> getAllToMap(){
		String json = "";
		try {
			json = new String(Files.readAllBytes(Paths.get(getPath())));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Type empMapType = new TypeToken<Map<String, MembershipPackage>>() {
		}.getType();

		Map<String, MembershipPackage> map = gs.fromJson(json, empMapType);
		return map;
		
	}

	@Override
	public ArrayList<MembershipPackage> getAllToList() {
		// TODO Auto-generated method stub
		return new ArrayList<MembershipPackage>(getAllToMap().values());
	}
	


}
