package io.javabrains.betterreads;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.datastax.astra.sdk.AstraClient;
import com.datastax.astra.sdk.databases.domain.Database;
import com.datastax.oss.driver.api.core.CqlSession;

@SpringBootTest
public class Test01_Connectivity {

    @Autowired
    private AstraClient astraClient;
    
    @Autowired
    private CqlSession cqlSession;
    
    @Test
    void should_display_cqlSession() {
        System.out.println("== CQL_SESSION ==");
        Assertions.assertTrue(cqlSession.getKeyspace().isPresent());
        System.out.println("+ Your Keyspace: " + cqlSession.getKeyspace().get());
    }
    
    @Test
    void should_display_astraClient() {
        System.out.println("== ASTRA ==");
        System.out.println("+ Your OrganizationID: " + astraClient.apiDevopsOrganizations().organizationId());
        System.out.println("+ Your Databases: ");
        astraClient.apiDevopsDatabases()
                   .databasesNonTerminated()
                   .forEach(this::displayDB);
    }
    
    private void displayDB(Database db) {
        System.out.println(db.getInfo().getName() + "\t : id=" + db.getId() + ", region=" + db.getInfo().getRegion());
    }
    
}