package ro.fasttrackit.curs13.student.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private int age;

    /*
     @Column(name = "student_id", nullable = false, unique = true)
    private String studentId;

    @Column(name = "first_name", nullable = false)
    @Size(min = 2, max = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Size(min = 2, max = 50)
    private String lastName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "year")
    private Integer year;

    @Column(name = "year")
    private String address;

    @PrePersist
    protected void onCreate() {
        this.studentId = new StringBuilder().append(firstName, 0, 2)
                .append(lastName, 0, 2)
                .append(age)
                .toString();
    }
     */

}
