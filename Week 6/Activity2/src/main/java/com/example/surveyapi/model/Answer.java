/*
 * SER421-Summer25
 * Lab 6 , Activity 2
 * ndavispe , 7/3/25
 * 
 * src\main\java\com\example\surveyapi\model\Answer.java
 * JPA Entities
 */

@Entity
public class Answer {
    @Id @GeneratedValue
    private Long id;
    private String response;

    @ManyToOne
    private Question question;
}