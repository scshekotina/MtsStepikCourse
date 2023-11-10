package com.example.mtsstepiccourse.service.user;

import com.example.mtsstepiccourse.model.Course;
import com.example.mtsstepiccourse.model.User;
import com.example.mtsstepiccourse.repository.CourseRepository;
import com.example.mtsstepiccourse.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@AllArgsConstructor
@Service
public class UserCoursesServiceImpl implements UserCoursesService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Override
    public Set<Course> getCourses(Long userId) {
        User user = userRepository.findByIdWithCourses(userId).orElseThrow();
        return user.getCourses();
    }

    @Override
    @Transactional
    public void addCourse(Long userId, Long courseId) {
        User user = userRepository.findById(userId).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();
        user.addCourse(course);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void removeCourse(Long userId, Long courseId) {
        User user = userRepository.findById(userId).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();
        user.removeCourse(course);
        userRepository.save(user);
    }
}
