package org.example.multimodule.searcher.services;

import org.example.multimodule.searcher.dto.PassportSearcherResponse;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PassportValidator {

    public PassportSearcherResponse validate(String series, String number) {

        PassportSearcherResponse passportSearcherResponse = new PassportSearcherResponse();

        if (Objects.isNull(number) || (number.length() != 6 && number.length() != 9)) {
            passportSearcherResponse.setCode("3");
            passportSearcherResponse.setErrorMessage("Error: Exception occured: Passport NUMBER validation");
            return passportSearcherResponse;
        }
        if (number.length() == 6 && (Objects.isNull(series) || series.length() != 2)) {
            passportSearcherResponse.setCode("3");
            passportSearcherResponse.setErrorMessage("Error: Exception occured: Passport SERIES validation");
            return passportSearcherResponse;
        }
        if (number.length() == 9 && (Objects.nonNull(series) && !series.isEmpty())) {
            passportSearcherResponse.setCode("3");
            passportSearcherResponse.setErrorMessage("Error: Exception occured: Identity card SERIES validation");
            return passportSearcherResponse;

        }
        return passportSearcherResponse;
    }
}
