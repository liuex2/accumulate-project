package com.cif.accumulate.rest.vo;

/**
 * @Author: liuxincai
 * @Description: the base page VO
 * @Date: 2019/5/29 16:06
 */
public class BasePageVO {
    private static final Integer DEFAULT_PAGE_SIZE = 20;
    private static final Integer DEFAULT_FIRST_PAGE = 1;

    //每页显示的大小
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    //当前页数
    private Integer currentPage = DEFAULT_FIRST_PAGE;

    //排序,desc或asc
    private String order;

    //需要排序的字段
    private String sort;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "BaseVO{" +
                "pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", order='" + order + '\'' +
                ", sort='" + sort + '\'' +
                '}';
    }
}
