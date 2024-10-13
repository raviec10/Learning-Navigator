@SpringBootTest
@AutoConfigureMockMvc
public class ExamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExamService examService;

    @Test
    public void testRegisterStudentForExam_Success() throws Exception {
        Long examId = 1L;
        Long studentId = 2L;

        Mockito.doNothing().when(examService).registerStudentForExam(examId, studentId);

        mockMvc.perform(post("/exams/{examId}", examId)
                .param("studentId", studentId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("Student registered for the exam successfully."));
    }

    @Test
    public void testRegisterStudentForExam_StudentNotEnrolledInSubject() throws Exception {
        Long examId = 1L;
        Long studentId = 2L;

        Mockito.doThrow(new BadRequestException("Student must be enrolled in the subject before registering for the exam"))
                .when(examService).registerStudentForExam(examId, studentId);

        mockMvc.perform(post("/exams/{examId}", examId)
                .param("studentId", studentId.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Student must be enrolled in the subject before registering for the exam"));
    }
}
