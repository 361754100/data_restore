package cn.com.nexwise.data_restore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

//@ConfigurationProperties(prefix = "spring.data.mongodb.sourceTemplate")
@Configuration
public class SourceMConfig {

    @Value("${spring.data.mongodb.sourceTemplate.host}")
    private String host;

    @Value("${spring.data.mongodb.sourceTemplate.port}")
    private int port;

    @Value("${spring.data.mongodb.sourceTemplate.database}")
    private String database;

    @Value("${spring.data.mongodb.sourceTemplate.username}")
    private String username;

    @Value("${spring.data.mongodb.sourceTemplate.password}")
    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
