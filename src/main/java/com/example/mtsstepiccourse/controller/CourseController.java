package com.example.mtsstepiccourse.controller;

import com.example.mtsstepiccourse.dto.*;
import com.example.mtsstepiccourse.mapper.ToCourseDtoMapper;
import com.example.mtsstepiccourse.mapper.ToLessonDtoMapper;
import com.example.mtsstepiccourse.model.Course;
import com.example.mtsstepiccourse.model.Lesson;
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
    private final ToLessonDtoMapper toLessonDtoMapper;

    @GetMapping("/courses")
    public List<CourseDto> courseTable() {
        return courseService.findAll().stream()
                .map(toCourseDtoMapper::courseToCourseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/courses/{id}")
    public CourseDto getCourse(@PathVariable("id") Long id) {
        return toCourseDtoMapper.courseToCourseDto(courseService.findById(id).orElseThrow());
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


    @PostMapping("/courses/{course_id}/lessons")
    public LessonDto addLesson(@PathVariable("course_id") Long courseId, @Valid @RequestBody LessonDtoToEdit lessonDtoToEdit) {
        LessonDto lessonDto = toLessonDtoMapper.lessonDtoToEditToLessonDto(lessonDtoToEdit);
        lessonDto.setCourseId(courseId);
        Lesson lesson = courseService.addLesson(courseId, lessonDto);
        return toLessonDtoMapper.lessonToLessonDto(lesson);
    }


    @DeleteMapping("/courses/{course_id}/lessons/{id}")
    public void deleteLesson(@PathVariable("course_id") Long courseId, @PathVariable Long id) {
        courseService.removeLesson(courseId, id);
    }

}
