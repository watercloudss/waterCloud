package com.cloudwater.common.commonVo;

public interface CommonResultConstant {
    //操作日志
	public static final int LOG_TYPE_1 = 1;
    //登录日志
    public static final int LOG_TYPE_2 = 2;
	/** {@code 500 Server Error} (HTTP/1.0 - RFC 1945) */
    public static final Integer SC_INTERNAL_SERVER_ERROR_500 = 500;
    /** {@code 200 OK} (HTTP/1.0 - RFC 1945) */
    public static final Integer SC_OK_200 = 20000;
    /**访问权限认证未通过 510*/
    public static final Integer SC_NO_AUTHZ=510;
    /**参数校验异常 511*/
    public static final Integer SC_PARAM_VIOLATION=511;
}
