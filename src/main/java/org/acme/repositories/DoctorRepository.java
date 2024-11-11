package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.Doctor;
import org.acme.entity.Patient;
import org.jboss.resteasy.annotations.Query;

@ApplicationScoped
public class DoctorRepository implements PanacheRepository<Doctor> {
    public Doctor findByEmail(String email) {
        return find("email", email).firstResult();
    }

}
