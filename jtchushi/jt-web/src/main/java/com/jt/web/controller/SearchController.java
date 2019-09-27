package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.search.pojo.Item;
import com.jt.search.service.SearchService;

@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/search")
	public String search(String q,Model model) throws Exception {
		byte[] data = q.getBytes("ISO-8859-1");
		String key = new String(data,"UTF-8");
		//dubbo消费者发送信息给 提供者, 字符集的编码是UTF-8
		//调用微服务的提供者
		List<Item> itemList = searchService.findItemByKey(key);
		model.addAttribute("itemList",itemList);
		return "search";
	}
}
