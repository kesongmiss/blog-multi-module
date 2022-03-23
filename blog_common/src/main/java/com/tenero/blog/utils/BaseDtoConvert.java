package com.tenero.blog.utils;


import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kesong
 * @date 2022/3/14 14:20
 */
public class BaseDtoConvert {
    /**
     * 将t中的对象复制到v
     *
     * @param t
     * @param v
     * @param <T>
     * @param <V>
     */
    public static <T, V> V tToV(T t, V v) {
        if (t == null || v == null) {
            return v;
        }
        BeanUtils.copyProperties(t, v);
        return v;
    }

    /**
     * 批量复制对象
     *
     * @param ts
     * @param targetCls
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> List<V> tToV(List<T> ts, Class<V> targetCls) {
        if (ts == null) {
            return null;
        }
        List<V> vs = new ArrayList<V>();
        for (T t : ts) {
            vs.add(tToV(t, targetCls));
        }
        return vs;
    }

    /**
     * @param targetCls
     * @param v
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> T tToV(V v, Class<T> targetCls) {
        T t = null;
        if (v == null) {
            return t;
        }
        try {
            t = targetCls.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Bean 转化异常，请生成 " + targetCls.getName() + "的构造方法!");
        }
        BeanUtils.copyProperties(v, t);
        return t;
    }

//    /**
//     * 分页转换
//     *
//     * @param resultDto
//     * @param cls
//     * @param <T>
//     * @param <V>
//     * @return
//     */
//    public static <T, V> PageResultDto<T> toPageResultDto(PageResultDto<V> resultDto, Class<T> cls) {
//        PageResultDto<T> result = new PageResultDto<>();
//        result.setList(tToV(resultDto.getList(), cls));
//        result.setTotalPage(resultDto.getTotalPage());
//        result.setTotalRows(resultDto.getTotalRows());
//        result.setResultCode(resultDto.getResultCode());
//        result.setErrorCode(resultDto.getErrorCode());
//        result.setErrorDesc(resultDto.getErrorDesc());
//        result.setRows(resultDto.getRows());
//        return result;
//    }
//
//    public static <T> PageResultDto<T> toPageResult(Page<T> pageResult, PageReqDto reqDto) {
//        PageResultDto<T> result = new PageResultDto<>(reqDto);
//        result.setList(pageResult.getResult());
//        result.setTotalPage(pageResult.getTotal(), reqDto.getRows());
//        result.setSuccess();
//        return result;
//    }

    /**
     * 将值转换成字符串
     *
     * @param obj
     * @return
     */
    public static String valueOf(Object obj) {
        return (obj == null) ? "" : obj.toString();
    }

}
