package uk.co.chonochonov.springfundamentalsworkshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.chonochonov.springfundamentalsworkshop.model.entity.Exercise;
import uk.co.chonochonov.springfundamentalsworkshop.model.service.ExerciseServiceModel;
import uk.co.chonochonov.springfundamentalsworkshop.model.service.UserServiceModel;
import uk.co.chonochonov.springfundamentalsworkshop.repository.ExerciseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ExerciseServiceModel findByName(String name) {

        return this.exerciseRepository.findByName(name)
                .map(exercise -> this.modelMapper.map(exercise,ExerciseServiceModel.class))
                .orElse(null);
    }

    @Override
    public ExerciseServiceModel addExercise(ExerciseServiceModel exerciseServiceModel) {
        Exercise exercise = this.modelMapper.map(exerciseServiceModel,Exercise.class);
        return this.modelMapper.map(this.exerciseRepository.saveAndFlush(exercise), ExerciseServiceModel.class);
    }

    @Override
    public List<String> findAllExerciseNames() {
        return this.exerciseRepository.findAll()
                .stream()
                .map(exercise -> exercise.getName())
                .collect(Collectors.toList());
    }
}
