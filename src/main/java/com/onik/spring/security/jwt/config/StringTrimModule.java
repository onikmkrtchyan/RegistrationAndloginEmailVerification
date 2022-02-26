package com.onik.spring.security.jwt.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class StringTrimModule extends SimpleModule {

    private final List<String> ignoredFields = List.of(
            "password"
    );

    public StringTrimModule() {
        addDeserializer(String.class, new StdScalarDeserializer<>(String.class) {
            @Override
            public String deserialize(JsonParser jsonParser, DeserializationContext ctx) throws IOException {
                if (jsonParser.getCurrentName() == null) {
                    return jsonParser.getValueAsString();
                }
                if (ignoredFields.contains(jsonParser.currentName())) {
                    return jsonParser.getValueAsString();
                }
                return jsonParser.getValueAsString().trim();
            }
        });
    }
}
