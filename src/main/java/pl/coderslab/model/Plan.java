package pl.coderslab.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Plan {
    private int id;
    private String name;
    private String description;
    private LocalDateTime created;
    private int admin_id;

    public Plan() {
    }

    public Plan(String name, String description, LocalDateTime created) {
        this.name = name;
        this.description = description;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    @Override
    public String toString() {
        return "Plan [id=" + id + "," +
                " name=" + name + "," +
                " description=" + description + "," +
                " created=" + created + "," +
                " admin_id=" + admin_id +"]";
    }
}
