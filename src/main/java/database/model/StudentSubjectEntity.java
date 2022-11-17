package database.model;

import javax.persistence.*;

@Entity
@Table(name = "student_subject", schema = "public", catalog = "college")
public class StudentSubjectEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "final_grade", nullable = true)
    private Integer finalGrade;
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private StudentEntity studentByStudentId;
    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private SubjectEntity subjectBySubjectId;

    @Override
    public String toString() {
        return "Grade {" +
                "value=" + finalGrade +
                ", student id =" + studentByStudentId.getId() +
                ", subject name =" + subjectBySubjectId.getName() +
                '}';
    }
}
