@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;
    
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToMany(mappedBy = "registeredExams")
    private List<Student> students = new ArrayList<>();

    // Getters and Setters
    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
