
package com.twl.springboot.es.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;


@Data
@Document(indexName = "logstash-2018.07.10", type = "logs")
public class LogStashData implements Serializable {

    private String log_message;
    private String level;
    private String service_name;
    @Id
    private String id;

    public String getLog_message() {
        return log_message;
    }

    public void setLog_message(String log_message) {
        this.log_message = log_message;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
