package com.distanceOptimizer;

import com.distanceOptimizer.service.IDistanceOptimizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author Efrem  on 4/20/19
 */

@SpringBootApplication(scanBasePackages = {"com.distanceOptimizer"})
public class Client extends SpringBootServletInitializer {

    @Autowired
    public static IDistanceOptimizer distanceOptimizer;

    public static void main(String[] args){
        new Client().configure(new SpringApplicationBuilder(Client.class)).run(args);
    }

}
