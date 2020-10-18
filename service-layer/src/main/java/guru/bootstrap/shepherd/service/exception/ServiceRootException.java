package guru.bootstrap.shepherd.service.exception;

/**
 * @author tangcheng
 */
public abstract class ServiceRootException extends Exception {

    protected ServiceRootException(String message) {
        super(message);
    }

    public abstract String getCodeWithPrefix();

    public abstract String getMsg();

    public abstract String getMsgEn();

}
// 2020/10/18 1:34
