package com.mwj.core.paging;

import org.springframework.data.domain.Page;
import org.springframework.ui.ModelMap;

/**
 * Created by gonglei on 16/2/12.
 * 分页显示
 */
public class PageTools {

    public static void buildPage(Page<?> page, ModelMap modelMap) {
        int totalPage = page.getTotalPages();
        buildPage(page.getNumber(), totalPage, page.getSize(), modelMap);
    }

    public static void buildPage2(Page<?> page, ModelMap modelMap) {
        int totalPage = page.getTotalPages();
        buildPage2(page.getNumber(), totalPage, page.getSize(), modelMap);
    }


    public static void buildPage(int toPage, int totalPage, int pageSize, ModelMap modelMap) {
        int currentPage = (totalPage < toPage ? totalPage : toPage)+1;
        modelMap.addAttribute("currentPage", currentPage);
        int maxPage = (currentPage / pageSize + 1) * pageSize;
        maxPage = totalPage < maxPage ? totalPage : maxPage;
        modelMap.addAttribute("totalPage", totalPage);
        modelMap.addAttribute("maxPage", maxPage);
        modelMap.addAttribute("minPage", (currentPage / pageSize) * pageSize + 1);
    }

    public static void buildPage2(int toPage, int totalPage, int pageSize, ModelMap modelMap) {
        int currentPage = totalPage < toPage ? totalPage : toPage;
        modelMap.addAttribute("currentPage2", currentPage);
        int maxPage = (currentPage / pageSize + 1) * pageSize;
        maxPage = totalPage < maxPage ? totalPage : maxPage;
        modelMap.addAttribute("maxPage2", maxPage);
        modelMap.addAttribute("minPage2", (currentPage / pageSize) * pageSize + 1);
    }
}
