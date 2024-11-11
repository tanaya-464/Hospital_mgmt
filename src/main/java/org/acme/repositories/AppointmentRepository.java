package org.acme.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.acme.entity.Appointment;
import org.acme.entity.Doctor;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class AppointmentRepository implements PanacheRepository<Appointment> {
    @Transactional
    public List<String> findAvailableSlots(Long doctorId, LocalDate date) {
        // Define the JPQL query
        String jpql = "SELECT a.slot FROM Appointment a WHERE a.doctor.id = :doctorId AND a.appointmentDate = :date " +
                "GROUP BY a.slot HAVING COUNT(a.slot) < 5";

        // Create the query
        Query query =  getEntityManager().createQuery(jpql);
        query.setParameter("doctorId", doctorId);
        query.setParameter("date", date);

        // Execute the query and return the result
        return query.getResultList();
    }
}
