package cl.uv.ici.arq.labs.demo.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.lang.reflect.Type;
import java.util.List;

@Configuration
public class MapperUtils {

	private static ModelMapper modelMapper = new ModelMapper();

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static List<?> mapAsList(Object source, Type targetListType) {
		if (source == null) { return null; }
		List<?> returnList = modelMapper.map(source, targetListType);
		return returnList;
	}

	public static Object map(Object source, Class<?> destinationClass) {
		if (source == null) { return null; }
		return modelMapper.map(source, destinationClass);
	}

	public static void addMapper(PropertyMap<?, ?> propertyMap) {
		modelMapper.addMappings(propertyMap);
	}

}