package com.tenero.blog.common;



import com.github.pagehelper.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

import static com.tenero.blog.utils.BaseDtoConvert.tToV;


/**
 * @author kesong
 * @date 2022/3/8 19:03
 */
public class CopyDataUtils {

    /**
     * 拷贝单个对象
     *
     * @param v   源对象
     * @param t   目标对象
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> V copyObject(V v, T t) {

        if (t == null || v == null) {
            return v;
        }
        BeanUtils.copyProperties(v, t);
        return v;
    }


    /**
     * 拷贝数据到新对象
     *
     * @param v
     * @param t
     * @param <V>
     * @param <T>
     * @return
     */
    public static <V, T> T copyNewObject(V v, Class<T> t) {
        T t1 = null;
        if (ObjectUtils.isEmpty(v)) {
            return t1;
        }

        try {
            t1 = t.newInstance();
            BeanUtils.copyProperties(v, t1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t1;
    }


    /**
     * copy集合
     *
     * @param v   源集合
     * @param t   目标集合
     * @param <V>
     * @param <T>
     * @return
     */
    public static <V, T> List<T> copyArrayList(List<V> v, List<T> t) {
        if (CollectionUtils.isEmpty(v)) {
            return t;
        }
        BeanUtils.copyProperties(v, t);
        return t;
    }


    public static <V, T> List<T> copyNewArrayList(List<V> v, Class<T> t) {
        List<T> t1 = new ArrayList<>();
        if (CollectionUtils.isEmpty(v)) {
            return t1;
        }
        for (V v1 : v) {
            t1.add(copyNewObject(v1, t));
        }
        return t1;
    }

    /**
     * 分页转换
     *
     * @param resultDto
     * @param cls
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> PageResultDto<T> toPageResultDto(PageResultDto<V> resultDto, Class<T> cls) {
        PageResultDto<T> result = new PageResultDto<>();
        result.setList(tToV(resultDto.getList(), cls));
        result.setTotalPage(resultDto.getTotalPage());
        result.setTotalRows(resultDto.getTotalRows());
        result.setResultCode(resultDto.getResultCode());
        result.setErrorCode(resultDto.getErrorCode());
        result.setErrorDesc(resultDto.getErrorDesc());
        result.setRows(resultDto.getRows());
        return result;
    }

    public static <T> PageResultDto<T> toPageResult(Page<T> pageResult, PageReqDto reqDto) {
        PageResultDto<T> result = new PageResultDto<>(reqDto);
        result.setList(pageResult.getResult());
        result.setTotalPage(pageResult.getTotal(), reqDto.getRows());
        result.setSuccess();
        return result;
    }

}
