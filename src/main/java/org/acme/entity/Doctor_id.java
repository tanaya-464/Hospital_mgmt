package org.acme.entity;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
public class Doctor_id extends PanacheEntity{

   // @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long doctor_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(long doctor_id) {
        this.doctor_id = doctor_id;
    }
}
