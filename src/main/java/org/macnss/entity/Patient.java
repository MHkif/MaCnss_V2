package org.macnss.entity;

public class Patient {
    private String matricule;
    private String fullName;

    public String getMatriculate() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Patient {\n" +
                "matriculate = " + matricule +
                ", fullName = '" + fullName + '\'' +
                "\n}";
    }
}
