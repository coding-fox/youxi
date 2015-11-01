package org.lingxi.youxi.web.model;

public class GangActivityRelDTOKey {
    private Integer gangId;

    private String ywrId;

    public Integer getGangId() {
        return gangId;
    }

    public void setGangId(Integer gangId) {
        this.gangId = gangId;
    }

    public String getYwrId() {
        return ywrId;
    }

    public void setYwrId(String ywrId) {
        this.ywrId = ywrId == null ? null : ywrId.trim();
    }
}