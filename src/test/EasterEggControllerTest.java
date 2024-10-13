@SpringBootTest
@AutoConfigureMockMvc
public class EasterEggControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void testGetNumberFact_Success() throws Exception {
        int number = 5;
        String expectedResponse = "5 is the number of platonic solids.";

        Mockito.when(restTemplate.getForObject("http://numbersapi.com/" + number, String.class))
                .thenReturn(expectedResponse);

        mockMvc.perform(get("/hidden-feature/{number}", number))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }

    @Test
    public void testGetNumberFact_Failure() throws Exception {
        int number = 5;

        Mockito.when(restTemplate.getForObject("http://numbersapi.com/" + number, String.class))
                .thenThrow(new RuntimeException("Service unavailable"));

        mockMvc.perform(get("/hidden-feature/{number}", number))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Failed to retrieve number fact. Please try again."));
    }
}
