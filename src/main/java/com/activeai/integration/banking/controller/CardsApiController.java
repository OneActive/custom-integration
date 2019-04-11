package com.activeai.integration.banking.controller;

import java.io.IOException;
import java.time.OffsetDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.activeai.integration.banking.model.CardDetailResponse;
import com.activeai.integration.banking.model.CardLimitResponse;
import com.activeai.integration.banking.model.CardTransactionsResponse;
import com.activeai.integration.banking.model.CardsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class CardsApiController {

  @Autowired
  private ObjectMapper objectMapper;

  private static final Logger logger = LoggerFactory.getLogger(CardsApiController.class);

  // TODO : Populate stub card list
  @RequestMapping(value = "/{customerId}/cards", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<CardsResponse> getCards(@PathVariable(value = "customerId", required = true) Integer customerId) {
    logger.info("Entering getCards");
    ResponseEntity<CardsResponse> response = null;
    try {
      response = new ResponseEntity<>(objectMapper.readValue(
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"cards\" : [\n"
              + "{  \n" + "   \"branchId\":\"JPMC001\",\n" + "   \"product\":\"product\",\n" + "   \"closingBalance\":6.027456183070403,\n"
              + "   \"branchName\":\"Manhattan\",\n" + "   \"bankName\" : \"JPMorgan Chase\",\n"
              + "   \"accountName\" : \"Platinum Edge\",\n" + "   \"type\":\"CREDIT\",\n" + "   \"accountNumber\":\"1234567890\",\n"
              + "   \"cardIssuer\":\"Visa\",\n" + "   \"amountDue\":1.4658129805029452,\n" + "   \"accountId\":\"5123456789012346\",\n"
              + "   \"paymentDueDate\":\"2000-01-23\",\n" + "   \"lastStatementDate\":\"2000-01-23\",\n"
              + "   \"productCode\":\"productCode\",\n" + "   \"lastStatementBalance\":3.616076749251911,\n"
              + "   \"oversearCardActivated\":true,\n" + "   \"minimumPayment\":5.962133916683182,\n"
              + "   \"creditLimit\":5.637376656633329,\n" + "   \"displayAccountNumber\":\"123xxxx890\",\n"
              + "   \"permanentCreditLimit\":9.301444243932576,\n" + "   \"category\":\"category\",\n"
              + "   \"openingBalance\":0.8008281904610115,\n" + "   \"availableCreditLimit\":2.3021358869347655,\n"
              + "   \"temporaryCreditLimit\":7.061401241503109,\n" + "   \"status\":\"ACTIVE\"\n" + "},\n" + "{  \n"
              + "   \"branchId\":\"USB001\",\n" + "   \"product\":\"product\",\n" + "   \"closingBalance\":6.027456183070403,\n"
              + "   \"branchName\":\"Marceline\",\n" + "   \"bankName\" : \"US Bancorp\",\n"
              + "   \"accountName\" : \"Titanium Times Card\",\n" + "   \"type\":\"CREDIT\",\n" + "   \"accountNumber\":\"1234567892\",\n"
              + "   \"cardIssuer\":\"Visa\",\n" + "   \"amountDue\":1.4658129805029452,\n" + "   \"accountId\":\"5123456789012347\",\n"
              + "   \"paymentDueDate\":\"2000-01-24\",\n" + "   \"lastStatementDate\":\"2000-01-24\",\n"
              + "   \"productCode\":\"productCode\",\n" + "   \"lastStatementBalance\":3.616076749251911,\n"
              + "   \"oversearCardActivated\":true,\n" + "   \"minimumPayment\":15.962133916683182,\n"
              + "   \"creditLimit\":6.637376656633329,\n" + "   \"displayAccountNumber\":\"123xxxx892\",\n"
              + "   \"permanentCreditLimit\":8.301444243932576,\n" + "   \"category\":\"category\",\n"
              + "   \"openingBalance\":1.8008281904610115,\n" + "   \"availableCreditLimit\":3.3021358869347655,\n"
              + "   \"temporaryCreditLimit\":5.061401241503109,\n" + "   \"status\":\"ACTIVE\"\n" + "},\n" + "{  \n"
              + "   \"branchId\":\"M001\",\n" + "   \"product\":\"product\",\n" + "   \"closingBalance\":6.027456183070403,\n"
              + "   \"branchName\":\"Arlington\",\n" + "   \"bankName\" : \"Wells Fargo\",\n"
              + "   \"accountName\" : \"Domestic Signature Card\",\n" + "   \"type\":\"CREDIT\",\n"
              + "   \"accountNumber\":\"1234567891\",\n" + "   \"cardIssuer\":\"Visa\",\n" + "   \"amountDue\":1.4658129805029452,\n"
              + "   \"accountId\":\"5123456789012348\",\n" + "   \"paymentDueDate\":\"2000-01-25\",\n"
              + "   \"lastStatementDate\":\"2000-01-25\",\n" + "   \"productCode\":\"productCode\",\n"
              + "   \"lastStatementBalance\":3.616076749251911,\n" + "   \"oversearCardActivated\":false,\n"
              + "   \"minimumPayment\":9.962133916683182,\n" + "   \"creditLimit\":4.637376656633329,\n"
              + "   \"displayAccountNumber\":\"123xxxx891\",\n" + "   \"permanentCreditLimit\":9.301444243932576,\n"
              + "   \"category\":\"category\",\n" + "   \"openingBalance\":2.80063881904610115,\n"
              + "   \"availableCreditLimit\":4.3021358869347655,\n" + "   \"temporaryCreditLimit\":9.061401241503109,\n"
              + "   \"status\":\"ACTIVE\"\n" + "}]}",
          CardsResponse.class), HttpStatus.OK);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      response = new ResponseEntity<CardsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return response;
  }


  @RequestMapping(value = "/{customerId}/cards/{cardId}", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<CardDetailResponse> getCardDetail(@PathVariable(value = "customerId", required = true) Integer customerId,
      @PathVariable(value = "cardId", required = true) Integer cardId) {
    logger.info("Entering getCardDetail");
    ResponseEntity<CardDetailResponse> response = null;
    try {
      response = new ResponseEntity<>(objectMapper.readValue(
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"cardDetail\" : {  \n"
              + "   \"branchId\":\"JPMC001\",\n" + "   \"product\":\"product\",\n" + "   \"closingBalance\":6.027456183070403,\n"
              + "   \"branchName\":\"Manhattan\",\n" + "   \"bankName\" : \"JPMorgan Chase\",\n"
              + "   \"accountName\" : \"Platinum Edge\",\n" + "   \"type\":\"CREDIT\",\n" + "   \"accountNumber\":\"1234567890\",\n"
              + "   \"cardIssuer\":\"Visa\",\n" + "   \"amountDue\":1.4658129805029452,\n" + "   \"accountId\":\"5123456789012346\",\n"
              + "   \"paymentDueDate\":\"2000-01-23\",\n" + "   \"lastStatementDate\":\"2000-01-23\",\n"
              + "   \"productCode\":\"productCode\",\n" + "   \"lastStatementBalance\":3.616076749251911,\n"
              + "   \"oversearCardActivated\":true,\n" + "   \"minimumPayment\":5.962133916683182,\n"
              + "   \"creditLimit\":5.637376656633329,\n" + "   \"displayAccountNumber\":\"123xxxx890\",\n"
              + "   \"permanentCreditLimit\":9.301444243932576,\n" + "   \"category\":\"category\",\n"
              + "   \"openingBalance\":0.8008281904610115,\n" + "   \"availableCreditLimit\":2.3021358869347655,\n"
              + "   \"temporaryCreditLimit\":7.061401241503109,\n" + "   \"status\":\"ACTIVE\"\n" + "}}",
          CardDetailResponse.class), HttpStatus.OK);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return response;
  }

  @RequestMapping(value = "/{customerId}/cards/{cardId}/transactions", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<CardTransactionsResponse> getCardTransactions(
      @PathVariable(value = "customerId", required = true) Integer customerId,
      @PathVariable(value = "cardId", required = true) Integer cardId) {
    logger.info("Entering getCardTransactions");
    ResponseEntity<CardTransactionsResponse> response = null;
    String cardTransactions = null;
    try {
      cardTransactions = "{\n" + 
          "    \"result\": {\n" + 
          "        \"messageCode\": \"messageCode\",\n" + 
          "        \"message\": \"message\",\n" + 
          "        \"status\": 0\n" + 
          "    },\n" + 
          "    \"cardTransactions\": [{\n" + 
          "            \"foreignTxnCurrency\": \"foreignTxnCurrency\",\n" + 
          "            \"amount\": 1000,\n" + 
          "            \"description\": \"description\",\n" + 
          "            \"currency\": \"currency\",\n" + 
          "            \"foreignTxnAmount\": 1.465812,\n" + 
          "            \"isDebit\": true,\n" + 
          "            \"category\": \"category\",\n" + 
          "            \"foreignTxnExchangeRate\": 5.962134,\n" + 
          "            \"txnDate\": \"2019-04-03\",\n" + 
          "            \"referenceId\": \"referenceId\"\n" + 
          "        },\n" + 
          "        {\n" + 
          "            \"foreignTxnCurrency\": \"foreignTxnCurrency\",\n" + 
          "            \"amount\": 2000,\n" + 
          "            \"description\": \"description\",\n" + 
          "            \"currency\": \"currency\",\n" + 
          "            \"foreignTxnAmount\": 1.465812,\n" + 
          "            \"isDebit\": true,\n" + 
          "            \"category\": \"category\",\n" + 
          "            \"foreignTxnExchangeRate\": 5.962134,\n" + 
          "            \"txnDate\": \"2019-04-02\",\n" + 
          "            \"referenceId\": \"referenceId\"\n" + 
          "        },\n" + 
          "        {\n" + 
          "            \"foreignTxnCurrency\": \"foreignTxnCurrency\",\n" + 
          "            \"amount\": 3000,\n" + 
          "            \"description\": \"description\",\n" + 
          "            \"currency\": \"currency\",\n" + 
          "            \"foreignTxnAmount\": 1.4658129805029452,\n" + 
          "            \"isDebit\": true,\n" + 
          "            \"category\": \"category\",\n" + 
          "            \"foreignTxnExchangeRate\": 5.962134,\n" + 
          "            \"txnDate\": \"2019-04-01\",\n" + 
          "            \"referenceId\": \"referenceId\"\n" + 
          "        },\n" + 
          "        {\n" + 
          "            \"foreignTxnCurrency\": \"foreignTxnCurrency\",\n" + 
          "            \"amount\": 4000,\n" + 
          "            \"description\": \"description\",\n" + 
          "            \"currency\": \"currency\",\n" + 
          "            \"foreignTxnAmount\": 1.4658129805029452,\n" + 
          "            \"isDebit\": true,\n" + 
          "            \"category\": \"category\",\n" + 
          "            \"foreignTxnExchangeRate\": 5.962134,\n" + 
          "            \"txnDate\": \"2019-03-31\",\n" + 
          "            \"referenceId\": \"referenceId\"\n" + 
          "        },\n" + 
          "        {\n" + 
          "            \"foreignTxnCurrency\": \"foreignTxnCurrency\",\n" + 
          "            \"amount\": 5000,\n" + 
          "            \"description\": \"description\",\n" + 
          "            \"currency\": \"currency\",\n" + 
          "            \"foreignTxnAmount\": 1.4658129805029452,\n" + 
          "            \"isDebit\": true,\n" + 
          "            \"category\": \"category\",\n" + 
          "            \"foreignTxnExchangeRate\": 5.962134,\n" + 
          "            \"txnDate\": \"2019-03-30\",\n" + 
          "            \"referenceId\": \"referenceId\"\n" + 
          "        }\n" + 
          "    ]\n" + 
          "}";
          
      response = new ResponseEntity<>(objectMapper.readValue(cardTransactions, CardTransactionsResponse.class), HttpStatus.OK);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return response;
  }

  @RequestMapping(value = "/{customerId}/cards/{cardId}/block", produces = {"application/json"}, consumes = {"application/json"},
      method = RequestMethod.PUT)
  public ResponseEntity<CardDetailResponse> blockCard(@PathVariable(name = "customerId", required = true) Integer customerId,
      @PathVariable(name = "cardId", required = true) Integer cardId,
      @RequestParam(value = "blockMode", required = true) String blockMode) {
    ResponseEntity<CardDetailResponse> response = null;
    try {
      response = new ResponseEntity<>(objectMapper.readValue(
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"cardDetail\" : {    \"branchId\" : \"branchId\",    \"product\" : \"product\",    \"closingBalance\" : 6.027456183070403,    \"branchName\" : \"branchName\",    \"type\" : \"CREDIT\",    \"accountNumber\" : \"accountNumber\",    \"cardIssuer\" : \"Visa\",    \"amountDue\" : 1.4658129805029452,    \"accountId\" : \"accountId\",    \"paymentDueDate\" : \"2000-01-23T04:56:07.000+00:00\",    \"lastStatementDate\" : \"2000-01-23T04:56:07.000+00:00\",    \"productCode\" : \"productCode\",    \"lastStatementBalance\" : 3.616076749251911,    \"oversearCardActivated\" : true,    \"minimumPayment\" : 5.962133916683182,    \"creditLimit\" : 5.637376656633329,    \"displayAccountNumber\" : \"123xxxx890\",    \"permanentCreditLimit\" : 9.301444243932576,    \"category\" : \"category\",    \"openingBalance\" : 0.8008281904610115,    \"availableCreditLimit\" : 2.3021358869347655,    \"temporaryCreditLimit\" : 7.061401241503109,    \"status\" : \"ACTIVE\"  }}",
          CardDetailResponse.class), HttpStatus.OK);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return response;
  }

  @RequestMapping(value = "/{customerId}/cards/{cardId}/overseasUse", produces = {"application/json"}, consumes = {"multipart/form-data"},
      method = RequestMethod.PUT)
  public ResponseEntity<CardDetailResponse> updateOverseasUsage(@PathVariable(value = "customerId", required = true) Integer customerId,
      @PathVariable(value = "cardId", required = true) Integer cardId,
      @RequestParam(value = "activationMode", required = true) String activationMode) {
    ResponseEntity<CardDetailResponse> response = null;
    try {
      response = new ResponseEntity<>(objectMapper.readValue(
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"cardDetail\" : {    \"branchId\" : \"branchId\",    \"product\" : \"product\",    \"closingBalance\" : 6.027456183070403,    \"branchName\" : \"branchName\",    \"type\" : \"CREDIT\",    \"accountNumber\" : \"accountNumber\",    \"cardIssuer\" : \"Visa\",    \"amountDue\" : 1.4658129805029452,    \"accountId\" : \"accountId\",    \"paymentDueDate\" : \"2000-01-23T04:56:07.000+00:00\",    \"lastStatementDate\" : \"2000-01-23T04:56:07.000+00:00\",    \"productCode\" : \"productCode\",    \"lastStatementBalance\" : 3.616076749251911,    \"oversearCardActivated\" : true,    \"minimumPayment\" : 5.962133916683182,    \"creditLimit\" : 5.637376656633329,    \"displayAccountNumber\" : \"123xxxx890\",    \"permanentCreditLimit\" : 9.301444243932576,    \"category\" : \"category\",    \"openingBalance\" : 0.8008281904610115,    \"availableCreditLimit\" : 2.3021358869347655,    \"temporaryCreditLimit\" : 7.061401241503109,    \"status\" : \"ACTIVE\"  }}",
          CardDetailResponse.class), HttpStatus.OK);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return response;
  }

  @RequestMapping(value = "/{customerId}/cards/{cardId}/limitUpdate", produces = {"application/json"}, consumes = {"multipart/form-data"},
      method = RequestMethod.PUT)
  public ResponseEntity<CardDetailResponse> limitUpdate(@PathVariable(value = "customerId", required = true) Integer customerId,
      @PathVariable(value = "cardId", required = true) Integer cardId,
      @RequestParam(value = "limitUpdateType", required = true) String limitUpdateType,
      @RequestParam(value = "amount", required = true) Double amount, @RequestParam(value = "reason", required = true) String reason,
      @RequestParam(value = "fromDate", required = false) OffsetDateTime fromDate,
      @RequestParam(value = "toDate", required = false) OffsetDateTime toDate) {
    ResponseEntity<CardDetailResponse> response = null;
    try {
      response = new ResponseEntity<CardDetailResponse>(objectMapper.readValue(
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"cardDetail\" : {    \"branchId\" : \"branchId\",    \"product\" : \"product\",    \"closingBalance\" : 6.027456183070403,    \"branchName\" : \"branchName\",    \"type\" : \"CREDIT\",    \"accountNumber\" : \"accountNumber\",    \"cardIssuer\" : \"Visa\",    \"amountDue\" : 1.4658129805029452,    \"accountId\" : \"accountId\",    \"paymentDueDate\" : \"2000-01-23T04:56:07.000+00:00\",    \"lastStatementDate\" : \"2000-01-23T04:56:07.000+00:00\",    \"productCode\" : \"productCode\",    \"lastStatementBalance\" : 3.616076749251911,    \"oversearCardActivated\" : true,    \"minimumPayment\" : 5.962133916683182,    \"creditLimit\" : 5.637376656633329,    \"displayAccountNumber\" : \"123xxxx890\",    \"permanentCreditLimit\" : 9.301444243932576,    \"category\" : \"category\",    \"openingBalance\" : 0.8008281904610115,    \"availableCreditLimit\" : 2.3021358869347655,    \"temporaryCreditLimit\" : 7.061401241503109,    \"status\" : \"ACTIVE\"  }}",
          CardDetailResponse.class), HttpStatus.OK);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      response = new ResponseEntity<CardDetailResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return response;
  }

  @RequestMapping(value = "/{customerId}/cards/{cardId}/getLimits", produces = {"application/json"}, method = RequestMethod.GET)
  public ResponseEntity<CardLimitResponse> getCardLimits(@PathVariable(value = "customerId", required = true) Integer customerId,
      @PathVariable(value = "cardId", required = true) Integer cardId) {
    ResponseEntity<CardLimitResponse> response = null;
    try {
      response = new ResponseEntity<>(objectMapper.readValue(
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"cardDetail\" : {    \"overseasMonthlyLimit\" : 8.097541212E7,    \"dailyLimit\" : 8.097541212E7,    \"overseasDailyLimit\" : 8.097541212E7  }}",
          CardLimitResponse.class), HttpStatus.OK);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return response;
  }

  @RequestMapping(value = "/{customerId}/cards/{cardId}/resetPin", produces = {"application/json"}, consumes = {"multipart/form-data"},
      method = RequestMethod.PUT)
  public ResponseEntity<CardDetailResponse> resetPin(@PathVariable(value = "customerId", required = true) Integer customerId,
      @PathVariable(value = "cardId", required = true) Integer cardId, @RequestParam(value = "resetType", required = true) String resetType,
      @RequestParam(value = "newPin", required = true) String newPin) {
    ResponseEntity<CardDetailResponse> response = null;
    try {
      response = new ResponseEntity<>(objectMapper.readValue(
          "{  \"result\" : {    \"messageCode\" : \"messageCode\",    \"message\" : \"message\",    \"status\" : 0  },  \"cardDetail\" : {    \"branchId\" : \"branchId\",    \"product\" : \"product\",    \"closingBalance\" : 6.027456183070403,    \"branchName\" : \"branchName\",    \"type\" : \"CREDIT\",    \"accountNumber\" : \"accountNumber\",    \"cardIssuer\" : \"Visa\",    \"amountDue\" : 1.4658129805029452,    \"accountId\" : \"accountId\",    \"paymentDueDate\" : \"2000-01-23T04:56:07.000+00:00\",    \"lastStatementDate\" : \"2000-01-23T04:56:07.000+00:00\",    \"productCode\" : \"productCode\",    \"lastStatementBalance\" : 3.616076749251911,    \"oversearCardActivated\" : true,    \"minimumPayment\" : 5.962133916683182,    \"creditLimit\" : 5.637376656633329,    \"displayAccountNumber\" : \"123xxxx890\",    \"permanentCreditLimit\" : 9.301444243932576,    \"category\" : \"category\",    \"openingBalance\" : 0.8008281904610115,    \"availableCreditLimit\" : 2.3021358869347655,    \"temporaryCreditLimit\" : 7.061401241503109,    \"status\" : \"ACTIVE\"  }}",
          CardDetailResponse.class), HttpStatus.OK);
    } catch (IOException e) {
      logger.error("Couldn't serialize response for content type application/json", e);
      response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return response;
  }

  @RequestMapping(value = "/{customerId}/cards/{cardId}/payment", produces = {"application/json"}, consumes = {"multipart/form-data"},
      method = RequestMethod.PUT)
  public ResponseEntity<Void> cardPayment(@PathVariable(name = "customerId", required = true) Integer customerId,
      @PathVariable(name = "cardId", required = true) Integer cardId, @RequestParam(value = "amount", required = true) Double amount,
      @RequestParam(value = "date", required = true) OffsetDateTime date,
      @RequestParam(value = "currency", required = true) String currency,
      @RequestParam(value = "remarks", required = true) String remarks) {
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
