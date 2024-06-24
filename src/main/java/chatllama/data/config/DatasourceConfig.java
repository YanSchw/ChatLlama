package chatllama.data.config;

import chatllama.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder
                .create()
                .url(Config.getMySqlHostURL())
                .username(Config.getMySqlUsername())
                .password(Config.getMySqlPassword())
                .build();
    }

}
