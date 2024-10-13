@RestController
@RequestMapping("/hidden-feature")
public class EasterEggController {

    @GetMapping("/{number}")
    public ResponseEntity<String> getNumberFact(@PathVariable int number) {
        // Use a Numbers API client or external library to get a fact about the number
        String fact = "Number " + number + " is interesting!";
        return ResponseEntity.ok(fact);
    }
}
