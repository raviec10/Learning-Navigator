@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private StudentService studentService;

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Exam getExamById(Long examId) {
        return examRepository.findById(examId)
            .orElseThrow(() -> new RuntimeException("Exam not found"));
    }

    public Exam saveExam(Exam exam) {
        return examRepository.save(exam);
    }

    public Exam registerStudentForExam(Long examId, Long studentId) {
        Exam exam = getExamById(examId);
        Student student = studentService.getStudentById(studentId);
        
        if (!student.getEnrolledSubjects().contains(exam.getSubject())) {
            throw new RuntimeException("Student must be enrolled in the subject before registering for the exam");
        }

        exam.getStudents().add(student);
        student.getRegisteredExams().add(exam);

        studentService.saveStudent(student);
        return examRepository.save(exam);
    }

    public void deleteExam(Long examId) {
        examRepository.deleteById(examId);
    }
}
