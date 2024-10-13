@RestController
@RequestMapping("/hidden-feature")
public class EasterEggController {

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Endpoint to get a random fact about a given number.
     *
     * @param number The number for which to generate a fact.
     * @return A fact about the number.
     */
    @GetMapping("/{number}")
    public ResponseEntity<String> getNumberFact(@PathVariable int number) {
        String url = "http://numbersapi.com/" + number;
        try {
            String response = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve number fact. Please try again.");
        }
    }
}
