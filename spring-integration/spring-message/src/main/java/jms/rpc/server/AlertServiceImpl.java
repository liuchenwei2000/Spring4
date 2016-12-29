package jms.rpc.server;

import jms.rpc.AlertService;

/**
 * RPC 服务实现
 * <p>
 * <p>
 * Created by liuchenwei on 2016/12/29.
 */
public class AlertServiceImpl implements AlertService {

    @Override
    public void sendEmail(String to) {
        System.out.println("[Alert!!!] Send Email to " + to);
    }
}
