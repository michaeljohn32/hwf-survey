package edu.umich.hwf.persistence;

import edu.umich.hwf.persistence.MockStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestPersistence {
	GooglePersistence persistence;

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
        assertTrue(persistence.addQuestionAnswer(question1, question1Answer1));
        assertTrue(persistence.addQuestionAnswer(question1, question1Answer2));
        assertTrue(persistence.addQuestionAnswer(question1, question1Answer3));
        assertTrue(persistence.addQuestionAnswer(question2, question2Answer1));
        assertTrue(persistence.addQuestionAnswer(question2, question2Answer2));

        //simulating 2 responses of answer 1 for question1
        persistence.persistQuestion(question1, question1Answer1);
        persistence.persistQuestion(question1, question1Answer1);

        //simulating 3 responses of answer 2 for question1
        persistence.persistQuestion(question1, question1Answer2);
        persistence.persistQuestion(question1, question1Answer2);
        persistence.persistQuestion(question1, question1Answer2);

        //simulating 3 responses of answer 3 for question1
        persistence.persistQuestion(question1, question1Answer3);
        persistence.persistQuestion(question1, question1Answer3);
        persistence.persistQuestion(question1, question1Answer3);

        //simulating 1 response of answer1 for question 2
        persistence.persistQuestion(question2, question2Answer1);

        //simulating 2 response of answer2 for question 2
        persistence.persistQuestion(question2, question2Answer2);
        persistence.persistQuestion(question2, question2Answer2);
    }

    @Test
    public void addQuestionAnswer(){
        assertTrue(persistence.addQuestionAnswer(questionWrong, answerWrong));
        assertTrue(persistence.addQuestionAnswer(questionWrong, answerWrong));
    }

    @Test
    public void persistQuestion(){
        assertFalse(persistence.persistQuestion(questionWrong, answerWrong));
        assertFalse(persistence.persistQuestion(question1, answerWrong));
        assertTrue(persistence.persistQuestion(question1, question1Answer1));
    }


    @Test
    public void getQuestionResults() {
        Map<String,Integer> answers=persistence.getQuestionResults(this.question1);
        assertEquals(numQ1A, answers.size());
        Integer count=answers.get(question1Answer1);
        assertEquals(numQ1A1, count.intValue());
        count=answers.get(question1Answer2);
        assertEquals(numQ1A2, count.intValue());

        answers=persistence.getQuestionResults(this.question2);
        assertEquals(numQ2A, answers.size());
        count=answers.get(question2Answer1);
        assertEquals(numQ2A1, count.intValue());
        count=answers.get(question2Answer2);
        assertEquals(numQ2A2, count.intValue());

        answers=persistence.getQuestionResults(this.questionWrong);
        assertNull(answers);
    }

    @Test
    public void getFullResults() {
        Map<String, HashMap<String, Integer>> allRes=persistence.getFullResults();
        assertEquals(numQ, allRes.size());

        Map<String, Integer> q1res=allRes.get(question1);
        assertEquals(numQ1A, q1res.size());
        assertEquals(this.numQ1A1, q1res.get(this.question1Answer1).intValue());
        assertEquals(this.numQ1A2, q1res.get(this.question1Answer2).intValue());

        Map<String, Integer> q2res=allRes.get(question2);
        assertEquals(numQ2A, q2res.size());
        assertEquals(this.numQ2A1, q2res.get(this.question2Answer1).intValue());
        assertEquals(this.numQ2A2, q2res.get(this.question2Answer2).intValue());
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