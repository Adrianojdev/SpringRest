package com.algaworks.algalog.common;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean // método instancia, inicializa e configura um bean que será gerenciado pelo spring
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
