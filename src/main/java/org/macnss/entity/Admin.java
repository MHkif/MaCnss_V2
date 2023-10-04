package org.macnss.entity;

public class Admin {
    private final String id;
    private final String fullName;
    private final String email;
    private final String password;

    private static Admin instance;

    private Admin(String id, String fullName, String email, String password) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public static synchronized Admin getInstance(String id, String fullName, String email, String password){
        if(instance == null){
            instance = new Admin(id, fullName, email, password);
        }
        return instance;
    }


    public String getId() {
        return id;
    }

    public String getFullName() { return fullName; }

    public String getEmail() { return email; }
    public String getPassword() { return password; }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
