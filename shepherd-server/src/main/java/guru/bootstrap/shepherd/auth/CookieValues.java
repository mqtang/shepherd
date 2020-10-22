package guru.bootstrap.shepherd.auth;

import java.util.Date;

/**
 * @author tangcheng
 */
public class CookieValues {

    private String _csrf;
    private Date _lvt;

    public String get_csrf() {
        return _csrf;
    }

    public void set_csrf(String _csrf) {
        this._csrf = _csrf;
    }

    public Date get_lvt() {
        return _lvt;
    }

    public void set_lvt(Date _lvt) {
        this._lvt = _lvt;
    }

}
// 2020/10/22 15:16
