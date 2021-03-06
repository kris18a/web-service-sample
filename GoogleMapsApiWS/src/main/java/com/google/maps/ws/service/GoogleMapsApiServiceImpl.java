package com.google.maps.ws.service;

import com.google.maps.ws.exceptions.InvalidConfigurationException;
import com.google.maps.ws.model.PlaceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by X on 31/05/2016.
 */
@Service("googleMapsApiService")
public class GoogleMapsApiServiceImpl implements GoogleMapsApiService {

    @Autowired
    @Qualifier("googleApiBaseUrl")
    private String url;

    @Autowired
    @Qualifier("googleApiKey")
    private String apiKey;

    @Override
    public PlaceResponse searchPlaces(final String placeType,
                                      final String latitude,
                                      final String longitude)
            throws IOException, URISyntaxException {

        //validate parameters
        if(StringUtils.isEmpty(getUrl())) {
            throw new InvalidConfigurationException("URL in configuration file is null or empty");
        }
        if(StringUtils.isEmpty(getApiKey())) {
            throw new InvalidConfigurationException("ApiKey in configuration file is null or empty");
        }
        final MessageFormat mFormat = new MessageFormat(getUrl());
        final Object[] params = {latitude, longitude, placeType, getApiKey()};
        URI uri = new URI(mFormat.format(params));
        final RestTemplate tmpl = new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJackson2HttpMessageConverter());
        tmpl.setMessageConverters(converters);
        PlaceResponse response = tmpl.getForObject(uri, PlaceResponse.class);
        return response;
    }

    public String getUrl() {
        return url;
    }

    public String getApiKey() {
        return apiKey;
    }
}
