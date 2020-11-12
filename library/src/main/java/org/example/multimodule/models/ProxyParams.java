package org.example.multimodule.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class ProxyParams {
    private String host;
    private Integer port;
    private String proxyAuth;
}

