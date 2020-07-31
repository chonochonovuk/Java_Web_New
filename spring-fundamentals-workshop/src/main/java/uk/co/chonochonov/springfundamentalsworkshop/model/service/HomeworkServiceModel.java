package uk.co.chonochonov.springfundamentalsworkshop.model.service;

import uk.co.chonochonov.springfundamentalsworkshop.model.entity.Exercise;
import uk.co.chonochonov.springfundamentalsworkshop.model.entity.User;

import java.time.LocalDateTime;

public class HomeworkServiceModel extends BaseServiceModel {
    private LocalDateTime addedOn;
    private String gitAddress;
    private UserServiceModel author;
    private ExerciseServiceModel exerciseServiceModel;

    public HomeworkServiceModel() {
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }

    public String getGitAddress() {
        return gitAddress;
    }

    public void setGitAddress(String gitAddress) {
        this.gitAddress = gitAddress;
    }

    public UserServiceModel getAuthor() {
        return author;
    }

    public void setAuthor(UserServiceModel author) {
        this.author = author;
    }

    public ExerciseServiceModel getExerciseServiceModel() {
        return exerciseServiceModel;
    }

    public void setExerciseServiceModel(ExerciseServiceModel exerciseServiceModel) {
        this.exerciseServiceModel = exerciseServiceModel;
    }
}
