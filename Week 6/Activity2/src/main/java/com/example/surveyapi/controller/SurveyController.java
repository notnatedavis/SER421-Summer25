/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/3/25
 * 
 * src\main\java\com\example\surveyapi\controller\SurveyController.java
 * REST Controller for Survey
 */

@RestController
@RequestMapping("/api/surveys")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @GetMapping
    public List<Survey> getAll() {
        return surveyService.getAllSurveys();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Survey> getOne(@PathVariable Long id) {
        return surveyService.getSurvey(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Survey> create(@RequestBody Survey survey) {
        Survey created = surveyService.createSurvey(survey);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}