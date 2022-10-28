package whut.yy.utils.utils;

import lombok.Getter;
import lombok.Setter;

/***
 * 封装返回结果
 * @param <T>:返回数据类型
 */
@Setter
@Getter
public class R<T> {
    private Integer code;
    private String msg;
    private T data;

    private R() {
    }

    public static <T> R<T> ok(T data) {
        return getResult(data, 200, "成功");
    }

    public static <T> R<T> error(Integer code, String msg) {
        return getResult(null, code, msg);
    }

    public static <T> R<T> error(T data, Integer code, String msg) {
        return getResult(data, code, msg);
    }

    private static <T> R<T> getResult(T data, Integer code, String msg) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }
}

