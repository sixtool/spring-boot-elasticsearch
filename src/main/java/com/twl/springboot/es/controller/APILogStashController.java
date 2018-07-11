
package com.twl.springboot.es.controller;

import com.twl.springboot.es.model.LogStashData;
import com.twl.springboot.es.repo.LogStashDataRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/logstash")
public class APILogStashController {

    @Autowired
    com.twl.springboot.es.service.LogStashDataService LogStashDataService;

    @Autowired
    LogStashDataRepository logStashDataRepository;

    @RequestMapping(value = "like", method = RequestMethod.POST)
    public List<LogStashData> like(String content) {
        return LogStashDataService.like(content);
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    public Object count(String content) {
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
                .add(QueryBuilders.matchPhraseQuery("log_message", "pushing"),
                        ScoreFunctionBuilders.weightFactorFunction(1000))
                .scoreMode("sum").setMinScore(10.0F);
        // 分页参数
        Pageable pageable = new PageRequest(0, 100);
        SearchQuery query = new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(functionScoreQueryBuilder).build();
        Iterable<LogStashData> logStashDatas = logStashDataRepository.search(query);
        List<LogStashData> list = new ArrayList<>();
        for (LogStashData logStashData : logStashDatas) {
            list.add(logStashData);
        }
        return list;
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public List<LogStashData> search(String content) {
        return LogStashDataService.search(content);
    }

}
