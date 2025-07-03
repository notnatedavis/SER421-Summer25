/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/3/25
 * 
 * src\main\java\com\example\surveyapi\model\Question.java
 * JPA Entities
 */

@Entity
public class Question {
    @Id @GeneratedValue
    private Long id;
    private String text;
    private String type;

    @ManyToOne
    private Survey survey;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();
}
