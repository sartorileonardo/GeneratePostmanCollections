package model;

public class ServerApplicationConfigVO {
    private String ip;
    private Integer port;
    private String defaultPath;
    private Integer usuario;
    private Integer filial;
    private Integer timeOutRequest;


    public ServerApplicationConfigVO(String ip, Integer port, String defaultPath, Integer usuario, Integer filial, Integer timeOutRequest) {
        this.ip = ip;
        this.port = port;
        this.defaultPath = defaultPath;
        this.usuario = usuario;
        this.filial = filial;
        this.timeOutRequest = timeOutRequest;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDefaultPath() {
        return defaultPath;
    }

    public void setDefaultPath(String defaultPath) {
        this.defaultPath = defaultPath;
    }

    public Integer getusuario() {
        return usuario;
    }

    public void setusuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Integer getfilial() {
        return filial;
    }

    public void setfilial(Integer filial) {
        this.filial = filial;
    }

    public Integer getTimeOutRequest() {
        return timeOutRequest;
    }

    public void setTimeOutRequest(Integer timeOutRequest) {
        this.timeOutRequest = timeOutRequest;
    }
}
