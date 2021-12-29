package com.watercloud.webmagic.common.exception;

import com.cloudwater.common.commonVo.Result;
import com.watercloud.webmagic.common.util.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 异常处理器
 * 针对不同的异常类进行不同的处理后，返回结果
 */
@RestControllerAdvice
@Slf4j
public class CloudWebmagicExceptionHandler {

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(CloudWebmagicException.class)
	public Result<?> handleCloudWebmagicException(CloudWebmagicException e){
		log.error(e.getMessage(), e);
		return Result.error(e.getMessage());
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public Result<?> handlerNoFoundException(Exception e) {
		log.error(e.getMessage(), e);
		return Result.error(404, "路径不存在，请检查路径是否正确");
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public Result<?> handleDuplicateKeyException(DuplicateKeyException e){
		log.error(e.getMessage(), e);
		return Result.error("数据库中已存在该记录");
	}

//	@ExceptionHandler({UnauthorizedException.class, CloudWebmagicException.class})
//	public Result<?> handleAuthorizationException(CloudWebmagicException e){
//		log.error(e.getMessage(), e);
//		return Result.noauth("没有权限，请联系管理员授权");
//	}

	// 捕获方法参数校验异常
	@ExceptionHandler(ConstraintViolationException.class)
	public Result<?> handleConstraintViolationException(ConstraintViolationException e){
		log.error(e.getMessage(), e);
		Set<ConstraintViolation<?>> message = e.getConstraintViolations();
		HashMap<String, Object> map = new HashMap<>();
		message.stream().forEach(msg -> {
			String path = msg.getPropertyPath().toString();
			String field = path.substring(path.indexOf(".")+1);
			map.put(field,msg.getMessageTemplate());
		});
		return Result.error(CommonConstant.SC_PARAM_VIOLATION,map.toString());
	}

	// 捕获实体参数校验异常
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Result<?> handleResolveMethodArgumentNotValidException(MethodArgumentNotValidException e){
		log.error(e.getMessage(), e);
		List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
		HashMap<String, Object> map = new HashMap<>();
		allErrors.stream().forEach(error -> {
			FieldError fieldError = (FieldError) error;
			map.put(fieldError.getField(), fieldError.getDefaultMessage());
		});
		return Result.error(CommonConstant.SC_PARAM_VIOLATION,map.toString());
	}

	// 捕获Form实体参数校验异常
	@ExceptionHandler(BindException.class)
	public Result<?> handleBindException(BindException e){
		log.error(e.getMessage(), e);
		List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
		HashMap<String, Object> map = new HashMap<>();
		allErrors.stream().forEach(error -> {
			FieldError fieldError = (FieldError) error;
			map.put(fieldError.getField(), fieldError.getDefaultMessage());
		});
		return Result.error(CommonConstant.SC_PARAM_VIOLATION,map.toString());
	}

	@ExceptionHandler(ShiroException.class)
	public Result<?> handle401() {
		return Result.error(CommonConstant.SC_NO_AUTHZ,"没有进行认证！");
	}

	@ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
	public Result<?> handleAuthorizationException(AuthorizationException e){
		log.error(e.getMessage(), e);
		return Result.error(CommonConstant.SC_NO_AUTHZ,"没有权限，请联系管理员授权!");
	}

	@ExceptionHandler(AuthenticationException.class)
	public Result<?> handleAuthenticationException(AuthenticationException e){
		log.error(e.getMessage(), e);
		return Result.error(CommonConstant.SC_NO_AUTHZ,"没有权限，请联系管理员授权");
	}

	@ExceptionHandler(Exception.class)
	public Result<?> handleException(Exception e){
		log.error(e.getMessage(), e);
		return Result.error(e.getMessage());
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Result<?> HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
		StringBuffer sb = new StringBuffer();
		sb.append("不支持");
		sb.append(e.getMethod());
		sb.append("请求方法，");
		sb.append("支持以下");
		String [] methods = e.getSupportedMethods();
		if(methods!=null){
			for(String str:methods){
				sb.append(str);
				sb.append("、");
			}
		}
		log.error(sb.toString(), e);
		//return Result.error("没有权限，请联系管理员授权");
		return Result.error(405,sb.toString());
	}
	
	 /** 
	  * spring默认上传大小100MB 超出大小捕获异常MaxUploadSizeExceededException 
	  */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result<?> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
    	log.error(e.getMessage(), e);
        return Result.error("文件大小超出100MB限制, 请压缩或降低文件质量! ");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result<?> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
    	log.error(e.getMessage(), e);
        return Result.error("字段太长,超出数据库字段的长度");
    }

//    @ExceptionHandler(PoolException.class)
//    public Result<?> handlePoolException(PoolException e) {
//    	log.error(e.getMessage(), e);
//        return Result.error("Redis 连接异常!");
//    }

}
