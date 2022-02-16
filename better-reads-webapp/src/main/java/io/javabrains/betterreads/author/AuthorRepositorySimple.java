package io.javabrains.betterreads.author;

import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.mapping.CassandraPersistentEntity;
import org.springframework.data.cassandra.repository.support.MappingCassandraEntityInformation;
import org.springframework.data.cassandra.repository.support.SimpleCassandraRepository;

import com.datastax.oss.driver.api.core.CqlSession;

public class AuthorRepositorySimple extends SimpleCassandraRepository<Author, String> {
    
    protected final CqlSession cqlSession;
    
    protected final CassandraOperations cassandraTemplate;
    
    @SuppressWarnings("unchecked")
    public AuthorRepositorySimple(CqlSession cqlSession, CassandraOperations ops) {
        super(new MappingCassandraEntityInformation<Author, String>(
                (CassandraPersistentEntity<Author>) ops.getConverter().getMappingContext()
                .getRequiredPersistentEntity(Author.class), ops.getConverter()), ops);
        this.cqlSession = cqlSession;
        this.cassandraTemplate = ops;
    }
    

}
