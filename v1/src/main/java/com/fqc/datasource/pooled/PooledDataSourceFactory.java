package com.fqc.datasource.pooled;

import com.fqc.datasource.upooled.UnpooledDataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

public class PooledDataSourceFactory extends UnpooledDataSourceFactory {

    public PooledDataSourceFactory(Properties props) {
        super(props);
    }

    public PooledDataSourceFactory() {
    }

    @Override
    public DataSource getDataSource() {
        PooledDataSource pooledDataSource = new PooledDataSource();
        pooledDataSource.setDriver(props.getProperty("driver"));
        pooledDataSource.setUrl(props.getProperty("url"));
        pooledDataSource.setUsername(props.getProperty("username"));
        pooledDataSource.setPassword(props.getProperty("password"));
        return pooledDataSource;
    }
}
