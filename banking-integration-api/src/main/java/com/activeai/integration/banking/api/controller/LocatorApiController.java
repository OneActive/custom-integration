package com.activeai.integration.banking.api.controller;

import com.activeai.integration.banking.api.services.LocatorService;
import com.activeai.integration.banking.common.util.ApplicationLogger;
import com.activeai.integration.banking.domain.request.AtmLocatorRequest;
import com.activeai.integration.banking.domain.response.AtmLocatorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Locator Related APIs", description = "Shows API documentation regarding atm or branch locator")
@RestController
public class LocatorApiController {

  @Autowired private LocatorService locatorService;

  @ApiOperation(value = "Returns list of Atms based on area")
  @PostMapping(value = "{fetchType}/atms", produces = {"application/json"})
  public ResponseEntity<AtmLocatorResponse> getNearestAtms(@PathVariable(name = "fetchType", required = true) String fetchType,
      @RequestBody final AtmLocatorRequest atmLocatorRequest) {
    ApplicationLogger.logInfo("Entering getAtms", this.getClass());
    if (StringUtils.isNotEmpty(fetchType) && fetchType.equalsIgnoreCase("address")) {
      return locatorService.getNearestAtmsAddressResponseEntity(atmLocatorRequest);
    } else {
      return locatorService.getNearestAtmsGeocodesResponseEntity(atmLocatorRequest);
    }
  }
}
