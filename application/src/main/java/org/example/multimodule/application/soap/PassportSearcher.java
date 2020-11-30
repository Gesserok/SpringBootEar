package org.example.multimodule.application.soap;


import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.example.multimodule.searcher.dto.PassportSearcherResponse;
import org.example.multimodule.searcher.services.PassportSearcherResponseBuilder;
import org.example.spring.binding.SpringBindingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;


import javax.annotation.Resource;
import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
@Interceptors({SpringBindingInterceptor.class})
@Log4j2
public class PassportSearcher {

    @Resource(name = "useMvsPassportService")
    private Boolean useMvsPassportService;
    @Resource(name = "useMigrationPassportService")
    private Boolean useMigrationPassportService;

    @Autowired
    private PassportSearcherResponseBuilder passportSearcherResponseBuilder;


    @WebMethod
    @WebResult(name = "Found")
    public PassportSearcherResponse find(
            @WebParam(name = "PassportSeries") String series,
            @WebParam(name = "PassportNumber") String number) {

        log.info("Search for series " + series + " number " + number);
        final PassportSearcherResponse passportSearcherResponse = passportSearcherResponseBuilder.findPassports(series, number, useMvsPassportService, useMigrationPassportService);
        log.info("Search result " + passportSearcherResponse + " for series " + series + " number " + number);
        return passportSearcherResponse;



    }
}
