package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class QueryResponse {

    private ResultObj result;

    public ResultObj getResult() {
        return result;
    }

    public void setResult(ResultObj result) {
        this.result = result;
    }

    public QueryResponse(ResultObj result) {
        this.result = result;
    }
}
