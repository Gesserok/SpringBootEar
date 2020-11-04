package org.example.multimodule.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ClientParams {
    private String baseAddress;
    private String u2cRemoteHttpsCredentials;
    private String mediaType;
    private String encoding;
    private Integer connectionTimeout;
}
