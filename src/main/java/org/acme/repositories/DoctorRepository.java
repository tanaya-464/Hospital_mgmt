package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.Doctor;
import org.acme.entity.Patient;

@ApplicationScoped
public class DoctorRepository implements PanacheRepository<Doctor> {
}
