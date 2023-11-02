package com.example.mtsstepiccourse.mapper;

import com.example.mtsstepiccourse.dto.CourseDto;
import com.example.mtsstepiccourse.dto.CourseRequestToCreate;
import com.example.mtsstepiccourse.dto.CourseRequestToUpdate;
import com.example.mtsstepiccourse.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ToCourseDtoMapper {

    CourseDto courseToCourseDto(Course course);
    CourseDto courseRequestToCreateToCourseDto(CourseRequestToCreate request);
    CourseDto courseRequestToUpdateToCourseDto(CourseRequestToUpdate request);
}
