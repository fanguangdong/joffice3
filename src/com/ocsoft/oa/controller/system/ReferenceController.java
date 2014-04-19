package com.ocsoft.oa.controller.system;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ocsoft.oa.service.system.IReferenceTableService;
import com.ocsoft.oa.utils.NumberUtil;
import com.ocsoft.oa.vo.system.ReferenceTable;

@Controller
public class ReferenceController
{
	private IReferenceTableService refTableService;
	@RequestMapping("/ref/showRef")
	public String showRef()
	{
		
		return "refs";
	}
	
	
	@RequestMapping("/ref/saveRef")
	@ResponseBody
	public ReferenceTable saveRef(HttpServletRequest request, HttpServletResponse response)
	{
		String refId = request.getParameter("refId");
		String refKey = request.getParameter("refKey");
		String refTable = request.getParameter("refTable");
		String textField = request.getParameter("textField");
		String valueField = request.getParameter("valueField");
		
		
		ReferenceTable po = null;
		if(NumberUtil.isNumber(refId))
		{
			po = refTableService.get(Long.valueOf(refId));
		}
		else
		{
			po = new ReferenceTable();
			
		}
		po.setRefKey(refKey);
		po.setRefTable(refTable);
		po.setTextField(textField);
		po.setValueField(valueField);
		
		po.setUser("test");
		refTableService.save(po);
		
		return po;
	}
	
	@RequestMapping("/ref/getRefs")
	@ResponseBody
	public List<ReferenceTable> getRefs()
	{
		List<ReferenceTable> fields = refTableService.getAll();
		return fields;
	}
	
	@RequestMapping("/ref/getFefList")
	@ResponseBody
	public Collection getFefList(@RequestParam String refKey)
	{
		return refTableService.getRefList(refKey);
	}
	
	
	
	
	
	
	public IReferenceTableService getRefTableService()
	{
		return refTableService;
	}
	public void setRefTableService(IReferenceTableService refTableService)
	{
		this.refTableService = refTableService;
	}
}
