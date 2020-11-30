package org.example.multimodule.services.db;

import org.example.multimodule.models.SOAPPassport;

import java.util.List;

public interface PassportService {
    List<SOAPPassport> find(String serie, String number);
}
