----------------------------
Spring boot-异常处理1		|
----------------------------
	# 使用 Spring Boot 提供的处理器
	# 可以处理 404,500等异常
	1,自动异常处理 Controller ,实现接口: org.springframework.boot.autoconfigure.web.ErrorController.ErrorController
	2,覆写方法
		
		@Controller
		public class ErrorController implements org.springframework.boot.autoconfigure.web.ErrorController {

			private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);

			/**
			 * 错误页面的目录/路径
			 * classpatg:/templates/error/error.html
			 */
			private static final String ERROR_PATH = "error";

			/**
			 * 异常的时候,系统会自动跳转到这个路径,并且会在 request 域中存放很多关于异常的信息
			 * 可以根据异常状态码来判断是什么异常
			 * 也可以根据请求类型(ajax)决定是返回ModelAndView还是直接响应Ajax数据
			 * @param modelAndView
			 * @param request
			 * @param response
			 * @return
			 */
			@RequestMapping(value = ERROR_PATH,method = RequestMethod.GET)
			public ModelAndView error(ModelAndView modelAndView,
									  HttpServletRequest request,
									  HttpServletResponse response){
				Class exceptionType = (Class) request.getAttribute("javax.servlet.error.exception_type");   //异常类型
				Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception");    //异常类,只有在 500 异常的清空下,该值不为空
				Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");     //HTTP异常状态码
				String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");     //异常的Servlet
				String erroPath = (String) request.getAttribute("javax.servlet.error.request_uri");         //发生了异常的请求地址(并不是当前地址-request.getRequestURI())
				LOGGER.error("服务器异常, erroPath={}, message={}",erroPath,exception.getMessage());
				/**
				 * 防止部分浏览器、路由器（如小米）等劫持不显示自己的错误页面，强制将code设置为200
				 * 但这样ajax就无法检测错误状态
				 */
				response.setStatus(HttpServletResponse.SC_OK);
				return new ModelAndView(ERROR_PATH + "/error");
			}

			/**
			 * 异常的时候,系统会自动调用这个方法,获取到异常的路径.然后对该路径执行请求
			 * @return
			 */
			@Override
			public String getErrorPath() {
				return ERROR_PATH;
			}
		}

----------------------------
Spring boot-异常处理2		|
----------------------------
	# 使用 Spring 提供的处理器
	# 仅仅只能处理掉 500 服务器异常,404 之类的不会处理
	# 实现 HandlerExceptionResolver 注册到IOC来实现全局的异常处理

		@Component
		public class ExceptionController implements HandlerExceptionResolver {

			private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);
			
			/*
			 * 可以根据请求的类型(Ajax)来确定是返回mv还是直接响应ajax数据
			 * 这种方式,只能处理掉服务器的异常,无法处理 404 之类的异常
			 */
			@Override
			@ExceptionHandler
			public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
				HandlerMethod handlerMethod = (HandlerMethod) o;		//把handler参数强转
				Method method = handlerMethod.getMethod();				//获取到请求异常的方法对象
				return new ModelAndView("error/error");
			}
		}
	

	# 或者
	# 仅仅只能处理掉 500 服务器异常,404 之类的不会处理
	# 标识 @ControllerAdvice 超级Controller注解,注册到IOC,来实现全局异常的处理
		@ControllerAdvice
		public class ExceptionController {

			private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);
			
			/*
			 * 可以根据请求的类型(Ajax)来确定是返回mv还是直接响应ajax数据
			 * 这种方式,只能处理掉服务器的异常,无法处理 404 之类的异常
			 */
			@ExceptionHandler
			public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
				HandlerMethod handlerMethod = (HandlerMethod) o;		//把handler参数强转
				Method method = handlerMethod.getMethod();				//获取到请求异常的方法对象
				return new ModelAndView("error/error");
			}
		}


