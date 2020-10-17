package guru.bootstrap.shepherd.http;

/**
 * @author tangcheng
 */
public class HttpRestEntity<T> {
    private T result;
    private ResultStatus status;

    public HttpRestEntity() {
    }

    private HttpRestEntity(T result) {
        this.result = result;
    }

    public static <T> HttpRestEntity<T> newResult(T result) {
        HttpRestEntity<T> entity = new HttpRestEntity<>();
        entity.result = result;
        return entity;
    }

    public static HttpRestEntity<String> newEmptyResult() {
        return new HttpRestEntity<>("");
    }

    public HttpRestEntity<T> withStatus(ResultStatus status) {
        this.status = status;
        return this;
    }

    public T getResult() {
        return result;
    }

    public ResultStatus getStatus() {
        return status;
    }
}
// 2020/9/16 19:56
