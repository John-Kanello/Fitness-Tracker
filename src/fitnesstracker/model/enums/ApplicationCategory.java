package fitnesstracker.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ApplicationCategory {
    BASIC,
    PREMIUM;

    @JsonCreator
    public static ApplicationCategory fromString(String value) {
        for(ApplicationCategory category : ApplicationCategory.values()) {
            if(category.name().equalsIgnoreCase(value)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Invalid ApplicationCategory: " + value);
    }

    @JsonValue
    public String toLowerCase() {
        return this.name().toLowerCase();
    }
}
