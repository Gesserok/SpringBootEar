package org.example.multimodule.csv.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVRecord;
import org.example.multimodule.csv.DataReceiver;
import org.example.multimodule.models.MVSUkrPassport;
import org.example.multimodule.models.MigrationServiceUrkPassport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
@Log4j2
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class DataReceiverImpl implements DataReceiver {

    private final ObjectMapper objectMapper;

    @Override
    public List<MigrationServiceUrkPassport> getPassports(Iterator<CSVRecord> iterator, Integer batchSize) {

        List<MigrationServiceUrkPassport> passports = new ArrayList<>();
        while (iterator.hasNext()) {
            CSVRecord next = iterator.next();
            MigrationServiceUrkPassport passport = new MigrationServiceUrkPassport(
                    next.get("nn"),
                    next.get("status"),
                    next.get("series"),
                    next.get("number"),
                    next.get("date_edit")
            );
            passports.add(passport);
            if (passports.size() == batchSize) {
                return passports;
            }
        }

        return passports;
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
                    log.info("LAST SYMBOL READ");
                    break;
                }
            } catch (IOException e) {
                log.info("IOEXCEPTION");
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
