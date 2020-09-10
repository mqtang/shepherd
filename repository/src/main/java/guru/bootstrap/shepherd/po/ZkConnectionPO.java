package guru.bootstrap.shepherd.po;

import java.util.Date;

/**
 * @author tangcheng
 */
public class ZkConnectionPO {
    private Long zkConnId;
    private Long zkServerId;
    private Long userId;
    private String title;
    private String connectionName;
    private String connectionString;
    private String namespace;
    private Byte retryPolicy;
    private String sessionTimeout;
    private String connectTimeout;
    private String authScheme;
    private String authKey;
    private String description;

    private Byte stickTopFlag;
    private Date stickTopTime;
    private Integer orderIndex;
    private Date createTime;
    private Date updateTime;

}
// 2020/9/5 9:19
