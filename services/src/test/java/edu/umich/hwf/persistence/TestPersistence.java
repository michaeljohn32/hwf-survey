package edu.umich.hwf.persistence;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TestPersistence {
    private Persistence persistence;

    static final String question1="Q1";
    static final String question2="Q2";
    static final String questionWrong="No such question";


    static final int numQ=2;  //number of questions
    static final int numQ1A=3; //number of possible answers for question 1
    static final int numQ2A=2;  // number of possible answers for question 2

    static final int numQ1A1=2;  //count of responses of type A1 for Question 1
    static final int numQ1A2=3;  //count of responses of type A2 for Question 1
    static final int numQ1A3=3;  //count of responses of type A3 for Question 1

    static final int numQ2A1=1;  //count of responses of type A1 for Question 2
    static final int numQ2A2=2;   //count of responses of type A2 for Question 2

    static final String question1Answer1="a";
    static final String question1Answer2="b";
    static final String question1Answer3="c";
    static final String question2Answer1="Yes";
    static final String question2Answer2="No";
    static final String answerWrong="No such answer";


    // Create an "empty" database, populate with question/answer pairs, simulate a few responses
    @Before
    public void Setup() {
//        System.out.println("Setup() called");
        persistence = new MockStorage();
        persistence.registerQuestionAndAnswer(question1, question1Answer1);
        persistence.registerQuestionAndAnswer(question1, question1Answer2);
        persistence.registerQuestionAndAnswer(question1, question1Answer3);
        persistence.registerQuestionAndAnswer(question2, question2Answer1);
        persistence.registerQuestionAndAnswer(question2, question2Answer2);

        //simulating 2 responses of answer 1 for question1
        persistence.persistQuestion(question1, question1Answer1, false);
        persistence.persistQuestion(question1, question1Answer1, false);

        //simulating 3 responses of answer 2 for question1
        persistence.persistQuestion(question1, question1Answer2, false);
        persistence.persistQuestion(question1, question1Answer2, false);
        persistence.persistQuestion(question1, question1Answer2, false);

        //simulating 3 responses of answer 3 for question1
        persistence.persistQuestion(question1, question1Answer3, false);
        persistence.persistQuestion(question1, question1Answer3, false);
        persistence.persistQuestion(question1, question1Answer3, false);

        //simulating 1 response of answer1 for question 2
        persistence.persistQuestion(question2, question2Answer1, false);

        //simulating 2 response of answer2 for question 2
        persistence.persistQuestion(question2, question2Answer2, false);
        persistence.persistQuestion(question2, question2Answer2, false);
    }

    @Test
    public void addQuestionAnswer(){
        persistence.registerQuestionAndAnswer(questionWrong, answerWrong);
        persistence.registerQuestionAndAnswer(questionWrong, answerWrong);
    }

    @Test
    public void persistQuestion(){
        assertFalse(persistence.persistQuestion(questionWrong, answerWrong, false));
        assertFalse(persistence.persistQuestion(question1, answerWrong, false));
        assertTrue(persistence.persistQuestion(question1, question1Answer1, false));
    }

    @Test
    public void getQuestionResults() {
        Map<String,Integer> answers=persistence.getQuestionResults(question1);
        assertEquals(numQ1A, answers.size());
        Integer count=answers.get(question1Answer1);
        assertEquals(numQ1A1, count.intValue());
        count=answers.get(question1Answer2);
        assertEquals(numQ1A2, count.intValue());

        answers=persistence.getQuestionResults(question2);
        assertEquals(numQ2A, answers.size());
        count=answers.get(question2Answer1);
        assertEquals(numQ2A1, count.intValue());
        count=answers.get(question2Answer2);
        assertEquals(numQ2A2, count.intValue());

        answers=persistence.getQuestionResults(questionWrong);
        assertNull(answers);
    }

    @Test
    public void getFullResults() {
        Map<String, HashMap<String, Integer>> allRes=persistence.getFullResults();
        assertEquals(numQ, allRes.size());

        Map<String, Integer> q1res=allRes.get(question1);
        assertEquals(numQ1A, q1res.size());
        assertEquals(numQ1A1, q1res.get(question1Answer1).intValue());
        assertEquals(numQ1A2, q1res.get(question1Answer2).intValue());

        Map<String, Integer> q2res=allRes.get(question2);
        assertEquals(numQ2A, q2res.size());
        assertEquals(numQ2A1, q2res.get(question2Answer1).intValue());
        assertEquals(numQ2A2, q2res.get(question2Answer2).intValue());
    }

//	private static void printQuestionResults(String name){
//
//		Map<String, Integer> q1 = persistence.getQuestionResults(name);
//		Set<String> keys = q1.keySet();
//		for (String key : keys ) {
//			System.out.println(key + "= " + q1.get(key));
//		}
//	}
}