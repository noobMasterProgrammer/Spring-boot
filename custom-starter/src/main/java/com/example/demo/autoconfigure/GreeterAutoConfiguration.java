package com.example.demo.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.library.Greeter;
import com.example.demo.library.GreeterImpl;

@Configuration
@ConditionalOnClass(Greeter.class)
@EnableConfigurationProperties(GreeterProperties.class)
public class GreeterAutoConfiguration {

	@Autowired
    private GreeterProperties greeterProperties;

    @Bean
    @ConditionalOnMissingBean
    public Greeter getDeafaultGreeter() {
    	String companyName=greeterProperties.getCompanyName()==null?"TCS":greeterProperties.getCompanyName();
    	String role=greeterProperties.getRole()==null?"Developer":greeterProperties.getRole();
    	return new GreeterImpl(companyName,role,null,null,null);
    }
}
