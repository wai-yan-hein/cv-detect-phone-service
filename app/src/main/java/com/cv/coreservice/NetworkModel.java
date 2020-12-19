package com.cv.coreservice;

public class NetworkModel {
    private Integer id;
    private String ipAddress;
    private Integer port;

    public NetworkModel(Integer id, String ipAddress, Integer port) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public NetworkModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
