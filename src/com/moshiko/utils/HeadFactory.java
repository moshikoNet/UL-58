package com.moshiko.utils;

import java.util.HashMap;
import java.util.Map;

import com.moshiko.beans.BulkHead;
import com.moshiko.beans.ConicalHead;
import com.moshiko.beans.DishedHead;
import com.moshiko.beans.Head;
import com.moshiko.beans.Type101BulkHead;
import com.moshiko.beans.Type102BulkHead;
import com.moshiko.enums.TypeOfBulkhead;
import com.moshiko.enums.TypeOfHead;

public class HeadFactory {
	
	    private static Map <String, Object> head;
	    
	    private HeadFactory(){}

	    static {
	    	
	    	//HashMap because it contains only unique elements
	    	
	    	head = new HashMap<String, Object>();
	    	head.put(TypeOfHead.FLANGE_FLAT.name(), new Head());
	    	head.put(TypeOfHead.CONICAL.name(), new ConicalHead());
	    	head.put(TypeOfHead.DISHED.name(), new DishedHead());

	    }
	    

	    public static Object get(TypeOfHead typeOfHead){
	        return head.get(typeOfHead.name());
	    } 
	    
	    public static void set(TypeOfHead typeOfHead,Object object){
	         head.put(typeOfHead.name(),object);
	    } 
	    
	}

