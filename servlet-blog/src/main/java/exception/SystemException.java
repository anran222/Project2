package exception;

/**
 * @Author:xiang
 */
public class SystemException extends BaseException {

    public SystemException(String code, String message) {
        super(code, message,null);
    }

    public SystemException(String code, String message, Throwable cause) {
        super("SYS"+code, "系统异常"+message, cause);
    }
}
