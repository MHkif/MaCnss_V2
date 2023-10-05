package org.macnss.entity;

import org.macnss.Enum.DocumentType;

public class Medicine extends ADocument {

    private String codeBar;

    public String getCodeBar() {
        return codeBar;
    }

    public void setCodeBar(String codeBar) {
        this.codeBar = codeBar;
    }
}
