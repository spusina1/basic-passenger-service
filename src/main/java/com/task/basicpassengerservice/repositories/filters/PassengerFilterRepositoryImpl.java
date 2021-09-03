package com.task.basicpassengerservice.repositories.filters;

import com.task.basicpassengerservice.models.Passenger;
import com.task.basicpassengerservice.requests.PassengerRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Repository
public class PassengerFilterRepositoryImpl implements PassengerFilterRepository {

    private final EntityManager em;

    @Override
    public List<Passenger> findByFilter(PassengerRequest filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Passenger> cq = cb.createQuery(Passenger.class);

        Root<Passenger> root = cq.from(Passenger.class);
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getFirstName() != null && !filter.getFirstName().isEmpty()) {
            String pattern = String.format("%%%s%%", filter.getFirstName());
            predicates.add(cb.like(root.get("firstName"), pattern));
        }

        if (filter.getLastName() != null && !filter.getLastName().isEmpty()) {
            String pattern = String.format("%%%s%%", filter.getLastName());
            predicates.add(cb.like(root.get("lastName"), pattern));
        }

        if (filter.getPassportId() != null && !filter.getPassportId().isEmpty()) {
            String pattern = String.format("%%%s%%", filter.getPassportId());
            predicates.add(cb.like(root.get("passportId"), pattern));
        }

        if (filter.getAddress() != null && !filter.getAddress().isEmpty()) {
            String pattern = String.format("%%%s%%", filter.getAddress());
            predicates.add(cb.like(root.get("address"), pattern));
        }

        if (filter.getPhoneNumber() != null && !filter.getPhoneNumber().isEmpty()) {
            String pattern = String.format("%%%s%%", filter.getPhoneNumber());
            predicates.add(cb.like(root.get("phoneNumber"), pattern));
        }

        if (filter.getEmail() != null && !filter.getEmail().isEmpty()) {
            String pattern = String.format("%%%s%%", filter.getEmail());
            predicates.add(cb.like(root.get("email"), pattern));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(cq).getResultList();
    }
}
