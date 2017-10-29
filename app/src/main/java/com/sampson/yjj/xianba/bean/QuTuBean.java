package com.sampson.yjj.xianba.bean;

/**
 * Created by yjj on 2017/10/29.
 */

public class QuTuBean {
    private String content;
    private String hashId;
    private String updatetime;
    private String unixitime;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public String getHashId() {
        return hashId;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public String getUnixitime() {
        return unixitime;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public void setUnixitime(String unixitime) {
        this.unixitime = unixitime;
    }
}
