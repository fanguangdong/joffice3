package com.ocsoft.oa.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ocsoft.oa.service.system.IPositionService;
import com.ocsoft.oa.utils.NumberUtil;
import com.ocsoft.oa.vo.system.Position;

@Controller
public class PositionController
{
	@Resource(name="positionService")
	private IPositionService positionService;
	
	@RequestMapping("/position/show")
	public String showPositionMgt()
	{
		return "positionMain";
	}
	
	@RequestMapping("/position/getTreeData")
	@ResponseBody
	public List<Position> getTreeData(HttpServletRequest request, HttpServletResponse response)
	{
		List res = new ArrayList();
		Long posId = NumberUtil.toLong(request.getParameter("posId"), 0L);
		Long parentId = NumberUtil.toLong(request.getParameter("parentId"), 0L);
		//if(posId == 0L && parentId == 0L)
		//{
			Position parent =  positionService.getParentPositionByParentId(parentId);
			parent.setChildren(positionService.getChildByParentId(posId,true));
			res.add(parent);
		//}
		//else
	//	{
	//		res = positionService.getChildByParentId(posId);
	//	}
		
		return res;
	}
	@RequestMapping("/position/showPosDetail")
	public ModelAndView showPosDetail(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mv = new ModelAndView();
		Long posId = NumberUtil.toLong(request.getParameter("posId"), null);
		Long parentId = NumberUtil.toLong(request.getParameter("parentId"), null);
		Position position = null;
		if(posId != null)
		{
			position = positionService.get(posId);
		}
		else
		{
			position = new Position();
			position.setParentId(parentId);
		}
		mv.addObject("posDetail",position);
		mv.setViewName("positionDetail");
		return mv;
	}
	
	@RequestMapping("/position/add")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response)
	{
		
		return this.showPosDetail(request, response);
	}
	
	@RequestMapping("/position/edit")
	public ModelAndView edit(HttpServletRequest request, HttpServletResponse response)
	{
		
		return this.showPosDetail(request, response);
	}
	
	@RequestMapping("/position/save")
	@ResponseBody
	public Map savePos(HttpServletRequest request, HttpServletResponse response, Position vo)
	{
		Map rtn = new HashMap();
		Long posId = NumberUtil.toLong(request.getParameter("posId"), null);
		Long parentId = NumberUtil.toLong(request.getParameter("parentId"), null);
		Position po = null;
		if(posId != null)
		{
			po = positionService.get(posId);
		}
		else
		{
			po = new Position();
			po.setParentId(parentId);
		}
		
		po.setPosName(vo.getPosName());
		po.setPosDesc(vo.getPosDesc());
		po.setUser("test");
		positionService.save(po);
		rtn.put("status", 1);
		rtn.put("posDetail", po);
		return rtn;
	}
	
	
}
