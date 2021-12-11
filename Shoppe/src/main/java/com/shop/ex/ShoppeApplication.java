package com.shop.ex;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.shop.ex.config.StorageProperties;
import com.shop.ex.services.StorageServices;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class ShoppeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppeApplication.class, args);
	}
  
	@Bean
  CommandLineRunner init(StorageServices services) {
	  return(args->{
		  services.init();
	  });
  }
}
