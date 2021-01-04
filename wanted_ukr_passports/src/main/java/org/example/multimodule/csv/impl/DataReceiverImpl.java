package org.example.multimodule.csv.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.multimodule.csv.DataReceiver;
import org.example.multimodule.exceptions.ODPConnectorException;
import org.example.multimodule.models.MVSUkrPassport;
import org.example.multimodule.models.MigrationServiceUrkPassport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Log4j2
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class DataReceiverImpl implements DataReceiver {

    private final ObjectMapper objectMapper;

    @Override
    public List<MigrationServiceUrkPassport> getPassports(List<String> lines) {

        return lines.stream().map(line -> line.split(";"))
                .map(arr -> new MigrationServiceUrkPassport(
                        arr[0], arr[1], arr[2], arr[3], arr[4]))
                .collect(Collectors.toList());
    }

    @Override
    public List<MVSUkrPassport> getPassports(Reader reader, Integer batchSize) throws JsonProcessingException {

        List<MVSUkrPassport> passports = new ArrayList<>();

        int symbol = 0;
        boolean isObject = false;
        StringBuilder sb = null;
        String passportJson;
        MVSUkrPassport passport;
        while (true) {
            try {
                if ((symbol = reader.read()) == -1) {
                    break;
                }
            } catch (IOException e) {
                throw new ODPConnectorException("I/O Exception", e);
            }
            if ('{' == (char) symbol) {
                isObject = true;
                sb = new StringBuilder();
            }
            if ('}' == (char) symbol) {
                Objects.requireNonNull(sb).append((char) symbol);
                isObject = false;
                passportJson = sb.toString();
                passport = objectMapper.readValue(passportJson, MVSUkrPassport.class);
                passports.add(passport);
                if (passports.size() == batchSize) {
                    return passports;
                }
            }

            if (isObject) {
                sb.append((char) symbol);
            }
        }
        return passports;
    }
}
