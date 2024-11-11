package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "appointment")
public class Appointment  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long app_id;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "appointment_seq")
//    @SequenceGenerator(name = "appointment_seq", sequenceName = "appointment_sequence", allocationSize = 1)

    //@Temporal(TemporalType.TIMESTAMP)
    private LocalDate appointmentDate;
    private String slot;
    private int cost;
    private String status;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    // Getters and Setters


    public long getApp_id() {
        return app_id;
    }

    public void setApp_id(long app_id) {
        this.app_id = app_id;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Appointment{" +
//                "app_id=" + app_id +
                ", appointmentDate=" + appointmentDate +
                ", slot='" + slot + '\'' +
                ", cost=" + cost +
                ", status='" + status + '\'' +
                ", patient=" + patient +
                ", doctor=" + doctor +
                '}';
    }
}
