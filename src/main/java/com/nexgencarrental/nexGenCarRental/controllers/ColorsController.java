package com.nexgencarrental.nexGenCarRental.controllers;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApiPathConstants;
import com.nexgencarrental.nexGenCarRental.services.abstracts.ColorService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.color.AddColorRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.color.UpdateColorRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.color.GetColorListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.color.GetColorResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiPathConstants.COLORS_BASE_URL)
@AllArgsConstructor
public class ColorsController {
    private final ColorService colorService;

    @GetMapping(ApiPathConstants.GET_ALL_COLORS)
    @ResponseStatus(HttpStatus.OK)
    public List<GetColorListResponse> getAll() {
        return colorService.getAll();
    }

    @GetMapping(ApiPathConstants.GET_COLOR_BY_ID)
    @ResponseStatus(code = HttpStatus.OK)
    public GetColorResponse getById(int id) {
        return colorService.getById(id);
    }

    @PostMapping(ApiPathConstants.ADD_COLOR)
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid AddColorRequest addColorRequests) {
        colorService.customAdd(addColorRequests);
    }

    @PutMapping(ApiPathConstants.UPDATE_COLOR)
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody @Valid UpdateColorRequest updateColorRequest) {
        colorService.customUpdate(updateColorRequest);
    }

    @DeleteMapping(ApiPathConstants.DELETE_COLOR)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        colorService.customDelete(id);
    }
}
