package com.neo.config;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

/**
 * Created by summer on 2016/11/25.
 */
@Configuration
@MapperScan(basePackages = "com.neo.mapper.datasource2",
    sqlSessionFactoryRef = DataSource2Config.SQL_SESSION_FACTORY_NAME)
@EnableTransactionManagement
public class DataSource2Config {

  public static final String SQL_SESSION_FACTORY_NAME = "sqlSessionFactory2";

  @Bean(name = "dataSource2", initMethod = "init", destroyMethod = "close")
  public DataSource dataSource2(Db2Config dbConfig) throws SQLException {
    MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
    mysqlXaDataSource.setUrl(dbConfig.getUrl());
    mysqlXaDataSource.setPassword(dbConfig.getPassword());
    mysqlXaDataSource.setUser(dbConfig.getUsername());
    mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

    AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
    xaDataSource.setXaDataSource(mysqlXaDataSource);
    xaDataSource.setUniqueResourceName("DataSource2");

    xaDataSource.setMinPoolSize(dbConfig.getMinPoolSize());
    xaDataSource.setMaxPoolSize(dbConfig.getMaxPoolSize());
    xaDataSource.setMaxLifetime(dbConfig.getMaxLifetime());
    xaDataSource.setBorrowConnectionTimeout(dbConfig.getBorrowConnectionTimeout());
    xaDataSource.setLoginTimeout(dbConfig.getLoginTimeout());
    xaDataSource.setMaintenanceInterval(dbConfig.getMaintenanceInterval());
    xaDataSource.setMaxIdleTime(dbConfig.getMaxIdleTime());
    xaDataSource.setTestQuery(dbConfig.getTestQuery());
    return xaDataSource;
  }

  @Bean(name = SQL_SESSION_FACTORY_NAME)
  public SqlSessionFactory sqlSessionFactory2(@Qualifier("dataSource2") DataSource dataSource)
      throws Exception {
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource(dataSource);
    return bean.getObject();
  }

  @Bean(name = "ds2SqlSessionTemplate")
  public SqlSessionTemplate ds2SqlSessionTemplate(
      @Qualifier("sqlSessionFactory2") SqlSessionFactory sqlSessionFactory) {
    return new SqlSessionTemplate(sqlSessionFactory);
  }

}
