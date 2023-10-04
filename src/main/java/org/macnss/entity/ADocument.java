package org.macnss.entity;



import org.macnss.Enum.DocumentType;
import org.macnss.Enum.DocumentStatus;

import java.util.Date;

public abstract class ADocument {
    private String id;
    private String title;
    private Date createdAt;
    private DocumentType type = DocumentType.DOCUMENT;
    private DocumentStatus status = DocumentStatus.REFUND;
    private Folder folder;
    private float price;
    private float refund_rate = 0;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getRefund_rate() {
        return refund_rate;
    }

    public void setRefund_rate(float refund_rate) {
        this.refund_rate = refund_rate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public DocumentType getType() {
        return type;
    }

    public void setType(DocumentType type) {
        this.type = type;
    }
    public void setStatus(DocumentStatus status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }
}
