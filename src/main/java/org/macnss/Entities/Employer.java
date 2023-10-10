package org.macnss.Entities;

import org.macnss.Enum.EmployerStatus;

import java.util.Date;

public class Employer {
    private String matriculate;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date birthDay;
    private String salary;

    private EmployerStatus status = EmployerStatus.EMPLOYED;


    public String getMatriculate() {
        return matriculate;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
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

    public EmployerStatus getStatus() {
        return status;
    }

    public void setStatus(EmployerStatus status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
