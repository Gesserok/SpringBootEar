package org.example.multimodule.models;

import lombok.*;

@Data
@AllArgsConstructor
public class ClientParams {
    private String baseAddress;
    private String u2cRemoteHttpsCredentials;
    private String mediaType;
    private String encoding;
    private Integer connectionTimeout;
}
