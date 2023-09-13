package br.lucasmsf.aded.application.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(
            List<PropertyMap<?, ?>> propertyMapList,
            List<Converter<?, ?>> converterList
    ) {
        var modelMapper = new ModelMapper();
        propertyMapList.forEach(modelMapper::addMappings);
        converterList.forEach(modelMapper::addConverter);
        return modelMapper;
    }

}
