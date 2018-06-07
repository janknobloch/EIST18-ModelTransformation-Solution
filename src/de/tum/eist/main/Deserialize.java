package de.tum.eist.main;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jackson2Annotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.rules.RuleFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.codemodel.JCodeModel;

import de.tum.eist.config.JsonReaderConfig;
import de.tum.eist.model.Car;
import de.tum.eist.model.Collision;

public class Deserialize {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {

		// basic: uses existing defined POJOs and loads them with content from JSON file
		// (class = filename) therefore generic use.
		// prints toString() methods of Objects and Childs (Collision and Cars)
		createPOJOFromJSON(new Collision());

		// advanced : creates java class files based on a given JSON format (reverse
		// engineering from JSON code)
		// createPOJOClassFilesFromJSON(new Collision());

	}

	private static <T> void createPOJOFromJSON(T toSerialize) {
		ObjectMapper mapper = new ObjectMapper();
		File f = new File("./generated/json/" + toSerialize.getClass().getSimpleName() + ".json");
		// JSON from file to Object including root value
		mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
		try {
			T obj = (T) mapper.readValue(f, toSerialize.getClass());
			System.out.println(obj);
		} catch (Exception e) {
			System.err.println("Something went wrong");
		}

	}

	private static <T> void createPOJOClassFilesFromJSON(T type) throws MalformedURLException, IOException {
		// Read json file
		JCodeModel codeModel = new JCodeModel();
		URL source = new File("generated/json/" + type.getClass().getSimpleName() + ".json").toURI().toURL();

		// specify own config to allow generation from JSON file not from JSON Schema
		GenerationConfig config = new JsonReaderConfig();

		// create a schema mapper using our config and generator
		SchemaMapper mapper = new SchemaMapper(new RuleFactory(config, new Jackson2Annotator(config), new SchemaStore()), new SchemaGenerator());

		// generate the class model
		mapper.generate(codeModel, type.getClass().getSimpleName(), "", source);

		// select directory and write to file
		File dir = new File("./generated/pojo");
		codeModel.build(dir);
	}
}
