package uk.co.chonochonov.springfundamentalsworkshop.service;

import uk.co.chonochonov.springfundamentalsworkshop.model.service.ExerciseServiceModel;

import java.util.List;

public interface ExerciseService {
    ExerciseServiceModel findByName(String name);
    ExerciseServiceModel addExercise(ExerciseServiceModel exerciseServiceModel);
    List<String> findAllExerciseNames();
}
