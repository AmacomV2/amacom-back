package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.PersonBabys;
import org.springframework.stereotype.Service;

@Service
public interface IPersonBabysService {

    PersonBabys findById(Long id);

    PersonBabys create(PersonBabys personBabys);

    PersonBabys update(PersonBabys personBabys);

    void deleteById(Long id);

}
