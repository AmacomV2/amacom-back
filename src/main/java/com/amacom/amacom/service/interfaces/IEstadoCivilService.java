package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.EstadoCivil;
import com.amacom.amacom.model.Persona;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IEstadoCivilService {

    List<EstadoCivil> getAll();
}
