package org.macnss.entity;

public class Employer {
    private String matriculate;
    private String firstName;
    private String lastName;
    private String birthDay;
    private String salary;

    public String getMatriculate() {
        return matriculate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public void setMatriculate(String matriculate) {
        this.matriculate = matriculate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "Patient {\n" +
                "Matriculate = " + matriculate +
                ", First name = '" + firstName + '\'' +
                ", Last name = '" + lastName + '\'' +
                ", Birth day = '" + birthDay + '\'' +
                ", Salary = '" + salary + '\'' +
                "\n}";
    }
}
