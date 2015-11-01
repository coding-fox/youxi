package org.lingxi.youxi.web.model;

import java.util.Date;

public class GangActivityRelDTO extends GangActivityRelDTOKey {
    private Byte duration;

    private Short fightCount;

    private Short deadCount;

    private Date createTime;

    public Byte getDuration() {
        return duration;
    }

    public void setDuration(Byte duration) {
        this.duration = duration;
    }

    public Short getFightCount() {
        return fightCount;
    }

    public void setFightCount(Short fightCount) {
        this.fightCount = fightCount;
    }

    public Short getDeadCount() {
        return deadCount;
    }

    public void setDeadCount(Short deadCount) {
        this.deadCount = deadCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}