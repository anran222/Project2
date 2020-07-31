package model;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:xiang
 * @Date:2020/7/15 20:10
 */
public class Page {
    private String searchText;
    private String sortOrder;
    private Integer pageSize;
    private Integer pageNumber;

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public static Page parse(HttpServletRequest req){
        Page p=new Page();
        p.searchText=req.getParameter("searchText");
        p.sortOrder=req.getParameter("sortOrder");
        p.pageSize=Integer.parseInt(req.getParameter("pageSize"));
        p.pageNumber=Integer.parseInt(req.getParameter("pageNumber"));
        return p;
    }
}
