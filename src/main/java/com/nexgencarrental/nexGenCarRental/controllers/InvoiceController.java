package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApiPathConstants;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Invoice;
import com.nexgencarrental.nexGenCarRental.services.abstracts.InvoiceService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.invoice.AddInvoiceRequest;
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
    public List<GetInvoiceListResponse> getAll() {
        return invoiceService.getAll();
    }

    @GetMapping(ApiPathConstants.GET_INVOICE_BY_ID)
    public GetInvoiceResponse getById(int id) {
        return invoiceService.getById(id);
    }

    @PostMapping(ApiPathConstants.ADD_INVOICE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddInvoiceRequest addInvoiceRequest) {
        this.invoiceService.add(addInvoiceRequest, Invoice.class);
    }

    @PutMapping(ApiPathConstants.UPDATE_INVOICE)
    public void update(@RequestBody @Valid UpdateInvoiceRequest updateInvoiceRequest) {
        invoiceService.update(updateInvoiceRequest, Invoice.class);
    }

    @DeleteMapping(ApiPathConstants.DELETE_INVOICE)
    public void delete(@PathVariable int id) {
        invoiceService.delete(id);
    }

}
