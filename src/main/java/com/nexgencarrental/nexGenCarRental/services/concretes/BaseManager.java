package com.nexgencarrental.nexGenCarRental.services.concretes;

import com.nexgencarrental.nexGenCarRental.core.utilities.constants.ApplicationConstants;
import com.nexgencarrental.nexGenCarRental.core.utilities.mappers.ModelMapperService;
import com.nexgencarrental.nexGenCarRental.entities.abstracts.BaseEntity;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Car;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Employee;
import com.nexgencarrental.nexGenCarRental.entities.concretes.Model;
import com.nexgencarrental.nexGenCarRental.entities.concretes.User;
import com.nexgencarrental.nexGenCarRental.services.abstracts.BaseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public abstract class BaseManager<T, R extends JpaRepository<T, Integer>,
        G, L, A, U> implements BaseService<T, R, G, L, A, U> {

    public R repository;
    public ModelMapperService modelMapperService;
    public Class<G> responseType;
    public Class<L> listResponseType;
    private final Class<T> entityClass;
    public Class<A> requestType;
    public Class<U> updateRequestType;

    @Override
    public void delete(int id) {
        T entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(entityClass.getSimpleName() + " with ID " + id + " not found."));
        repository.delete(entity);
    }

    @Override
    public G getById(int id) {
        T entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(entityClass.getSimpleName() + " with ID " + id + " not found."));
        return modelMapperService.forResponse().map(entity, responseType);
    }

    @Override
    public List<L> getAll() {
        List<T> entities = repository.findAll();

        if (entities.isEmpty())
            throw new EntityNotFoundException(ApplicationConstants.NO_ENTITIES_FOUND);

        return entities.stream()
                .map(entity -> modelMapperService.forResponse().map(entity, listResponseType))
                .collect(Collectors.toList());
    }

    @Override
    public void add(A request, Class<T> entityClass) {
        T entity = modelMapperService.forRequest().map(request, entityClass);
        if (entity instanceof Car) {
            Car carEntity = (Car) entity;
            carEntity.setPlate(carEntity.getPlate().replaceAll("\\s", ""));
        } else if (entity instanceof Employee) {
            Employee employeeEntity = (Employee) entity;
            employeeEntity.setId(0);
        } else if (entity instanceof Model) {
            Model modelEntity = (Model) entity;
            modelEntity.setId(0);
        } else if (entity instanceof User) {
            User userEntity = (User) entity;
            userEntity.setNationalityId(userEntity.getNationalityId().replaceAll("\s", ""));
        }
        repository.save(entity);
    }

    @SneakyThrows
    @Override
    public void update(U updateRequest, Class<T> entityClass) {
        Integer entityId = (Integer) updateRequest.getClass().getMethod("getId").invoke(updateRequest);

        T existingEntity = repository.findById(entityId)
                .orElseThrow(() -> new EntityNotFoundException(entityClass.getSimpleName() + " with ID " + entityId + " not found."));

        T entity = modelMapperService.forRequest().map(updateRequest, entityClass);

        if (entity instanceof BaseEntity) {
            BaseEntity existingBaseEntity = (BaseEntity) existingEntity;
            BaseEntity updatedBaseEntity = (BaseEntity) entity;
            updatedBaseEntity.setCreatedDate(existingBaseEntity.getCreatedDate());
        }

        if (entity instanceof Car) {
            Car carEntity = (Car) entity;
            carEntity.setPlate(carEntity.getPlate().replaceAll("\\s", ""));
        } else if (entity instanceof User) {
            User userEntity = (User) entity;
            userEntity.setNationalityId(userEntity.getNationalityId().replaceAll("\\s", ""));
        }
        repository.save(entity);
    }
}
