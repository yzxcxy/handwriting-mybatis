package com.fqc.datasource.pooled;

import com.fqc.datasource.upooled.UnpooledDataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

public class PooledDataSourceFactory extends UnpooledDataSourceFactory {

    public PooledDataSourceFactory() {
        this.dataSource = new PooledDataSource();
    }
}
