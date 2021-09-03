package com.task.basicpassengerservice.repositories.filters;

import com.task.basicpassengerservice.models.Company;
import com.task.basicpassengerservice.requests.CompanyRequest;
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
public class CompanyFilterRepositoryImpl implements CompanyFilterRepository{

    private final EntityManager em;

    @Override
    public List<Company> findByFilter(CompanyRequest filter) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Company> cq = cb.createQuery(Company.class);

        Root<Company> root = cq.from(Company.class);
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getName() != null && !filter.getName().isEmpty()) {
            String pattern = String.format("%%%s%%", filter.getName());
            predicates.add(cb.like(root.get("name"), pattern));
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
