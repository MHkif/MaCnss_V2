package org.macnss.entity;

import org.macnss.Enum.FolderStatus;

import java.util.Date;

public class Folder {
    private String id;
    private String name;
    private Date depositDate;
    private FolderStatus status = FolderStatus.WAITING;
    private Patient patient;
    private float total_refund = 0;
    private Agent agent;

    public Folder() {

    }


    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTotal_refund() {
        return total_refund;
    }

    public void setTotal_refund(float total_refund) {
        this.total_refund = total_refund;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(Date depositDate) {
        this.depositDate = depositDate;
    }

    public FolderStatus getStatus() {
        return status;
    }

    public void setStatus(FolderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Folder {\n" +
                "ID = " + id +
                ", Deposit At = " + depositDate +
                ", Status = " + status +
                ", Total Refund = " + total_refund +
                ", Patient = \n" + patient.toString() +
                ", Agent = \n" + agent.toString() +
                "\n}";
    }
}
