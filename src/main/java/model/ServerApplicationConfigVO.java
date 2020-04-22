package model;

public class ServerApplicationConfigVO {
    private String ip;
    private Integer port;
    private String defaultPath;
    private Integer erkUsuario;
    private Integer erkFilial;
    private Integer timeOutRequest;


    public ServerApplicationConfigVO(String ip, Integer port, String defaultPath, Integer erkUsuario, Integer erkFilial, Integer timeOutRequest) {
        this.ip = ip;
        this.port = port;
        this.defaultPath = defaultPath;
        this.erkUsuario = erkUsuario;
        this.erkFilial = erkFilial;
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

    public Integer getErkUsuario() {
        return erkUsuario;
    }

    public void setErkUsuario(Integer erkUsuario) {
        this.erkUsuario = erkUsuario;
    }

    public Integer getErkFilial() {
        return erkFilial;
    }

    public void setErkFilial(Integer erkFilial) {
        this.erkFilial = erkFilial;
    }

    public Integer getTimeOutRequest() {
        return timeOutRequest;
    }

    public void setTimeOutRequest(Integer timeOutRequest) {
        this.timeOutRequest = timeOutRequest;
    }
}
