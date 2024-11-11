package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private LocalDate dob;
    private String gender;
    private long phone;
    private String email;
    private String password;
    @OneToMany(mappedBy = "patient", cascade = {CascadeType.PERSIST,CascadeType.ALL}, orphanRemoval = true) // Add orphanRemoval if needed
    private List<Appointment> appointments = new ArrayList<>();
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        appointment.setPatient(this); // Set the booking reference in Passenger}
    }

}