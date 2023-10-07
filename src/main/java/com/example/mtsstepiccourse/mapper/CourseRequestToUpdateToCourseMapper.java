package com.example.mtsstepiccourse.mapper;

import com.example.mtsstepiccourse.dto.CourseRequestToUpdate;
import com.example.mtsstepiccourse.model.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseRequestToUpdateToCourseMapper {

    Course courseRequestToCreateToCourse(CourseRequestToUpdate request);
}
