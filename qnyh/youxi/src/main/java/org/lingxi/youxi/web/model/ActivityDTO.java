package org.lingxi.youxi.web.model;

public class ActivityDTO extends ActivityDTOKey {
    private Short killQ;

    private Short assistQ;

    private Short carryQ;

    private Long outputM;

    private Long cureM;

    private Long bearM;

    private Short corpseQ;

    private Short reliveQ;

    private Short killedQ;

    private Short tankKillQ;

    private String hero;

    private String driver;

    private String gangName;

    public Short getKillQ() {
        return killQ;
    }

    public void setKillQ(Short killQ) {
        this.killQ = killQ;
    }

    public Short getAssistQ() {
        return assistQ;
    }

    public void setAssistQ(Short assistQ) {
        this.assistQ = assistQ;
    }

    public Short getCarryQ() {
        return carryQ;
    }

    public void setCarryQ(Short carryQ) {
        this.carryQ = carryQ;
    }

    public Long getOutputM() {
        return outputM;
    }

    public void setOutputM(Long outputM) {
        this.outputM = outputM;
    }

    public Long getCureM() {
        return cureM;
    }

    public void setCureM(Long cureM) {
        this.cureM = cureM;
    }

    public Long getBearM() {
        return bearM;
    }

    public void setBearM(Long bearM) {
        this.bearM = bearM;
    }

    public Short getCorpseQ() {
        return corpseQ;
    }

    public void setCorpseQ(Short corpseQ) {
        this.corpseQ = corpseQ;
    }

    public Short getReliveQ() {
        return reliveQ;
    }

    public void setReliveQ(Short reliveQ) {
        this.reliveQ = reliveQ;
    }

    public Short getKilledQ() {
        return killedQ;
    }

    public void setKilledQ(Short killedQ) {
        this.killedQ = killedQ;
    }

    public Short getTankKillQ() {
        return tankKillQ;
    }

    public void setTankKillQ(Short tankKillQ) {
        this.tankKillQ = tankKillQ;
    }

    public String getHero() {
        return hero;
    }

    public void setHero(String hero) {
        this.hero = hero == null ? null : hero.trim();
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver == null ? null : driver.trim();
    }

    public String getGangName() {
        return gangName;
    }

    public void setGangName(String gangName) {
        this.gangName = gangName == null ? null : gangName.trim();
    }
}