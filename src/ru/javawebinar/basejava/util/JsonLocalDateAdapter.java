package ru.javawebinar.basejava.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//https://github.com/google/gson/issues/1507

public class JsonLocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String ldtString = jsonElement.getAsString();
        return LocalDate.parse(ldtString, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public JsonElement serialize(LocalDate localDate, Type type, JsonSerializationContext jsonSerializationContext) {
        String format = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        return new JsonPrimitive(format);
    }
}
