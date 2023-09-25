package com.amacom.amacom.service.interfaces;


import com.amacom.amacom.model.auth.Rol;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IRolService {

    List<Rol> getAll();
}
