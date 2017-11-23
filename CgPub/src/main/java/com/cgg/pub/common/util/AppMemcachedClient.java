package com.cgg.pub.common.util;



import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class AppMemcachedClient {
    private String servers;

//    private net.spy.memcached.MemcachedClient mc;

    public void setServers(String servers) {
        this.servers = servers;
    }

    private void connect() {
//        try {
//            String[] serverArray = servers.split(",");
//            List<InetSocketAddress> serverAddress = new ArrayList<InetSocketAddress>();
//            for (String server : serverArray) {
//                String[] temp = server.split(":");
//                serverAddress.add(new InetSocketAddress(temp[0], Integer.valueOf(temp[1])));
//            }
//
//            mc = new net.spy.memcached.MemcachedClient(serverAddress);
//        } catch (Exception e) {
//            throw new AppException(e);
//        }
    }

    public void put(String key, int exp, Object value) {
//        if (mc == null || !mc.isAlive()) {
//            connect();
//        }
//
//        mc.set(key, exp, value);
    }

    public Object get(String key) {
//        if (mc == null || !mc.isAlive()) {
//            connect();
//        }
//
//        return mc.get(key);
    	return null;
    }

    public void clear(String key) {
//        if (mc == null || !mc.isAlive()) {
//            connect();
//        }
//        mc.delete(key);
    }

    public void closeConnection() {
//        if (mc != null) {
//            try {
//                mc.shutdown();
//            } catch (Exception e) {
//
//            }
//        }
    }

    public void replace(String key, int exp, Object obj) {
//        mc.replace(key, exp, obj);
    }
}
