package hu.me;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Marshaller {

    private ObjectMapper objectMapper;

    public void marshalJson(Output output) throws IOException {
        this.objectMapper = new ObjectMapper();
        objectMapper.writeValue(new FileOutputStream("src//main//resources//" + "output.json"), output);
    }

    public void marshalYaml(Output output) throws IOException {
        this.objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.writeValue(new FileOutputStream("src//main//resources//" + "output.yaml"), output);
    }

    public Input unMarshal(String input, String which) throws IOException {
        if ("json".equals(which)) {
            this.objectMapper = new ObjectMapper();
            return objectMapper.readValue(input, Input.class);
        } else if ("yaml".equals(which)) {
            this.objectMapper = new ObjectMapper(new YAMLFactory());
            return objectMapper.readValue(input, Input.class);
        } else return null;
    }

    public void marshalJsonFile(Output output) throws IOException {
        this.objectMapper = new ObjectMapper();
        objectMapper.writeValue(new FileOutputStream("src//main//resources//" + "outputFile.json"), output);
    }

    public void marshalYamlFile(Output output) throws IOException {
        this.objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.writeValue(new FileOutputStream("src//main//resources//" + "outputFile.yaml"), output);
    }

    public Input unMarshalJsonFile(String jsonFajl) throws IOException {
        this.objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("src//main//resources//" + jsonFajl), Input.class);
    }

    public Input unMarshalYamlFile(String yamlFajl) throws IOException {
        this.objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File("src//main//resources//" + yamlFajl), Input.class);
    }
}