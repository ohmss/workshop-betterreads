package io.javabrains.betterreads.loader;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface AuthorRepository extends CassandraRepository<Author, String>{

}
