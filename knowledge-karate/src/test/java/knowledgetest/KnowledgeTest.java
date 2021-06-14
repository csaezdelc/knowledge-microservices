package knowledgetest;

import com.intuit.karate.junit5.Karate;

public class KnowledgeTest {

	 @Karate.Test
	    Karate testAll() {
	        return Karate.run().relativeTo(getClass());
	    }
	
}
