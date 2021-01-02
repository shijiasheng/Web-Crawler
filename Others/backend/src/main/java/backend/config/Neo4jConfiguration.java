package backend.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@MapperScan(basePackages = Neo4jConfiguration.PACKAGE,sqlSessionFactoryRef = "Neo4jSessionFactory")
@EnableTransactionManagement
public class Neo4jConfiguration {
    static final String PACKAGE = "backend.dao.neo4j";
    static final String MAPPER_LOCATION = "classpath:mapper/neo4j/*.xml";

    private String url="jdbc:neo4j:bolt://101.132.33.87:7687";

    private String userName="neo4j";

    private String password="123";

    private String DriverClassName="org.neo4j.jdbc.bolt.BoltDriver";


//    @Bean
//    public org.neo4j.ogm.config.Configuration  myUserconfiguration() {
//        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder()
//                .uri(url)
//                .credentials(userName,password)
//                .build();
//        return configuration;
//    }

    @Bean(name = "neo4jDataSource")
    public DataSource neo4jDataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(DriverClassName);
        return dataSource;
    }


    @Bean(name = "Neo4jSessionFactory")
    public SqlSessionFactory neo4jSqlSessionFactory(@Qualifier("neo4jDataSource") DataSource neo4jDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(neo4jDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(Neo4jConfiguration.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
//    @Bean
//    public SessionFactory sessionFactory(){
//        return new SessionFactory(myUserconfiguration(), "com.example.easynotes.models.graph");
//    }


}
