package org.example.multimodule.searcher.services;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.example.multimodule.models.MVSUkrPassport;
import org.example.multimodule.models.MigrationServiceUrkPassport;
import org.example.multimodule.searcher.dto.PassportSearcherResponse;
import org.example.multimodule.searcher.models.SOAPPassport;
import org.example.multimodule.searcher.services.db.MigrationPassportService;
import org.example.multimodule.searcher.services.db.MvsPassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.persistence.QueryTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@ComponentScan(basePackages = "org.multimodule.searcher")
public class PassportSearcherResponseBuilder {

    private static int MAX_ERROR_MESSAGE_LENGTH = 1860;
    @Autowired
    private MvsPassportService mvsPassportService;
    @Autowired
    private MigrationPassportService migrationPassportService;
    @Autowired
    private PassportValidator passportValidator;


    public PassportSearcherResponse findPassports(
            String series,
            String number,
            Boolean useMvsPassportService,
            Boolean useMigrationPassportService) {

        PassportSearcherResponse validate = passportValidator.validate(series, number);

        if (Objects.nonNull(validate.getCode())) {
            return validate;
        }

        return buildPassportSearcherResponse(series, number, useMvsPassportService, useMigrationPassportService);

    }

    private PassportSearcherResponse createPassportSearcherResponseSuccessfully(List<SOAPPassport> passports) {

        PassportSearcherResponse passportSearcherResponse = new PassportSearcherResponse();
        if (passports == null || passports.isEmpty()) {
            passportSearcherResponse.setCode("0");
            return passportSearcherResponse;
        }
        passportSearcherResponse.setCode("1");
        passportSearcherResponse.setPassports(passports);
        return passportSearcherResponse;
    }

    private PassportSearcherResponse buildPassportSearcherResponse(
            String series,
            String number,
            Boolean useMvsPassportService,
            Boolean useMigrationPassportService) {

        List<SOAPPassport> result = new ArrayList<>();
        List<MVSUkrPassport> mvsPassports;
        List<MigrationServiceUrkPassport> migrationServiceUrkPassports = new ArrayList<>();
        PassportSearcherResponse passportSearcherResponse;
        try {
            if (useMvsPassportService) {
                mvsPassports = (series == null || series.isEmpty())
                        ? mvsPassportService.findByNumber(number)
                        : mvsPassportService.findBySeriesAndNumber(series, number);
                for (MVSUkrPassport mvsUkrPassport : mvsPassports) {
                    result.add(new SOAPPassport(mvsUkrPassport));
                }
            }
            if (useMigrationPassportService) {
                migrationServiceUrkPassports = (series == null || series.isEmpty())
                        ? migrationPassportService.findByNumber(number)
                        : migrationPassportService.findBySeriesAndNumber(series, number);
                for (MigrationServiceUrkPassport migrationServiceUrkPassport : migrationServiceUrkPassports) {
                    result.add(new SOAPPassport(migrationServiceUrkPassport));
                }
            }
            passportSearcherResponse = createPassportSearcherResponseSuccessfully(result);
        } catch (QueryTimeoutException  e) {
            passportSearcherResponse = createPassportSearcherResponseWithError(e, "2");
        } catch (Throwable e) {
            passportSearcherResponse = createPassportSearcherResponseWithError(e, "3");
        }
        return passportSearcherResponse;
    }

    private PassportSearcherResponse createPassportSearcherResponseWithError(Throwable e, String code) {
        PassportSearcherResponse passportSearcherResponse = new PassportSearcherResponse();
        passportSearcherResponse.setCode(code);
        passportSearcherResponse.setErrorMessage(
                code.equals("2")
                        ? "Error: Time out"
                        : "Error: Exception occured " + getStackTrace(e));
        return passportSearcherResponse;
    }

    private String getStackTrace(Throwable e) {
        String stackTrace = ExceptionUtils.getStackTrace(e);
        return stackTrace.length() > MAX_ERROR_MESSAGE_LENGTH + 1
                ? stackTrace.substring(0, MAX_ERROR_MESSAGE_LENGTH)
                : stackTrace;
    }

}
