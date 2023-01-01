package com.pcc.carrental.controller;

import com.pcc.carrental.request.RentalTransactionRequest;
import com.pcc.carrental.request.UpdateRentalTransactionRequest;
import com.pcc.carrental.response.RentalTransactionDTO;
import com.pcc.carrental.service.RentalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(tags = "APIs for rental")
@Controller
@RequestMapping("/api/v1/rental")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @ApiOperation(value = "start a new rental transaction")
    @PostMapping("/transaction")
    ResponseEntity<Long> startTransaction(@RequestBody RentalTransactionRequest request) {
        Long trxId = rentalService.newTransaction(request);
        return ResponseEntity.ok(trxId);
    }

    @ApiOperation(value = "update a rental transaction")
    @PutMapping("/transaction")
    ResponseEntity<String> updateTransaction(@RequestBody UpdateRentalTransactionRequest request) {
        rentalService.updateTransaction(request);
        return ResponseEntity.ok("");
    }

    @ApiOperation(value = "get a rental transaction via its id")
    @GetMapping("/transaction/{trx_id}")
    ResponseEntity<RentalTransactionDTO> getTransaction(@PathVariable(name = "trx_id") Long trxId) {
        return ResponseEntity.ok(rentalService.getTransaction(trxId));
    }

}
