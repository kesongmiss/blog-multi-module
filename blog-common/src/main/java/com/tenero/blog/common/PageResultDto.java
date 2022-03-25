package com.tenero.blog.common;


import java.util.Collections;
import java.util.List;

/**
 * @author kesong
 * @version V1.0
 * @description
 * @date 2022/3/18 16:03
 */
public class PageResultDto<T> extends BaseRespDto {
    private static final long serialVersionUID = -3883676418991065147L;

    /**
     * 查询结果列表
     */
    private List<T> list = Collections.emptyList();

    /**
     * 总数
     */
    private Long totalRows = 0L;

    /**
     * 页码
     */
    private Integer page = 0;

    /**
     * 总页数
     */
    private Integer totalPage = 0;

    private Integer rows = 10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * 设置总页数
     * @param totalRows 总数
     * @param rows 每页显示数量
     * @return
     */
    public PageResultDto<T> setTotalPage(Long totalRows, Integer rows) {
        if (totalRows == null) {
            totalRows = 0L;
        }
        this.totalRows = totalRows;
        if (rows == null) {
            return this;
        }
        this.totalPage = (int) Math.ceil(((double) totalRows) / rows);
        this.rows = rows;
        return this;
    }

    public PageResultDto() {
        super();
    }

    public PageResultDto(PageReqDto reqDto) {
        super();
        this.page = reqDto.getPage();
        this.rows = reqDto.getRows();
    }

    public PageResultDto(String resultCode, String errorCode, String errorDesc) {
        super(resultCode, errorCode, errorDesc);
    }

    public PageResultDto(String resultCode, String errorDesc) {
        super(resultCode, errorDesc);
    }

    public PageResultDto(String resultCode) {
        super(resultCode);
    }

    public PageResultDto(List<T> list, Long totalRows) {
        super(BaseRespDto.SUCCESS);
        this.list = list;
        this.totalRows = totalRows;
    }

    public PageResultDto(List<T> list, Integer page) {
        super(BaseRespDto.SUCCESS);
        this.list = list;
        this.page = page;
    }

    public PageResultDto(List<T> list, Long totalRows, Integer page) {
        super(BaseRespDto.SUCCESS);
        this.list = list;
        this.totalRows = totalRows;
        this.page = page;
    }

    public PageResultDto(List<T> list, Long totalRows, Integer page, Integer totalPage) {
        super(BaseRespDto.SUCCESS);
        this.list = list;
        this.totalRows = totalRows;
        this.page = page;
        this.totalPage = totalPage;
    }

    public PageResultDto(List<T> list, Long totalRows, Integer page, Integer totalPage,
                         Integer rows) {
        super(BaseRespDto.SUCCESS);
        this.list = list;
        this.totalRows = totalRows;
        this.page = page;
        this.totalPage = totalPage;
        this.rows = rows;
    }

    public List<T> getList() {
        return list;
    }

    public void setResult(List<T> list) {
        this.list = list;
    }

    public Long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Long totalRows) {
        this.totalRows = totalRows;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
