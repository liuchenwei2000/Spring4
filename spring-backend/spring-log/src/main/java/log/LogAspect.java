package log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 日志切面
 * <p>
 * <p>
 * Created by liuchenwei on 2017/7/13.
 */
@Aspect
@Component
public class LogAspect {

//    private static Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

//	@Autowired
//	private LogService logService;
	
	@Pointcut("@annotation(log.Log)")
	public void logPointCut() { 
		
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();

		Object result = point.proceed();

		long time = System.currentTimeMillis() - beginTime;

		// 记录日志
		log(point, time);

		return result;
	}

	private void log(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		LogEntity logEntity = new LogEntity();
        logEntity.setTime(time);

		Log log = method.getAnnotation(Log.class);
		if(log != null){
			// 注解上的描述
            logEntity.setOperation(log.value());
		}

		// 方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
        logEntity.setMethod(className + "." + methodName + "()");

		// 参数
		Object[] args = joinPoint.getArgs();
		if(args != null && args.length > 0){
            logEntity.setParams(args[0].toString());
        }

        System.out.println(logEntity.toString());
    }
}
