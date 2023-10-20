package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.AlarmSign;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.repository.IAlarmSignRepository;
import com.amacom.amacom.service.interfaces.IAlarmSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.UUID;

@Service
public class AlarmSignServiceImpl implements IAlarmSignService {

    private IAlarmSignRepository alarmSignRepository;

    private EntityManager entityManager;


    @Override
    public AlarmSign getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return alarmSignRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }


    @Override
    public AlarmSign findById(UUID id) {
        return this.alarmSignRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public AlarmSign create(AlarmSign alarmSign) {
        this.validarCreacion(alarmSign);
        alarmSign.setId(UUID.randomUUID());
        alarmSign.setFechaHoraCreacion(new Date());
        var alarmSignBD = this.alarmSignRepository.save(alarmSign);
        this.entityManager.flush();
        this.entityManager.refresh(alarmSignBD);
        return alarmSignBD;
    }

    @Override
    public AlarmSign update(AlarmSign alarmSign) {
        this.validarCreacion(alarmSign);
        var alarmSignBD = this.alarmSignRepository.findById(alarmSign.getId()).orElseThrow(DataNotFoundException::new);
        alarmSignBD.setNombre(alarmSign.getNombre());
        alarmSignBD.setTipoDescripcion(alarmSign.getTipoDescripcion());
        alarmSignBD.setLinkImagen(alarmSign.getLinkImagen());
        alarmSignBD.setEstado(alarmSign.getEstado());
        alarmSignBD.setTipo(alarmSign.getTipo());
        alarmSignBD.setFechaHoraModificacion(new Date());
        return this.alarmSignRepository.save(alarmSignBD);
    }

    @Override
    public void deleteById(UUID id) {
        var alarmSignBD = this.alarmSignRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.alarmSignRepository.deleteById(alarmSignBD.getId());
    }

    private void validarCreacion(AlarmSign alarmSign){

        var existsSimilar = this.alarmSignRepository.existsByNombre(alarmSign.getId(), alarmSign.getNombre());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidacionException("Ya existe un registro con este nombre");
    }


    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setAlarmSignRepository(IAlarmSignRepository alarmSignRepository) {
        this.alarmSignRepository = alarmSignRepository;
    }
}
