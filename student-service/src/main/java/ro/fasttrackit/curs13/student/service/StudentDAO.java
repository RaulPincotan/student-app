package ro.fasttrackit.curs13.student.service;

import org.springframework.stereotype.Repository;
import ro.fasttrackit.curs13.student.model.StudentEntity;
import ro.fasttrackit.curs13.student.model.StudentFilter;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Repository
public class StudentDAO {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public StudentDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
    }


    public List<StudentEntity> getStudents(StudentFilter studentFilter) {
        CriteriaQuery<StudentEntity> criteria = criteriaBuilder.createQuery(StudentEntity.class);
        Root<StudentEntity> root = criteria.from(StudentEntity.class);

        List<Predicate> whereClause = getClauses(studentFilter, root);

        CriteriaQuery<StudentEntity> query = criteria.select(root).where(whereClause.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }

    private List<Predicate> getClauses(StudentFilter studentFilter, Root<StudentEntity> studentRoot) {
        List<Predicate> clauses = new ArrayList<>();
        ofNullable(studentFilter.getFirstName())
                .ifPresent(firstName -> clauses.add(criteriaBuilder.like(studentRoot.get("%firstName%"), firstName)));

        ofNullable(studentFilter.getLastName())
                .ifPresent(lastName -> clauses.add(criteriaBuilder.like(studentRoot.get("%lastName%"), lastName)));

        ofNullable(studentFilter.getYear())
                .ifPresent(year -> clauses.add(criteriaBuilder.equal(studentRoot.get("year"), year)));

        ofNullable(studentFilter.getAddress())
                .ifPresent(address -> clauses.add(criteriaBuilder.like(studentRoot.get("%address%"), address)));

        return clauses;
    }
}
