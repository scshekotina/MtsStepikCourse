package com.example.mtsstepiccourse.service.course;

import com.example.mtsstepiccourse.model.Course;
import com.example.mtsstepiccourse.model.Module;
import com.example.mtsstepiccourse.model.UpdatableAndDeletableEntity;
import com.example.mtsstepiccourse.repository.CourseRepository;
import com.example.mtsstepiccourse.repository.ModuleRepository;
import com.example.mtsstepiccourse.service.UpdatableEntityServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Course findById(Long id) {
        CourseRepository repository1 = (CourseRepository) repository;
        return repository1.findByIdWithLessons(id).orElseThrow();
    }

    @Override
    protected void deleteLinkedEntities(Course entity, Course entityFromRepo) {
        if (entityFromRepo != null && entityFromRepo.getModules() != null) {
            Set<Long> modulesFromRepoIds = entityFromRepo.getModules().stream().map(UpdatableAndDeletableEntity::getId)
                    .collect(Collectors.toSet());

            Set<Long> forDelete;
            if (entity.getModules() != null) {
                Set<Long> modulesIds = entity.getModules().stream().map(UpdatableAndDeletableEntity::getId)
                        .collect(Collectors.toSet());

                forDelete = modulesFromRepoIds.stream().filter(id -> !modulesIds.contains(id)).collect(Collectors.toSet());
            } else {
                forDelete = modulesFromRepoIds;
            }

            forDelete.forEach(id -> {
                Module module = moduleRepository.findById(id).orElseThrow();
                module.setCourse(null);
                module.markAsUpdated();
                moduleRepository.save(module);

            });
        }
    }

    @Override
    protected void saveLinkedEntities(Course entity) {
        if(entity.getModules() != null) {
            List<Module> modules = entity.getModules().stream()
                    .map(m -> {
                        Module moduleForAdding = moduleRepository.findByIdAndDeletingDateIsNull(m.getId()).orElseThrow();
                        moduleForAdding.setCourse(entity);
                        moduleForAdding.markAsUpdated();
                        return moduleForAdding;
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
