package com.example.mtsstepiccourse.controller;

import com.example.mtsstepiccourse.dto.*;
import com.example.mtsstepiccourse.mapper.ToCourseDtoMapper;
import com.example.mtsstepiccourse.mapper.ToCourseWithUsersDtoMapper;
import com.example.mtsstepiccourse.mapper.ToLessonDtoMapper;
import com.example.mtsstepiccourse.model.Course;
import com.example.mtsstepiccourse.service.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNullElse;

@AllArgsConstructor
@RestController
@RequestMapping
public class CourseController {
    private final CourseService courseService;
    private final ToCourseDtoMapper toCourseDtoMapper;
    private final ToCourseWithUsersDtoMapper toCourseWithUsersDtoMapper;
    private final ToLessonDtoMapper toLessonDtoMapper;

    @GetMapping("/courses")
    public List<CourseDto> courseTable() {
        return courseService.findAll().stream()
                .map(toCourseDtoMapper::courseToCourseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/courses/{id}")
    public CourseWithUsersDto getCourse(@PathVariable("id") Long id) {
        return toCourseWithUsersDtoMapper.courseToCourseWithUsersDto(courseService.findById(id).orElseThrow());
    }

    @PutMapping("/courses/{id}")
    public synchronized void updateCourse(@PathVariable Long id,
                             @Valid @RequestBody CourseRequestToUpdate request) {
        CourseDto courseDto = toCourseDtoMapper.courseRequestToUpdateToCourseDto(request);
        courseService.update(id, courseDto).orElseThrow();
    }

    @PostMapping("/courses")
    public CourseDto createCourse(@Valid @RequestBody CourseRequestToCreate request) {
        CourseDto courseDto = toCourseDtoMapper.courseRequestToCreateToCourseDto(request);
        Course course = courseService.create(courseDto);
        return toCourseDtoMapper.courseToCourseDto(course);
    }

    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteById(id).orElseThrow();
    }

    @GetMapping("/courses/filter")
    public List<CourseDto> getCoursesByTitlePrefix(@RequestParam(name = "titlePrefix", required = false) String titlePrefix) {
        return courseService.findByTitleLike(requireNonNullElse(titlePrefix, "")).stream()
                .map(toCourseDtoMapper::courseToCourseDto)
                .collect(Collectors.toList());
    }


    @GetMapping("/courses/{course_id}/lessons")
    public List<LessonDto> lessonTable(@PathVariable("course_id") Long courseId) {
        return courseService.getLessons(courseId).stream()
                .map(toLessonDtoMapper::lessonToLessonDto)
                .collect(Collectors.toList());
    }

//    @PostMapping("/courses/{module_id}/lessons")
//    public LessonDto addLesson(@PathVariable("module_id") Long moduleId, @Valid @RequestBody LessonDtoToEdit lessonDtoToEdit) {
//        LessonDto lessonDto = toLessonDtoMapper.lessonDtoToEditToLessonDto(lessonDtoToEdit);
//        lessonDto.setModuleId(moduleId);
//        Lesson lesson = courseService.addModule(moduleId, lessonDto);
//        return toLessonDtoMapper.lessonToLessonDto(lesson);
//    }

}
