package com.microtripit.mandrillapp.lutung.http;

import com.microtripit.mandrillapp.lutung.model.MandrillApiError;

import java.io.IOException;
import java.util.Map;

/**
 * Created by yotam on 12/25/15.
 */
public interface Dispatcher {
    <V> V send(final String url, final Map<String,Object> params, Class<V> responseType) throws IOException, MandrillApiError;
}
