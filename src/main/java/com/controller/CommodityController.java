package com.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.SystemCommodityInformation;
import com.service.CommodityService;

/**
 * <h2>商品控制器</h2>
 * <p>负责处理所有的商品请求</p>
 * @author JiangShan
 * @date 2018年6月25日
 */
@Controller
@RequestMapping("/commodityController")
public class CommodityController {

	@Resource
	private CommodityService cs;
	
	/**
	 * <h2>获取商品信息</h2>
	 * <P>根据传入的页码和搜索信息查询对应的商品信息</p>
	 * @author JiangShan
	 * @Date 2018年6月25日
	 * @param request
	 * @return String 商品对象集合转换成的字符串
	 */
	@RequestMapping(value="/queryCommodity.do",produces="application/json; charset=utf-8")
	public @ResponseBody String queryCommodity(HttpServletRequest request) {	
		return cs.selectCommodity(request);
	}
	
	/**
	 * <h2>获取页数</h2>
	 * <P>根据搜索信息查询数量，并根据每页的信息数量，得出页数</p>
	 * @author JiangShan
	 * @Date 2018年6月25日
	 * @param request
	 * @return String page对象转换成的字符串
	 */
	@RequestMapping("/getPages.do")
	public @ResponseBody String getPages(HttpServletRequest request) {
		return cs.selectPageCount(request);
	}
	
	/**
	 * <h2>添加商品</h2>
	 * <p>根据传入的商品信息添加商品</p>
	 * @author JiangShan
	 * @Date 2018年6月25日
	 * @param request
	 * @return String 添加判定符
	 * <p>添加判定符包含以下几种：</p>
	 * <ul>
	 * <li>YES 添加成功</li>
	 * <li>NO 添加失败</li>
	 * </ul>
	 */
	@RequestMapping("/addCommodity.do")
	public @ResponseBody String addCommodity(HttpServletRequest request) {	
		return cs.addCommodity(request);
	}
	
	/**
	 * <h2>更改商品状态</h2>
	 * <p>根据当前商品的状态，将商品状态反转</p>
	 * @author JiangShan
	 * @Date 2018年6月25日
	 * @param request
	 * @return String 将页面重定向
	 */
	@RequestMapping("/changeStatus.do")
	public String changeStatus(HttpServletRequest request) {
		cs.updateStatus(request);
		return "redirect:/commodityController/queryCommodity.do";
	}
	
	/**
	 * <h2>更新商品信息的准备工作</h2>
	 * <p>根据商品Id查询商品信息，并将信息传送给前端页面</p>
	 * @author JiangShan
	 * @Date 2018年6月25日
	 * @param request
	 * @return String 跳转到修改页面
	 */
	@RequestMapping("/updateCommodityUI.do")
	public String updateCommodityUI(HttpServletRequest request) {
		SystemCommodityInformation sci = cs.selectCommodityById(request);
		request.setAttribute("sci", sci);	
		return "/view/commodity/updateCommodity.jsp";
	}
	
	/**
	 * <h2>更新商品信息</h2>
	 * <p>根据传入的商品ID，将商品信息进行修改</p>
	 * @author JiangShan
	 * @Date 2018年6月25日
	 * @param request
	 * @return String 修改判定符
	 * <p>修改判定符包含以下几种：</p>
	 * <ul>
	 * <li>YES 修改成功</li>
	 * <li>NO 修改失败</li>
	 * </ul>
	 */
	@RequestMapping("/updateCommodity.do")
	public @ResponseBody String updateCommodity(HttpServletRequest request) {		
		return cs.updateCommodityInformation(request);
	}
	
	/**
	 * <h2>搜索商品</h2>
	 * <p>根据传入的搜索内容，搜索对应的商品名称，并将商品信息返回到前端页面</p>
	 * @author JiangShan
	 * @Date 2018年6月25日
	 * @param request
	 * @return String 跳转到商品列表页面
	 */
	@RequestMapping("/search.do")
	public String search(HttpServletRequest request) {	
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		request.setAttribute("commodity", request.getParameter("commodity"));
		return "/view/commodity/listCommodity.jsp";
	}
}
