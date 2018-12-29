package com.moshiko.utils;

import java.util.HashMap;

import com.moshiko.beans.Type101BulkHead;
import com.moshiko.beans.Type102BulkHead;
import com.moshiko.enums.TypeOfBulkhead;


//HashMap because it contains only unique elements

public class BulkHeadFactory {
	
	    private static HashMap <String, Object> bulkHead;
	    
	    private BulkHeadFactory(){}

	    static {
	    	
	    	
	    }

	    public static Object get(TypeOfBulkhead typeOfBulkhead){
	        return bulkHead.get(typeOfBulkhead.name());
	    } 
	    
	    public static void set(TypeOfBulkhead typeOfBulkhead,Object object){
	    	
	    	if (typeOfBulkhead.equals(TypeOfBulkhead.NUM100.name())){
	    		Type101BulkHead type100BulkHead = new Type101BulkHead();
		    	bulkHead.put(typeOfBulkhead.name(),object);

	    		}
	    	if (typeOfBulkhead.equals(TypeOfBulkhead.NUM101.name())){
	    		Type101BulkHead type101BulkHead = new Type101BulkHead();
		    	bulkHead.put(typeOfBulkhead.name(),object);
	
	    	}
	    	if (typeOfBulkhead.equals(TypeOfBulkhead.NUM102.name())){
	    		Type102BulkHead type102BulkHead = new Type102BulkHead();
		    	bulkHead.put(typeOfBulkhead.name(),object);

	    	}
	    	
	    } 
	}

