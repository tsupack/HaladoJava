package hu.me;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Futtathato implements CommandLineRunner{
    private Marshaller marshaller;
    private Szerviz szerviz;

    @Autowired
    public void setMarshaller(Marshaller marshaller){
        this.marshaller = marshaller;
    }

    @Autowired
    public void setSzerviz(Szerviz szerviz){
        this.szerviz = szerviz;
    }

    public static void main(String[] args) {
        SpringApplication.run(Futtathato.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String jsonBemenet = "{\"muvelet\": \"osszead\", \"operandusok\": [3, 4]}";
        String yamlBemenet = "---\n" +
                "muvelet: \"kivon\"\n" +
                "operandusok:\n" +
                "- 10\n" +
                "- 7";

        try {
            marshaller.marshalJsonFile(szerviz.szamol(marshaller.unMarshalJsonFile("JsonInput.json")));
            System.out.println("JSON-bol JSON-be torteno szamitas megtortent!");

            marshaller.marshalYamlFile(szerviz.szamol(marshaller.unMarshalYamlFile("YamlInput.yaml")));
            System.out.println("YAML-bol YAML-ba torteno szamitas megtortent!");

            Output jsonEredmenyStrinbol = szerviz.szamol(marshaller.unMarshal(jsonBemenet, "json"));
            marshaller.marshalJson(jsonEredmenyStrinbol);
            System.out.println("JSON input stringbol valo szamitas megtortent!");

            Output yamlEredmenyStrinbol = szerviz.szamol(marshaller.unMarshal(yamlBemenet, "yaml"));
            marshaller.marshalYaml(yamlEredmenyStrinbol);
            System.out.println("YAML input stringbol valo szamitas megtortent!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}