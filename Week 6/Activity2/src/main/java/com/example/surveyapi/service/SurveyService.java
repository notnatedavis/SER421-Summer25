/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/3/25
 * 
 * src\main\java\com\example\surveyapi\service\SurveyService.java
 * Logic for Survey Service
 */

@Service
public class SurveyService {
    @Autowired
    private SurveyRepository surveyRepo;

    public List<Survey> getAllSurveys() {
        return surveyRepo.findAll();
    }

    public Survey createSurvey(Survey survey) {
        return surveyRepo.save(survey);
    }

    public Optional<Survey> getSurvey(Long id) {
        return surveyRepo.findById(id);
    }
}