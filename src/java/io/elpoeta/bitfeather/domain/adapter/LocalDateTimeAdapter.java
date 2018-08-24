
package io.elpoeta.bitfeather.domain.adapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author elpoeta
 */
public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>{

    @Override
    public JsonElement serialize(LocalDateTime t, Type type, JsonSerializationContext jsc) {
        final  DateTimeFormatter formatoFeha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return new JsonPrimitive(t.format(formatoFeha));
    }
    
}
