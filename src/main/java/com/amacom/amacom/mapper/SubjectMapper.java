package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.SubjectDTO;
import com.amacom.amacom.model.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.Map;

@Mapper
public interface SubjectMapper {

    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    Subject toSubject(SubjectDTO subjectDTO);

    @Mapping(target = "idSubjectParent", source = "subjectParent.id")
    @Mapping(target = "idResultadosAsociados", source = "resultadosAsociados.id")
    SubjectDTO toSubjectDTO(Subject subject);

    static Map<String, String> getClavesToSort() {
        Map<String, String> clavesToSort = new HashMap<>();
        clavesToSort.put("nombre", "nombre");
        clavesToSort.put("fechaHoraCreacion", "fechaHoraCreacion");
        return clavesToSort;
    }


}
