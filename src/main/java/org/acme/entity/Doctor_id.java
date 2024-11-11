package org.acme.entity;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
public class Doctor_id  {

   // @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id=0;
//   @SequenceGenerator(name = "doctor_id_seq", sequenceName = "doctor_id_sequence", allocationSize = 1)

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
