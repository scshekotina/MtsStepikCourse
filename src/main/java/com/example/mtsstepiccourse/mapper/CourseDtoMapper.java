package com.example.mtsstepiccourse.mapper;

import com.example.mtsstepiccourse.dto.*;
import com.example.mtsstepiccourse.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = ModuleDtoMapper.class)
public interface CourseDtoMapper {

    CourseSimpleDto courseToCourseSimpleDto(Course course);

    CourseDto courseToCourseDto(Course course);
    CourseToEditDto courseToCourseToEditDto(Course course);
    CourseOnlyModulesDto courseToCourseOnlyModulesDtoDto(Course course);
    CourseWithUsersDto courseToCourseWithUsersDto(Course course);
}
