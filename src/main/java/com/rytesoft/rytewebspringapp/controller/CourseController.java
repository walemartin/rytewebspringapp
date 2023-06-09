package com.rytesoft.rytewebspringapp.controller;

import com.rytesoft.rytewebspringapp.model.Course;
import com.rytesoft.rytewebspringapp.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/courses/")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public Iterable<Course> getAllCourses() {
        return courseService.getCourses();
    }

    @GetMapping("{id}")
    public Optional<Course> getCourseById(@PathVariable("id") long courseId) {
        return courseService.getCourseById(courseId);
    }

    @GetMapping("category/{name}")
    public Iterable<Course> getCourseByCategory(@PathVariable("name") String category) {
        return courseService.getCoursesByCategory(category);
    }

    @PostMapping("cr")
    public Course createCourse(@Valid @RequestBody Course course) {
        return courseService.createCourse(course);
    }

    @PutMapping("{id}")
    public void updateCourse(@PathVariable("id") long courseId, @Valid @RequestBody Course course) {
        courseService.updateCourse(courseId, course);
    }

    @DeleteMapping("{id}")
    void deleteCourseById(@PathVariable("id") long courseId) {
        courseService.deleteCourseById(courseId);
    }

    @DeleteMapping
    void deleteCourses() {
        courseService.deleteCourses();
    }

}
