@RestController
@RequestMapping("/exams")
public class ExamController {
    @Autowired
    private ExamService examService;

    @GetMapping
    public List<Exam> getAllExams() {
        return examService.getAllExams();
    }

    @GetMapping("/{id}")
    public Exam getExamById(@PathVariable Long id) {
        return examService.getExamById(id);
    }

    @PostMapping
    public Exam createExam(@RequestBody Exam exam) {
        return examService.saveExam(exam);
    }

    @PostMapping("/{examId}/register/{studentId}")
    public Exam registerStudentForExam(@PathVariable Long examId, @PathVariable Long studentId) {
        try {
            examService.registerStudentForExam(examId, studentId);
            return ResponseEntity.ok("Student registered for the exam successfully.");
        } catch (ResourceNotFoundException | BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
    }
}
