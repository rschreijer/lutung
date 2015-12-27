package com.microtripit.mandrillapp.lutung.controller;

import com.microtripit.mandrillapp.lutung.http.MandrillService;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Created by yotam on 12/25/15.
 */
public class QueryExecutor {
    private String path;
    private Map<String, Object> params = new HashMap<String, Object>();
    private final MandrillService mandrillService;

    public QueryExecutor(final MandrillService mandrillService) {
        this.mandrillService = mandrillService;
    }

    public QueryExecutor path(final String path) {
        this.path = path;
        return this;
    }

    public QueryExecutor addParam(final String key, final Object value) {
        params.put(key, value);
        return this;
    }

    public QueryExecutor addParamIfNotNull(final String key, final Object value) {
        if (null != value) params.put(key, value);
        return this;
    }

    public QueryExecutor delegate(final Consumer<QueryExecutor> delegated) {
        delegated.accept(this);
        return this;
    }

    public <OUT> OUT execute(final Class<OUT> clazz) throws IOException, MandrillApiError {
        return mandrillService.query(path, params, clazz);
    }
}
