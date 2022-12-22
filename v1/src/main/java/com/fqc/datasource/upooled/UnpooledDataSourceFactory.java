package com.fqc.datasource.upooled;

import com.fqc.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 无池化的数据源工厂
 */
public class UnpooledDataSourceFactory implements DataSourceFactory {
    protected Properties props;

    public UnpooledDataSourceFactory(Properties props) {
        this.props = props;
    }

    public UnpooledDataSourceFactory() {
    }

    @Override
    public void setProperties(Properties props) {
        this.props=props;
    }

    @Override
    public DataSource getDataSource() {
        UnpooledDataSource unpooledDataSource = new UnpooledDataSource();
        unpooledDataSource.setDriver(props.getProperty("driver"));
        unpooledDataSource.setUrl(props.getProperty("url"));
        unpooledDataSource.setUsername(props.getProperty("username"));
        unpooledDataSource.setPassword(props.getProperty("password"));
        return unpooledDataSource;
    }
}
