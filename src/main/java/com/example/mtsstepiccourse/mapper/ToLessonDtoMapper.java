package com.example.mtsstepiccourse.mapper;

import com.example.mtsstepiccourse.dto.LessonDto;
import com.example.mtsstepiccourse.dto.LessonDtoToEdit;
import com.example.mtsstepiccourse.model.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ToLessonDtoMapper {

    @Mapping(source = "module.id", target = "moduleId")
    LessonDto lessonToLessonDto(Lesson lesson);
    LessonDto lessonDtoToEditToLessonDto(LessonDtoToEdit lessonDtoToEdit);
}
