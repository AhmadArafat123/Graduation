package com.test.testpro.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableRetry
@ConfigurationProperties("springit")
public class SpringitProperties {
    /**
     * This is our Conf message
     */


    private String welcome="Hello, world";

    public String getWelcome() {
        return welcome;
    }

    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }
//    @Bean
//    public DataSource dataSource() {
//        return new MysqlDataSource(); // (1)
//    }

}
