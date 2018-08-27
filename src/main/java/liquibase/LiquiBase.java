package liquibase;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;

public class LiquiBase {
    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:changeLog.xml");
        //liquibase.setDataSource(dataSource());
        return liquibase;
    }
}



