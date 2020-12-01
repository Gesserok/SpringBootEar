package org.example.multimodule.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProxyParams {
    private String host;
    private Integer port;
    private String proxyAuth;
}

