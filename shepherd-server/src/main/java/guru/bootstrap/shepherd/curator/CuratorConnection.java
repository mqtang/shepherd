package guru.bootstrap.shepherd.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.ACLProvider;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;

import java.util.List;

/**
 * @author tangcheng
 */
public class CuratorConnection {
    private String host;
    private String port;
    private CuratorFramework curator;

    public CuratorConnection(String host, String port) {
        this.host = host;
        this.port = port;
        initClient(host, port);
    }

    private void initClient(String host, String port) {
        String serverAddress = host + ":" + port;
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 5);
        curator = CuratorFrameworkFactory.builder()
                .connectString(serverAddress)
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(60 * 1000)
                .connectionTimeoutMs(3 * 1000)
//                .namespace("apple-3")
//                .aclProvider(new ACLProvider() {
//                    @Override
//                    public List<ACL> getDefaultAcl() {
//                        return ZooDefs.Ids.CREATOR_ALL_ACL;
//                    }
//
//                    @Override
//                    public List<ACL> getAclForPath(String path) {
//                        return ZooDefs.Ids.CREATOR_ALL_ACL;
//                    }
//                })
//                .authorization("digest", "root:root1995".getBytes())
                .build();
        curator.start();
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public CuratorFramework getCurator() {
        return curator;
    }
}
// 2020/9/3 22:28
