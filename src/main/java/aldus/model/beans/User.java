package aldus.model.beans;

import java.sql.Date;
import java.sql.Timestamp;

public class User {
    private int id = 0;
    private String name;
    private String password;
    private int role;
    private Date birthDay;
    private Timestamp creationTime;
    private Timestamp lastActivity;
    private String email;

    public User(){
        name = null;
        password = null;
        role =0;
        creationTime  =null;
        lastActivity = null;
        email = null;
    }

    public User(int id, String name, String password, int role, Timestamp creationTime, Timestamp lastActivity, String email,Date birthDay) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.role = role;
        this.creationTime = creationTime;
        this.lastActivity = lastActivity;
        this.birthDay = birthDay;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(Timestamp lastActivity) {
        this.lastActivity = lastActivity;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}
