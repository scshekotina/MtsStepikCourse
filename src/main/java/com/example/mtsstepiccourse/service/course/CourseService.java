package com.example.mtsstepiccourse.service.course;

import com.example.mtsstepiccourse.model.Course;
import com.example.mtsstepiccourse.service.UpdatableEntityService;

import java.util.List;

public interface CourseService extends UpdatableEntityService<Course> {

    List<Course> simpleFindAll();

    List<Course> findByTitleLike(String prefix);

}
