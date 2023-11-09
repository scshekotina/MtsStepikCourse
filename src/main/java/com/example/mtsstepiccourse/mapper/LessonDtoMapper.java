package com.example.mtsstepiccourse.mapper;

import com.example.mtsstepiccourse.dto.LessonDto;
import com.example.mtsstepiccourse.dto.LessonSimpleDto;
import com.example.mtsstepiccourse.dto.LessonToEditDto;
import com.example.mtsstepiccourse.model.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LessonDtoMapper {

    LessonSimpleDto lessonToLessonSimpleDto(Lesson lesson);

    LessonDto lessonToLessonDto(Lesson lesson);

    @Mapping(source = "module.id", target = "moduleId")
    LessonToEditDto lessonToLessonToEditDto(Lesson lesson);
}
