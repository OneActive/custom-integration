package com.activeai.integration.banking.controller;

import com.activeai.integration.banking.domain.request.AtmLocatorRequest;
import com.activeai.integration.banking.domain.response.AtmLocatorResponse;
import com.activeai.integration.banking.services.LocatorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Locator Related APIs", description = "Shows API documentation regarding atm or branch locator")
@RestController
public class LocatorApiController {

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private LocatorService locatorService;

  private static final Logger logger = LoggerFactory.getLogger(LocatorApiController.class);

  @ApiOperation(value = "Returns list of Atms based on area")
  @RequestMapping(value = "{fetchType}/atms", produces = {"application/json"}, method = RequestMethod.POST)
  public ResponseEntity<AtmLocatorResponse> getNearestAtms(@PathVariable(name = "fetchType", required = true) String fetchType,
      @RequestBody final AtmLocatorRequest atmLocatorRequest) {
    logger.info("Entering getAtms");
    if(StringUtils.isNotEmpty(fetchType) && fetchType.equalsIgnoreCase("address"))
    {
      return locatorService.getNearestAtmsAddressResponseEntity(atmLocatorRequest);
    }
    else {
      return locatorService.getNearestAtmsGeocodesResponseEntity(atmLocatorRequest);
    }
  }
}
