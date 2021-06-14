package knowledgetest.users;

import com.intuit.karate.junit5.Karate;

class UsersRunner {
    

    @Karate.Test
    Karate testAll() {
        return Karate.run("users").relativeTo(getClass());
    }
    
}