----------------------------
Spring boot-我最喜欢的实践  |
----------------------------
	# 视图请求异常,响应异常的视图信息
	# api接口异常,响应异常的JSON/状态码信息
	
	# 抽象基本的异常处理类
		import java.io.IOException;

		import javax.servlet.http.HttpServletRequest;
		import javax.servlet.http.HttpServletResponse;

		import org.springframework.http.HttpStatus;
		import org.springframework.http.converter.HttpMessageNotReadableException;
		import org.springframework.validation.BindException;
		import org.springframework.web.HttpMediaTypeNotSupportedException;
		import org.springframework.web.HttpRequestMethodNotSupportedException;
		import org.springframework.web.bind.MissingServletRequestParameterException;
		import org.springframework.web.bind.ServletRequestBindingException;
		import org.springframework.web.bind.annotation.ExceptionHandler;
		import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
		import org.springframework.web.multipart.MaxUploadSizeExceededException;
		import org.springframework.web.servlet.NoHandlerFoundException;

		import io.javaweb.paste.common.Message;
		import io.javaweb.paste.exception.ServiceException;


		public abstract class BaseExceptionAdvice {
			
			//请求路径未找到
			@ExceptionHandler(NoHandlerFoundException.class)  
			public Object noHandlerFoundException(HttpServletRequest request,HttpServletResponse response,NoHandlerFoundException e) throws IOException {
				return this.errorHandler(request,response,Message.fail(HttpStatus.NOT_FOUND, "请求路径不存在"),e);
			}
			
			//上传文件过大
			@ExceptionHandler(value = {MaxUploadSizeExceededException.class})
			public Object maxUploadSizeExceededException(HttpServletRequest request,HttpServletResponse response,MaxUploadSizeExceededException e) throws IOException {
				return this.errorHandler(request,response,Message.fail(HttpStatus.BAD_REQUEST, "文件大小不能超过:" + e.getMaxUploadSize()),e);
			}
			
			//请求方式不支持
			@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
			public Object httpRequestMethodNotSupportedException(HttpServletRequest request,
											HttpServletResponse response,
											HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException) {
				return this.errorHandler(request, response, Message.fail(HttpStatus.METHOD_NOT_ALLOWED, "请求方式不支持"),httpRequestMethodNotSupportedException);
			}
			
			//缺少必须参数
			@ExceptionHandler(MissingServletRequestParameterException.class)
			public Object missingServletRequestParameterException(HttpServletRequest request,
																		HttpServletResponse response,
																		MissingServletRequestParameterException exception) {
				return this.errorHandler(request,response,Message.fail(HttpStatus.BAD_REQUEST, "缺少必须参数:" + exception.getParameterName()),exception);
			}
			
			//业务异常
			@ExceptionHandler(ServiceException.class)
			public Object businessException(HttpServletRequest request,
													HttpServletResponse response,
													ServiceException exception) {
				return this.errorHandler(request, response, exception.message(),exception);
			}
			
			//系统异常
			@ExceptionHandler(Exception.class)
			public Object exception(HttpServletRequest httpServletRequest,
											HttpServletResponse httpServletResponse,
											Exception exception) {
				return this.errorHandler(httpServletRequest, httpServletResponse, Message.fail(HttpStatus.INTERNAL_SERVER_ERROR, "系统异常"),exception);
			}
			
			
			//非法请求
			@ExceptionHandler(value = {
				HttpMessageNotReadableException.class,
				IllegalArgumentException.class,
				MethodArgumentTypeMismatchException.class,
				BindException.class,
				HttpMediaTypeNotSupportedException.class,
				ServletRequestBindingException.class
			})
			public Object  badRequestException(HttpServletRequest request,HttpServletResponse response,Exception e) throws IOException {
				e.printStackTrace();
				return this.errorHandler(request,response,Message.fail(HttpStatus.BAD_REQUEST, "非法请求"),e);
			} 
			
			
			abstract public Object errorHandler(HttpServletRequest request,HttpServletResponse response,Message<Void> message,Throwable throwable); 
		}
	
	# api异常
		import javax.servlet.http.HttpServletRequest;
		import javax.servlet.http.HttpServletResponse;

		import org.springframework.http.ResponseEntity;
		import org.springframework.web.bind.annotation.ControllerAdvice;
		import org.springframework.web.bind.annotation.RestController;

		import io.javaweb.paste.common.Message;

		@ControllerAdvice(annotations = {RestController.class})		// 只处理restController
		public class RestExceptionAdvice extends BaseExceptionAdvice {

			@Override
			public ResponseEntity<String> errorHandler(HttpServletRequest request, HttpServletResponse response, Message<Void> message,Throwable throwable) {
				throwable.printStackTrace();
				return ResponseEntity.status(message.getStatus()).build();
			}

		}

	# 视图异常
		
		import java.io.PrintWriter;

		import javax.servlet.http.HttpServletRequest;
		import javax.servlet.http.HttpServletResponse;

		import org.springframework.stereotype.Controller;
		import org.springframework.web.bind.annotation.ControllerAdvice;
		import org.springframework.web.servlet.ModelAndView;

		import io.javaweb.paste.common.Message;
		import io.javaweb.paste.common.StringBuilderWriter;

		@ControllerAdvice(annotations = {Controller.class})		// 只处理Controller
		public class ExceptionAdvice extends BaseExceptionAdvice {

			public static final String ERROR_PATH = "error/error";
			
			@Override
			public Object errorHandler(HttpServletRequest request, HttpServletResponse response, Message<Void> message,Throwable throwable) {
				
				ModelAndView modelAndView = new ModelAndView(ERROR_PATH);
				
				modelAndView.addObject("message", message);
				
				StringBuilderWriter stringBuilderWriter = new StringBuilderWriter();
				
				PrintWriter printWrite = new PrintWriter(stringBuilderWriter);
				
				throwable.printStackTrace(printWrite);
				
				modelAndView.addObject("throwable", stringBuilderWriter.toString());
				
				return modelAndView;
			}
		}

		

		# 也可以定义俩注解
			@View		标识在视图Controller上
			@Api		标识在api接口Controller上


			// 处理视图异常
			@ControllerAdvice(annotations = {View.class})
			public class VieExceptionAdvice extends BaseExceptionAdvice
			

			// 处理接口异常
			@ControllerAdvice(annotations = {Api.class})
			public class ApiExceptionAdvice extends BaseExceptionAdvice 
