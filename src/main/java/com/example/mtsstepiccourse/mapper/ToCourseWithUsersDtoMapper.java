package com.example.mtsstepiccourse.mapper;

import com.example.mtsstepiccourse.dto.CourseWithUsersDto;
import com.example.mtsstepiccourse.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ToCourseWithUsersDtoMapper {

    CourseWithUsersDto courseToCourseWithUsersDto(Course course);
}
