package com.atguigu.gmallsearch.controller;

import com.atguigu.gmallsearch.bean.SearchParamVo;
import com.atguigu.gmallsearch.bean.SearchResponseVo;
import com.atguigu.gmallsearch.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: dpc
 * @data: 2020/5/29,13:35
 */
@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;

    @GetMapping("search")
    public String search(SearchParamVo searchParamVo, Model model) {
        SearchResponseVo responseVo = this.searchService.search(searchParamVo);
        model.addAttribute("response",responseVo);
        model.addAttribute("searchParam",searchParamVo);
        return "search";
    }
}
