package com.mastery.mshop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mastery.mshop.dao.GoodsDao;
import com.mastery.mshop.domain.Goods;
import com.mastery.mshop.domain.Page;
import com.mastery.mshop.domain.PageUtil;
import com.mastery.mshop.domain.User;
import com.mastery.mshop.service.GoodsService;

@RestController
public class GoodsController {
	@Autowired
	private GoodsService goodsService;

	@Autowired
	private GoodsDao goodsDao;

	// @RequestMapping("/goods/list")
	// public List selectAll() {
	// List<Goods> goods = goodsService.selectAll();
	// return goods;
	// }
	@RequestMapping("/goods/list")
	public PageUtil selectAll(@RequestParam(value = "page") int page, @RequestParam(value = "rows") int rows) {
		PageUtil pu = new PageUtil();
		System.out.println(page + "==============================");
		System.out.println(rows + "==============================");

		List<Goods> goodsAll = goodsService.selectAll();
		// xi
		Page pp = new Page(page, rows);
		List<Goods> goods = new ArrayList<Goods>();
		goods.clear();
		List<Goods> goodss = goodsDao.selectGoodsPage(pp);
		goods.addAll(goodss);
		goodss.clear();
		// xi
		System.err.println(pp);
		// List<Goods> goods = goodsService.selectGoods(page, rows);
		pu.setRows(goods);
		pu.setTotal(goodsAll.size());
		System.out.println(goodss.size());

		return pu;
	}

	@RequestMapping(value = "/goods/delete", method = RequestMethod.POST)
	public int deleteOne(@RequestParam(value = "id") String gid, Model model) {
		int temp = goodsService.deleteOne(gid);
		return temp;
	}

	@RequestMapping(value = "/goods/selectOne")
	public ModelAndView selectOne(@RequestParam(value = "id") String gid, Model model) {
		Goods goods = goodsService.selectById(gid);
		System.out.println(goods.getTitle());
		model.addAttribute("OneGoods", goods);
		return new ModelAndView("update");
	}

	@RequestMapping(value = "/goods/updateOne", method = RequestMethod.POST)
	public int updateOne(@RequestParam("id") String id, @RequestParam("title") String title,
			@RequestParam("price") Long price, @RequestParam("num") int num, @RequestParam("barcode") String barcode,
			Model model) {
		Goods goods = new Goods();
		goods.setId(id);
		goods.setTitle(title);
		goods.setPrice(price);
		goods.setNum(num);
		goods.setBarcode(barcode);
		goods.setCreated(new Date());
		int temp = goodsService.updateOne(goods);
		return temp;
	}

	@RequestMapping(value = "/goods/insertOne", method = RequestMethod.POST)
	public int insertOne(@RequestParam("title") String title, @RequestParam("price") long price,
			@RequestParam("num") int num, @RequestParam("barcode") String barcode, Model model) {
		Goods goods = new Goods();
		String t = UUID.randomUUID().toString().replaceAll("-", "");
		goods.setId(t);
		goods.setTitle(title);
		goods.setPrice(price);
		goods.setNum(num);
		goods.setBarcode(barcode);
		goods.setCreated(new Date());
		int temp = goodsService.insertOne(goods);
		return temp;
	}

	@RequestMapping(path = "/tt")
	public String Test() {
		System.out.println(11111111);
		System.out.println("111111");
		Map<Object, Object> m = new HashMap<Object, Object>();
		Goods d = new Goods();
		d.setImage("dd");
		d.setBarcode("ddss");
		m.put("d", d);
		Goods d2 = new Goods();
		d2.setImage("dd");
		d2.setBarcode("ddss");
		List<Goods> list = new ArrayList<Goods>();
		list.add(d);
		list.add(d2);
		m.put("dssss", list);
		return "forward:/tt";
	}
}
