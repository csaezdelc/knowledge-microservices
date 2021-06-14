package knowledgetest.skills;

import com.intuit.karate.junit5.Karate;

class SkillsRunner {
    

    @Karate.Test
    Karate testAll() {
        return Karate.run("skills").relativeTo(getClass());
    }
    
}
