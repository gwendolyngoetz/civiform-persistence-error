package models;

import com.jayway.jsonpath.*;
import java.util.Optional;

public class ApplicantData {
    private final DocumentContext jsonData;

    public ApplicantData() {
        this("{}");
    }

    public ApplicantData(String jsonData) {
        this.jsonData = JsonPath.parse(jsonData);
    }

    public void putString(String value) {
        jsonData.put("$", "some_field", value);
    }

    public Optional<String> readString() {
        try {
            return Optional.ofNullable(jsonData.read("$.some_field", String.class));
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }

    public String asJsonString() {
        return jsonData.jsonString();
    }
}
