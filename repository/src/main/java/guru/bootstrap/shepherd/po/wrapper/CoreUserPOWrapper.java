package guru.bootstrap.shepherd.po.wrapper;

import guru.bootstrap.shepherd.po.CoreUserPO;

/**
 * @author tangcheng
 */
public class CoreUserPOWrapper extends BasePOWrapper {
    private CoreUserPO userPO;

    public CoreUserPOWrapper(CoreUserPO userPO) {
        this.userPO = userPO;
    }

    public CoreUserPO getUserPO() {
        return userPO;
    }

    @Override
    protected boolean isNotEmpty() {
        return this.userPO != null;
    }
}
// 2020/9/14 18:34
