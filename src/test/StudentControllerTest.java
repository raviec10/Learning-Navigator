@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testGetStudent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/students/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists());
    }
}
