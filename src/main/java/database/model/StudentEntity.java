package database.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data /* @ToString @NoArgsConstructor @Getter @Setter @EqualsAndHashCode */
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "student", schema = "public", catalog = "college")
public class StudentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "surname", nullable = true)
    private String surname;
    @Basic
    @Column(name = "forename", nullable = true)
    private String forename;
    @Basic
    @Column(name = "email", nullable = true)
    private String email;
    @OneToMany(mappedBy = "studentByStudentId", fetch = FetchType.EAGER)
    private Collection<StudentSubjectEntity> studentSubjects;

    /*
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="student_subject",
            joinColumns = @JoinColumn(
                    name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "subject_id",  referencedColumnName = "id"))
    private Collection<SubjectEntity> subjects;
    */
}
