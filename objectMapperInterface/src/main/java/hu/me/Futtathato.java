package hu.me;

import java.io.IOException;

public class Futtathato {
    public static void main(String[] args) {
        Marshaller marshaller = new Marshaller();
        Szerviz szerviz = new Szerviz(new Szamologep());

        String jsonBemenet = "{\"muvelet\": \"osszead\", \"operandusok\": [3, 4]}";
        String yamlBemenet = "---\n" +
                "muvelet: \"kivon\"\n" +
                "operandusok:\n" +
                "- 10\n" +
                "- 7";

        try {
            marshaller.marshalJsonFile(szerviz.szamol(marshaller.unMarshalJsonFile("JsonInput.json")));
            marshaller.marshalYamlFile(szerviz.szamol(marshaller.unMarshalYamlFile("YamlInput.yaml")));

            Output jsonEredmenyStrinbol = szerviz.szamol(marshaller.unMarshal(jsonBemenet, "json"));
            marshaller.marshalJson(jsonEredmenyStrinbol);

            Output yamlEredmenyStrinbol = szerviz.szamol(marshaller.unMarshal(yamlBemenet, "yaml"));
            marshaller.marshalYaml(yamlEredmenyStrinbol);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}