package de.tum.jk.modeltransform.config;

import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.SourceType;

public class JsonReaderConfig extends DefaultGenerationConfig {
	@Override
	public SourceType getSourceType() {
		return SourceType.JSON;
	}
}
