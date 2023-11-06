package com.example.mtsstepiccourse.mapper;

import com.example.mtsstepiccourse.dto.ModuleDto;
import com.example.mtsstepiccourse.dto.ModuleToEditDto;
import com.example.mtsstepiccourse.model.Module;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ModuleDtoMapper {

    @Mapping(source = "course.id", target = "courseId")
    ModuleDto moduleToModuleDto(Module module);
    ModuleToEditDto moduleToModuleToEditDto(Module module);
}
