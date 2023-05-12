package com.bazztech.fileservice.repository;

import com.bazztech.fileservice.domain.entity.File;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author bazz
 * May 07 2023
 * 13:03
 */
@Repository
public interface FileRepository extends MongoRepository<File, Long> {

}
