package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApiPathConstants;
import com.nexgencarrental.nexGenCarRental.services.abstracts.InvoiceService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.invoice.AddInvoiceRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.invoice.DeleteInvoiceRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.invoice.UpdateInvoiceRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.invoice.GetInvoiceListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.invoice.GetInvoiceResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPathConstants.INVOICE_BASE_URL)
@AllArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping(ApiPathConstants.GET_ALL_INVOICES)
    @ResponseStatus(HttpStatus.OK)
    public List<GetInvoiceListResponse> getAll() {
        return invoiceService.getAll();
    }

    @GetMapping(ApiPathConstants.GET_INVOICE_BY_ID)
    @ResponseStatus(HttpStatus.OK)
    public GetInvoiceResponse getById(int id) {
        return invoiceService.getById(id);
    }

    @PostMapping(ApiPathConstants.ADD_INVOICE)
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddInvoiceRequest addInvoiceRequest) {
        this.invoiceService.customAdd(addInvoiceRequest);
    }

    @PutMapping(ApiPathConstants.UPDATE_INVOICE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody @Valid UpdateInvoiceRequest updateInvoiceRequest) {
        invoiceService.customUpdate(updateInvoiceRequest);
    }

    @DeleteMapping(ApiPathConstants.DELETE_INVOICE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody @Valid DeleteInvoiceRequest deleteInvoiceRequest) {
        invoiceService.customDelete(deleteInvoiceRequest);
    }
}
