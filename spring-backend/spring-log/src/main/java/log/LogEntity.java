package log;

/**
 * 日志实体
 * <p>
 * <p>
 * Created by liuchenwei on 2017/7/13.
 */
public class LogEntity {

	// 用户操作
	private String operation;
	// 请求方法
	private String method;
	// 请求参数
	private String params;
	// 执行时长(毫秒)
	private Long time;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "LogEntity{" +
                "operation='" + operation + '\'' +
                ", method='" + method + '\'' +
                ", params='" + params + '\'' +
                ", time=" + time +
                '}';
    }
}
