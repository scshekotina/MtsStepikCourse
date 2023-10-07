package com.example.mtsstepiccourse.mapper;

import com.example.mtsstepiccourse.dto.CourseRequestToCreate;
import com.example.mtsstepiccourse.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseRequestToCreateToCourseMapper {

    Course courseRequestToCreateToCourse(CourseRequestToCreate request);
}
