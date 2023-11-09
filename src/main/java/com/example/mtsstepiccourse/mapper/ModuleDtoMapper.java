package com.example.mtsstepiccourse.mapper;

import com.example.mtsstepiccourse.dto.ModuleDto;
import com.example.mtsstepiccourse.dto.ModuleSimpleDto;
import com.example.mtsstepiccourse.dto.ModuleToEditDto;
import com.example.mtsstepiccourse.model.Module;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = LessonDtoMapper.class)
public interface ModuleDtoMapper {

    ModuleSimpleDto moduleToModuleSimpleDto(Module module);

    ModuleDto moduleToModuleDto(Module module);

    @Mapping(source = "course.id", target = "courseId")
    ModuleToEditDto moduleToModuleToEditDto(Module module);


}
