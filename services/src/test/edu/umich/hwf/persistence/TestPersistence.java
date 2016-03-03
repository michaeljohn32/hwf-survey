package edu.umich.hwf.persistence;

import edu.umich.hwf.persistence.MockStorage;

import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestPersistence {
	static GooglePersistence persistence = new MockStorage();
	
    @Before
    public void Setup() {
		persistence.persistQuestion("Q1", "a");
		persistence.persistQuestion("Q1", "b");
		persistence.persistQuestion("Q1", "a");
		persistence.persistQuestion("Q1", "b");
		persistence.persistQuestion("Q2", "Yes");
		persistence.persistQuestion("Q2", "No");
	}

    @Test
    public void storeQuestion(){
        boolean ok=persistence.persistQuestion("Fake Q", "something");
        assertTrue(ok);
    }

	private static void printQuestionResults(String name){
		
		Map<String, Integer> q1 = persistence.getQuestionResults(name);
		Set<String> keys = q1.keySet();
		for (String key : keys ) {
			System.out.println(key + "= " + q1.get(key));
		}
	}
}