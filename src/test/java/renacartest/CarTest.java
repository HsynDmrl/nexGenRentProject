package renacartest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexgencarrental.nexGenCarRental.NexGenCarRentalApplication;
import com.nexgencarrental.nexGenCarRental.controllers.CarsController;
import com.nexgencarrental.nexGenCarRental.services.abstracts.CarService;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.AddCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.car.UpdateCarRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarFilterResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarListResponse;
import com.nexgencarrental.nexGenCarRental.services.dtos.responses.car.GetCarResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = NexGenCarRentalApplication.class)
public class CarTest {

    @Mock
    private CarService carService;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private CarsController carsController;

    @Test
    public void testGetAllCars() {
        // Given
        List<GetCarListResponse> expectedCars = new ArrayList<>();
        when(carService.getAll()).thenReturn(expectedCars);

        // When
        List<GetCarListResponse> actualCars = carsController.getAll();

        // Then
        assertEquals(expectedCars, actualCars);
    }

    @Test
    public void testGetCarById() {
        // Given
        int id = 1;
        GetCarResponse expectedCar = new GetCarResponse();
        when(carService.getById(id)).thenReturn(expectedCar);

        // When
        GetCarResponse actualCar = carsController.getById(id);

        // Then
        assertEquals(expectedCar, actualCar);
    }

    @Test
    public void testAddCar() throws IOException {
        // Given
        AddCarRequest addCarRequest = new AddCarRequest();
        List<MultipartFile> images = new ArrayList<>();
        GetCarFilterResponse expectedResponse = new GetCarFilterResponse();
        when(objectMapper.readValue(anyString(), eq(AddCarRequest.class))).thenReturn(addCarRequest);
        when(carService.customAdd(addCarRequest, images)).thenReturn(expectedResponse);

        // When
        ResponseEntity<?> responseEntity = carsController.add("{}", images);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    public void testUpdateCar() {
        // Given
        UpdateCarRequest updateCarRequest = new UpdateCarRequest();
        GetCarFilterResponse expectedResponse = new GetCarFilterResponse(); // Değişiklik burada yapıldı
        when(carService.customUpdate(updateCarRequest)).thenReturn(expectedResponse);

        // When
        ResponseEntity<?> responseEntity = carsController.update(updateCarRequest);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


    @Test
    public void testDeleteCar() {
        // Given
        int id = 1;

        // When
        carsController.delete(id);

        // Then
        verify(carService, times(1)).customDelete(id);
    }
}
