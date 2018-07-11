/**
 * Date:2017年9月27日下午1:25:11
 * Copyright (c) 2017, guooo All Rights Reserved.
 *
*/

package com.twl.springboot.es.service.impl;

import com.twl.springboot.es.model.LogStashData;
import com.twl.springboot.es.repo.LogStashDataRepository;
import com.twl.springboot.es.service.LogStashDataService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:LogStashDataServiceImpl <br/>
 * Function: article es 操作<br/>
 * Date: 2017年9月27日 下午1:25:11 <br/>
 * 
 * @author guooo
 * @version
 * @since JDK 1.6
 * @see
 */
@Service
public class LogStashDataServiceImpl implements LogStashDataService {

    @Override
    public List<LogStashData> search(String content) {
        return null;
    }

    final int page = 0;
    final int size = 10;

    /* 搜索模式 */
    String SCORE_MODE_SUM = "sum"; // 权重分求和模式
    Float MIN_SCORE = 10.0F; // 由于无相关性的分值默认为 1 ，设置权重分最小值为 10

    Pageable pageable = new PageRequest(page, size);

    @Autowired
    LogStashDataRepository repository;

    @Override
    public String saveLogStashData(LogStashData article) {
        LogStashData result = repository.save(article);
        return result.getId();
    }

   @Override
    public void deleteLogStashData(String id) {
        //repository.delete(id);
    }

    @Override
    public LogStashData findLogStashData(String id) {
        return null;
    }

    @Override
    public List<LogStashData> findLogStashDataPageable() {

        return repository.findAll(pageable).getContent();
    }

    @Override
    public List<LogStashData> findLogStashDataAll() {
        Iterable<LogStashData> iterables = repository.findAll();
        List<LogStashData> articles = new ArrayList<>();
        for (LogStashData article : iterables) {
            articles.add(article);
        }
        return articles;
    }

    @Override
    public List<LogStashData> findLogStashDataSort() {
        List<Order> orders = new ArrayList<>();
        Order order = new Order(Direction.ASC, "@timestamp");
        orders.add(order);
        Sort sort = new Sort(orders);
        Iterable<LogStashData> iterables = repository.findAll(sort);
        List<LogStashData> articles = new ArrayList<>();
        for (LogStashData article : iterables) {
            articles.add(article);
        }
        return articles;
    }

    @Override
    public List<LogStashData> like(String content) {
        SearchQuery query = getSearchQuery(content, page, size);
        Iterable<LogStashData> articles = repository.search(query);
        List<LogStashData> list = new ArrayList<>();
        for (LogStashData article : articles) {
            list.add(article);
        }
        return list;
    }

    private SearchQuery getSearchQuery(String searchContent, int page, int size) {
        // 短语匹配到的搜索词，求和模式累加权重分
        // 权重分查询 https://www.elastic.co/guide/cn/elasticsearch/guide/current/function-score-query.html
        //   - 短语匹配 https://www.elastic.co/guide/cn/elasticsearch/guide/current/phrase-matching.html
        //   - 字段对应权重分设置，可以优化成 enum
        //   - 由于无相关性的分值默认为 1 ，设置权重分最小值为 10
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
                .add(QueryBuilders.matchPhraseQuery("log_message", searchContent),
                        ScoreFunctionBuilders.weightFactorFunction(1000))
                .scoreMode(SCORE_MODE_SUM).setMinScore(MIN_SCORE);

        // 分页参数
        Pageable pageable = new PageRequest(page, size);
        return new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(functionScoreQueryBuilder).build();
    }

    /*@Override
    public List<LogStashData> search(String content) {
        return repository.findByAbstractsAndContent(content, content);
    }*/

    @Override
    public String update(String id) {
       /* LogStashData article = repository.findOne(id);
        article.setLog_message("test");
        LogStashData retun = repository.save(article);
        System.out.println(retun.getId()+"更新的数据");
        return retun.getId();*/
       return null;
    }



}
