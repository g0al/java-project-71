package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public final class ObjectMapperBuilder {

    private boolean preserveOrder;

    ObjectMapperBuilder preserveOrder() {
        this.preserveOrder = true;
        return this;
    }

    public ObjectMapper build() {
        ObjectMapper objectMapper = new ObjectMapper();
        if (this.preserveOrder) {
            objectMapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
        }
        return objectMapper;
    }
}
