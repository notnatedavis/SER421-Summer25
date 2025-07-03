/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/3/25
 * 
 * src\main\java\com\example\surveyapi\model\Survey.java
 * JPA Entities
 */

@Entity
public class Survey {
    @Id @GeneratedValue
    private Long id;
    private String title;
    private String description;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();
}
