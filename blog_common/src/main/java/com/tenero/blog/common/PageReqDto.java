package com.tenero.blog.common;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kesong
 * @description
 * @date 2022/3/18 16:04
 */
public class PageReqDto implements Serializable {

    private static final long serialVersionUID = -3883676418991065147L;

    /**
     * 页数
     */
    private Integer page = 1;

    /**
     * 记录数
     */
    private Integer rows = 10;

    /**
     * 过滤条件(k:过滤字段,v:字段值)
     */
    private Map<String, Object> filter = new HashMap<>(8);

    /**
     * 排序规则(k:排序字段,v:排序方式DESC/ASC)
     */
    private Map<String, Object> sort = new HashMap<>(8);

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Map<String, Object> getFilter() {
        return filter;
    }

    public void setFilter(Map<String, Object> filter) {
        this.filter = filter;
    }

    public Map<String, Object> getSort() {
        return sort;
    }

    public void setSort(Map<String, Object> sort) {
        this.sort = sort;
    }

    public void clearParam() {
        if(filter != null) filter.clear();
        if(sort != null) sort.clear();
    }

}
