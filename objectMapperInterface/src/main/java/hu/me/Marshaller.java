package hu.me;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import hu.me.exceptions.InvalidProcedureException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Marshaller {

    private ObjectMapper objectMapper;

    public void marshalJson(double eredmeny, String uzenet, int hibakod) throws IOException {
        this.objectMapper = new ObjectMapper();
        Output output = ujOutput(eredmeny, uzenet, hibakod);
        objectMapper.writeValue(new FileOutputStream("src//main//resources//" + "output.json"), output);
    }

    public Input unMarshalJson(String jsonFajl) throws IOException {
        this.objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("src//main//resources//" + jsonFajl), Input.class);
    }

    public void marshalYaml(double eredmeny, String uzenet, int hibakod) throws IOException {
        this.objectMapper = new ObjectMapper(new YAMLFactory());
        Output output = ujOutput(eredmeny, uzenet, hibakod);
        objectMapper.writeValue(new FileOutputStream("src//main//resources//" + "output.yaml"), output);
    }

    public Input unMarshalYaml(String yamlFajl) throws IOException {
        this.objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File("src//main//resources//" + yamlFajl), Input.class);
    }

    public void keszitsInputJsont(String fajlNev, String muvelet, int a, int b) throws InvalidProcedureException, IOException {
        this.objectMapper = new ObjectMapper();
        objectMapper.writeValue(new FileOutputStream("src//main//resources//" + fajlNev + ".json"), ujInput(muvelet, a, b));
    }

    public void keszitsInputYamlt(String fajlNev, String muvelet, int a, int b) throws InvalidProcedureException, IOException {
        this.objectMapper = new ObjectMapper(new YAMLFactory());
        objectMapper.writeValue(new FileOutputStream("src//main//resources//" + fajlNev + ".yaml"), ujInput(muvelet, a, b));
    }

    public Input ujInput(String muvelet, int a, int b) throws InvalidProcedureException {
        Input input = new Input();
        input.setMuvelet(muvelet);
        int[] operandusok = new int[2];
        operandusok[0] = a;
        operandusok[1] = b;
        input.setOperandusok(operandusok);
        return input;
    }

    public Output ujOutput(double eredmeny, String uzenet, int hibakod) {
        Output output = new Output();
        output.setEredmeny(eredmeny);
        output.setUzenet(uzenet);
        output.setHibakod(hibakod);
        return output;
    }
}