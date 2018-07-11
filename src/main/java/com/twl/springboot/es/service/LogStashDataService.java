package com.twl.springboot.es.service;

import com.twl.springboot.es.model.LogStashData;

import java.util.List;
public interface LogStashDataService {
    String saveLogStashData(LogStashData article);
    void deleteLogStashData(String id);
    LogStashData findLogStashData(String id);
    List<LogStashData> findLogStashDataPageable();
    List<LogStashData> findLogStashDataAll();
    List<LogStashData> findLogStashDataSort();
    List<LogStashData> like(String content);
    List<LogStashData> search(String content);
    String update(String id);

}
