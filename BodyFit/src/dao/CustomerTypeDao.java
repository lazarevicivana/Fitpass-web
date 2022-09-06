package dao;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import beans.CustomerType;

public class CustomerTypeDao  extends GenericDao<String,CustomerType, CustomerTypeDao>{

	
	public Map<String,CustomerType> getAllToMap(){
		String json = "";
		try {
			json = new String(Files.readAllBytes(Paths.get(getPath())));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Type empMapType = new TypeToken<Map<String, CustomerType>>() {
		}.getType();

		Map<String, CustomerType> map = gs.fromJson(json, empMapType);
		return map;
		
	}

	@Override
	public ArrayList<CustomerType> getAllToList() {
		// TODO Auto-generated method stub
		return new ArrayList<CustomerType>(getAllToMap().values());
	}


	@Override
	protected String getFilePath() {
		// TODO Auto-generated method stub
		return "customerTypes.json";
	}

	@Override
	protected String getKey(CustomerType entity) {
		// TODO Auto-generated method stub
		return entity.getName();
	}
	


}
