package com.tenero.blog.common;


import java.io.Serializable;

/**
 * @author kesong
 * @date 2022/3/8 11:18
 */

public class BaseRespDto implements Serializable {
    private static final long serialVersionUID = -2865798622445981925L;

    /**
     * 结果编号
     */
    private String resultCode = FAIL;

    /**
     * 错误编号
     */
    private String errorCode;

    /**
     * 错误描述
     */
    private String errorDesc;

    /**
     * 成功
     */
    public static final String SUCCESS = "1";

    /**
     * 失败
     */
    public static final String FAIL = "0";

    /**
     * 返回操作成功
     * @return
     */
    public static BaseRespDto success(){
        return new BaseRespDto(SUCCESS);
    }

    public BaseRespDto() {
        super();
    }

    public BaseRespDto(String resultCode) {
        super();
        this.resultCode = resultCode;
    }

    public BaseRespDto(String resultCode, String errorCode, String errorDesc) {
        super();
        this.resultCode = resultCode;
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    public BaseRespDto(String resultCode, String errorDesc) {
        super();
        this.resultCode = resultCode;
        this.errorDesc = errorDesc;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public void setSuccess() {
        this.setResultCode(SUCCESS);
    }

    public void setFail() {
        this.setResultCode(FAIL);
    }

    public boolean isSuccess() {
        return SUCCESS.equals(this.getResultCode());
    }
}
