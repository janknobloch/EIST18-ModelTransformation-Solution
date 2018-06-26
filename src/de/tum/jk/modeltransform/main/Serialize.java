package de.tum.jk.modeltransform.main;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import de.tum.jk.modeltransform.model.Car;
import de.tum.jk.modeltransform.model.Collision;

public class Serialize {

	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {

		// Serialize and generate JSON from Java POJO
			Car winnerCar = new Car("winner.png");
			Car looserCar = new Car("looser.png");
			Collision col = new Collision(winnerCar, looserCar);
		
		//serializing Collision Object to JSON (includes Car objects)
		serializeObjectTOJSON(col);
		
	}

	private static <T>void serializeObjectTOJSON(T c) throws IOException, JsonGenerationException, JsonMappingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		// pretty printing
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		// adding root value wrapping
		mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
		// Object to JSON in file
		mapper.writeValue(new File("./generated/json/" + c.getClass().getSimpleName() + ".json"), c);
	}
}
