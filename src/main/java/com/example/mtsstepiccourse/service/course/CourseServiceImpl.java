package com.example.mtsstepiccourse.service.course;

import com.example.mtsstepiccourse.model.Course;
import com.example.mtsstepiccourse.model.Module;
import com.example.mtsstepiccourse.repository.CourseRepository;
import com.example.mtsstepiccourse.repository.ModuleRepository;
import com.example.mtsstepiccourse.service.UpdatableEntityServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl extends UpdatableEntityServiceImpl<Course> implements CourseService {

    private final ModuleRepository moduleRepository;


    public CourseServiceImpl(ModuleRepository moduleRepository, CourseRepository repository) {
        super(repository);
        this.moduleRepository = moduleRepository;
    }

    @Override
    public List<Course> simpleFindAll() {
        CourseRepository repository1 = (CourseRepository) repository;
        return repository1.findAllByDeletingDateIsNullOrderByTitle();
    }

    @Override
    public void updateLinkedEntities(Course entity) {
        if (entity.getModules() != null) {
            List<Module> modules = entity.getModules().stream()
                    .map(m -> {
                        Module fromRepo = moduleRepository.findByIdAndDeletingDateIsNull(m.getId()).orElseThrow();
                        fromRepo.setCourse(entity);
                        fromRepo.markAsUpdated();
                        return fromRepo;
                    }).toList();
            entity.setModules(modules);
        }
    }

    @Override
    public List<Course> findByTitleLike(String title) {
        CourseRepository repository1 = (CourseRepository) repository;
        return repository1.findByTitleLike("%" + title + "%");
    }

}
