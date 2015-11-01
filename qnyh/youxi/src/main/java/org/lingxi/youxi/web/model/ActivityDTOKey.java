package org.lingxi.youxi.web.model;

public class ActivityDTOKey {
    private String ywrId;

    private Integer gangId;

    private Integer objId;

    public String getYwrId() {
        return ywrId;
    }

    public void setYwrId(String ywrId) {
        this.ywrId = ywrId == null ? null : ywrId.trim();
    }

    public Integer getGangId() {
        return gangId;
    }

    public void setGangId(Integer gangId) {
        this.gangId = gangId;
    }

    public Integer getObjId() {
        return objId;
    }

    public void setObjId(Integer objId) {
        this.objId = objId;
    }
}