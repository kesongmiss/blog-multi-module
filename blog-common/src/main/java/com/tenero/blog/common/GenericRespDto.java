package com.tenero.blog.common;


/**
 * @author kesong
 * @date 2022/3/8 11:19
 */
public class GenericRespDto<T> extends BaseRespDto  {
    private static final long serialVersionUID = 2708344294462958057L;

    /**
     * 响应数据
     */
    private T result;

    public GenericRespDto() {
        super();
    }

    public GenericRespDto(T result) {
        super();
        setSuccess();
        this.result = result;
    }

    public GenericRespDto(String resultCode, String errorCode, String errorDesc) {
        super(resultCode, errorCode, errorDesc);
    }

    public GenericRespDto(String resultCode, String errorDesc) {
        super(resultCode, errorDesc);
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
