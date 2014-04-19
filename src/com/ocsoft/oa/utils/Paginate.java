package com.ocsoft.oa.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Paginate
{
	
	/**
	 * Record list of current page
	 */
	public static final String ROWS="rows";
	
	/**
	 * Current page number
	 */
	public static final String PAGE="page";
	
	
	/**
	 * Number of records for each page
	 */
	public static final String RECORDS="records";
	
	/**
	 * Total number of records
	 */
	public static final String TOTAL="total";
	
	
	/**
	 * Default page size
	 */
	public static final int DEFAULT_PAGE_SIZE=20;
	
	
	
	public static Map getPage(List records, int page, int rows)
	{
		Map res = new HashMap();
		int count = records.size();
		int limit = rows;
		int totalPages =  count/limit;
		if (count%limit != 0) {
			totalPages++;
		}
		int currPage = Math.min(totalPages, page);
		
		int start = currPage * limit - limit;
		start = start < 0 ? 0 : start;
		List pageRecs = new ArrayList();
		if(count>0 && count<= limit)
			pageRecs = records;
		else if(count>0)
			pageRecs = records.subList(start, Math.min(start+limit,count-1));
		
		res.put("records", count);
		res.put("total", totalPages);
		res.put("page",currPage);
		res.put("rows", pageRecs);
		
		return res;
	}
	
	public static Map getPage(int count, List pageRecs, int page, int rows)
	{
		Map res = new HashMap();
		int limit = rows;
		int totalPages =  count/limit;
		if (count%limit != 0) {
			totalPages++;
		}
		int currPage = Math.min(totalPages, page);
		
		int start = currPage * limit - limit;
		start = start < 0 ? 0 : start;
		
		res.put("records", count);
		res.put("total", totalPages);
		res.put("page",currPage);
		res.put("rows", pageRecs);
		
		return res;
	}

	
}
