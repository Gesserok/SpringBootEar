package org.example.multimodule.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProxyParams {
    private String host;
    private Integer port;
    private String proxyAuth;
}

