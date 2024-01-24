package com.stackroute.FavStock;

import com.stackroute.FavStock.Filter.FavJWTFilter;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableDiscoveryClient
@OpenAPIDefinition(info = @Info(title = "Favourite Stock Microservice", version = "1.0", description = "saves favourite stock country"))
public class FavStockApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavStockApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean getfilter()
	{
		FilterRegistrationBean fbean=new FilterRegistrationBean();
		fbean.setFilter(new FavJWTFilter());
		fbean.addUrlPatterns("/favorite/*");
		return fbean;
	}

}
