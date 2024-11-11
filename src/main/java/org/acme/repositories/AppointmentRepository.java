package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.Appointment;
import org.acme.entity.Doctor;

@ApplicationScoped
public class AppointmentRepository implements PanacheRepository<Appointment> {
}
