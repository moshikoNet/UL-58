package com.moshiko.utils;

import java.util.HashMap;
import java.util.Map;

import com.moshiko.Dao.AdminsDao;
import com.moshiko.Dao.UsersDao;
import com.moshiko.enums.UserType;

public class DaoFactory {
	
	    private static final Map <String, UsersDao> dao;
	    
	    private DaoFactory(){}

	    static {
	    	
	    	//HashMap because it contains only unique elements
	    	
	    	dao = new HashMap<String, UsersDao>();
	    	dao.put(UserType.ADMIN.name(), new AdminsDao());
	    	dao.put(UserType.ENGINEER.name(), new UsersDao());
	    	dao.put(UserType.INSPECTOR.name(), new UsersDao());

	    }

	    public static UsersDao get(UserType userType){
	        return dao.get(userType.name());
	    } 
	}

