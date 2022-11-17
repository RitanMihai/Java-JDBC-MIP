package database.model;

import lombok.Data;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "subject", schema = "public", catalog = "college")
public class SubjectEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "name", nullable = true, length = -1)
    private String name;
    @Basic
    @Column(name = "abbreviation", nullable = true, length = -1)
    private String abbreviation;
    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;
    @OneToMany(mappedBy = "subjectBySubjectId")
    private Collection<StudentSubjectEntity> studentSubjectsById;
}
