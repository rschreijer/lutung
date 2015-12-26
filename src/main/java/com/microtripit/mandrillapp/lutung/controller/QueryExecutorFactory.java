package com.microtripit.mandrillapp.lutung.controller;

import com.google.common.base.Preconditions;
import com.microtripit.mandrillapp.lutung.http.MandrillService;

/**
 * Created by yotam on 12/25/15.
 */
public class QueryExecutorFactory {
    private final MandrillService mandrillService;

    public QueryExecutorFactory(final MandrillService mandrillService) {
        this.mandrillService = Preconditions.checkNotNull(mandrillService, "mandrillService is null");
    }

    public QueryExecutor create() {
        return new QueryExecutor(mandrillService);
    }
}
