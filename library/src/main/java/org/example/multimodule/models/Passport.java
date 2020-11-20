package org.example.multimodule.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=MigrationServiceUrkPassport.class, name = "MigrationServiceUrkPassport"),
        @JsonSubTypes.Type(value=MVSUkrPassport.class, name = "MVSUkrPassport")
})
public interface Passport {
}
