package com.fqc.builder;

import com.fqc.session.Configuration;

public class BaseBuilder {
    protected Configuration configuration;
    public BaseBuilder(Configuration config) {
        this.configuration=config;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
