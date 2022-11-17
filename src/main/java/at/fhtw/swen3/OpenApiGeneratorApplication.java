package at.fhtw.swen3;

import com.fasterxml.jackson.databind.Module;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/** I had some problems after creating function submitParcel() in the ParcelApiController and ParcelEntity.
 * The programm gave me an error that I solved excluding the DataSrouceAutoConfiguration class following this:
 * https://stackoverflow.com/questions/51221777/failed-to-configure-a-datasource-url-attribute-is-not-specified-and-no-embedd
 * This might have to be solved in the feature.
 */
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@SpringBootApplication
@SpringBootConfiguration
@EnableJpaRepositories
@ComponentScan(basePackages = {"org.openapitools", "at.fhtw.swen3.services", "at.fhtw.swen3.controller" , "at.fhtw.swen3.configuration"})
@EntityScan(basePackages = {"at.fhtw.swen3.persistence" })
@Slf4j
public class OpenApiGeneratorApplication {

    public static void main(String[] args) {
        log.info("Start...");
        SpringApplication.run(OpenApiGeneratorApplication.class, args);
    }

    @Bean
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }

}