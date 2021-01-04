package org.example.multimodule.application.controllers;

public  enum ContextRoot {

    SOAP_CONTEXT_ROOT("Hydrogen"),
    REST_CONTEXT_ROOT("Helium");

    public final String label;

    private ContextRoot(String label) {
        this.label = label;
    }

    public static ContextRoot valueOf() {
        return ContextRoot.SOAP_CONTEXT_ROOT;
    }
}
