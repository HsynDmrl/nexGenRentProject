package renacartest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexgencarrental.nexGenCarRental.controllers.BrandsController;
import com.nexgencarrental.nexGenCarRental.services.abstracts.BrandService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand.AddBrandRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.brand.UpdateBrandRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.brand.GetBrandFilterResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.brand.GetBrandListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.brand.GetBrandResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BrandTest {

    @Mock
    private BrandService brandService;

    @InjectMocks
    private BrandsController brandsController;

    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllBrands() {
        // Given
        List<GetBrandListResponse> expectedBrands = new ArrayList<>();
        when(brandService.getAll()).thenReturn(expectedBrands);

        // When
        List<GetBrandListResponse> actualBrands = brandsController.getAll();

        // Then
        assertEquals(expectedBrands, actualBrands);
    }

    @Test
    void getBrandById() {
        // Given
        int id = 1;
        GetBrandResponse expectedBrand = new GetBrandResponse();
        when(brandService.getById(id)).thenReturn(expectedBrand);

        // When
        GetBrandResponse actualBrand = brandsController.getById(id);

        // Then
        assertEquals(expectedBrand, actualBrand);
    }

    @Test
    void addBrand() throws IOException {
        // Given
        AddBrandRequest addBrandRequest = new AddBrandRequest();
        MultipartFile logoFile = mock(MultipartFile.class);
        GetBrandFilterResponse expectedResponse = new GetBrandFilterResponse();
        when(objectMapper.readValue(anyString(), eq(AddBrandRequest.class))).thenReturn(addBrandRequest);
        doNothing().when(brandService).customAdd(addBrandRequest, logoFile);
        ResponseEntity<?> expectedResponseEntity = ResponseEntity.ok("Brand successfully added with logo.");

        // When
        ResponseEntity<?> responseEntity = brandsController.add("{}", logoFile);

        // Then
        assertEquals(expectedResponseEntity.getStatusCode(), responseEntity.getStatusCode());
        assertEquals(expectedResponseEntity.getBody(), responseEntity.getBody());
    }

    @Test
    void updateBrand() throws IOException {
        // Given
        UpdateBrandRequest updateBrandRequest = new UpdateBrandRequest();
        MultipartFile logoFile = mock(MultipartFile.class);
        ResponseEntity<?> expectedResponseEntity = ResponseEntity.ok("Brand successfully updated with new logo.");
        when(objectMapper.readValue(anyString(), eq(UpdateBrandRequest.class))).thenReturn(updateBrandRequest);
        doNothing().when(brandService).customUpdate(updateBrandRequest, logoFile);

        // When
        ResponseEntity<?> responseEntity = brandsController.update("{}", logoFile);

        // Then
        assertEquals(expectedResponseEntity.getStatusCode(), responseEntity.getStatusCode());
        assertEquals(expectedResponseEntity.getBody(), responseEntity.getBody());
    }

    @Test
    void deleteBrand() {
        // Given
        int id = 1;

        // When
        brandsController.delete(id);

        // Then
        verify(brandService, times(1)).customDelete(id);
    }
}
