
package com.twl.springboot.es.repo;

import com.twl.springboot.es.model.LogStashData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LogStashDataRepository extends ElasticsearchRepository<LogStashData, Long> {

}

