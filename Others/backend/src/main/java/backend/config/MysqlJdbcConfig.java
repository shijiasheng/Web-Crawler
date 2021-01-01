package backend.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = MysqlJdbcConfig.PACKAGE, sqlSessionFactoryRef = "secondSqlSessionFactory")
public class MysqlJdbcConfig {
    static final String PACKAGE = "backend.dao.mysql";
    static final String MAPPER_LOCATION = "classpath:mapper/mysql/*.xml";


    @Value("${mysql.datasource.url}")
    private String url;

    @Value("${mysql.datasource.username}")
    private String user;

    @Value("${mysql.datasource.password}")
    private String password;

    @Value("${mysql.datasource.driver-class-name}")
    private String driverClass;


    @Bean(name = "mysqlDataSource")
    public DataSource clusterDataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean(name = "secondTransactionManager")
    public DataSourceTransactionManager clusterTransactionManager() {
        return new DataSourceTransactionManager(clusterDataSource());
    }

    @Bean(name = "secondSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("mysqlDataSource") DataSource clusterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(clusterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MysqlJdbcConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
