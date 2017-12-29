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
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;

/**
 * Created by lizhipeng.
 */
@Configuration
@MapperScan(basePackages = "com.neo.mapper.datasource1", 
    sqlSessionFactoryRef = DataSource1Config.SQL_SESSION_FACTORY_NAME)
@EnableTransactionManagement
public class DataSource1Config {

  public static final String SQL_SESSION_FACTORY_NAME = "sqlSessionFactory1";
  public static final String TX_MANAGER = "Datasour1TxManager";

  @Bean(name = "dataSource1", initMethod = "init", destroyMethod = "close")
  @Primary
  public DataSource dataSource1(Db1Config dbConfig) throws SQLException {
    MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
    mysqlXaDataSource.setUrl(dbConfig.getUrl());
    mysqlXaDataSource.setPassword(dbConfig.getPassword());
    mysqlXaDataSource.setUser(dbConfig.getUsername());
    mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

    AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
    xaDataSource.setXaDataSource(mysqlXaDataSource);
    xaDataSource.setUniqueResourceName("dataSource1");

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
  @Primary
  public SqlSessionFactory sqlSessionFactory1(@Qualifier("dataSource1") DataSource dataSource)
      throws Exception {
    SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
    bean.setDataSource(dataSource);
    return bean.getObject();
  }

  @Bean(name = "ds1SqlSessionTemplate")
  @Primary
  public SqlSessionTemplate ds1SqlSessionTemplate(
      @Qualifier("sqlSessionFactory1") SqlSessionFactory sqlSessionFactory) throws Exception {
    return new SqlSessionTemplate(sqlSessionFactory);
  }

}
