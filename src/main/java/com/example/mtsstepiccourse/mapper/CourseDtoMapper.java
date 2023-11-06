package com.example.mtsstepiccourse.mapper;

import com.example.mtsstepiccourse.dto.CourseDto;
import com.example.mtsstepiccourse.dto.CourseToEditDto;
import com.example.mtsstepiccourse.dto.CourseSimpleDto;
import com.example.mtsstepiccourse.dto.CourseWithUsersDto;
import com.example.mtsstepiccourse.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseDtoMapper {

    CourseDto courseToCourseDto(Course course);
    CourseToEditDto courseToCourseToEditDto(Course course);
    CourseSimpleDto courseToCourseSimpleDto(Course course);
    CourseWithUsersDto courseToCourseWithUsersDto(Course course);
}
