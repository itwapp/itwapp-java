package io.itwapp.models;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class ApplicantStatusDeserializer implements JsonDeserializer<ApplicantStatus> {
    @Override
    public ApplicantStatus deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        int key = jsonElement.getAsInt();
        try {
            return ApplicantStatus.fromCode(key);
        }catch(IllegalArgumentException exc)    {
            return ApplicantStatus.DEFAULT_CODE;
        }
    }
}
