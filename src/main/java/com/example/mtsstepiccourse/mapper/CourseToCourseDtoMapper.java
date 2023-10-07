package com.example.mtsstepiccourse.mapper;

import com.example.mtsstepiccourse.dto.CourseDto;
import com.example.mtsstepiccourse.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseToCourseDtoMapper {

    CourseDto courseToCourseDto(Course course);
}
