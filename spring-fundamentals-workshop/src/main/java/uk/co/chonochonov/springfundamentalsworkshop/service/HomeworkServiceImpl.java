package uk.co.chonochonov.springfundamentalsworkshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.chonochonov.springfundamentalsworkshop.model.entity.Homework;
import uk.co.chonochonov.springfundamentalsworkshop.model.service.HomeworkServiceModel;
import uk.co.chonochonov.springfundamentalsworkshop.repository.HomeworkRepository;

import java.util.Comparator;

@Service
public class HomeworkServiceImpl implements HomeworkService {
    private final HomeworkRepository homeworkRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeworkServiceImpl(HomeworkRepository homeworkRepository, ModelMapper modelMapper) {
        this.homeworkRepository = homeworkRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public HomeworkServiceModel addHomework(HomeworkServiceModel homeworkServiceModel) {
        Homework homework = this.modelMapper.map(homeworkServiceModel,Homework.class);
        this.homeworkRepository.saveAndFlush(homework);
        return this.modelMapper.map(homework,HomeworkServiceModel.class);
    }

    @Override
    public HomeworkServiceModel findOneToCheck() {

        return this.homeworkRepository
                .findAll()
                .stream()
                .min(Comparator.comparingInt(c -> c.getComments().size()))
                .map(homework -> this.modelMapper.map(homework,HomeworkServiceModel.class))
                .orElse(null);
    }

    @Override
    public HomeworkServiceModel findHomework(HomeworkServiceModel homeworkServiceModel) {
        Homework homework = this.homeworkRepository.findById(homeworkServiceModel.getId())
                .map(homework1 -> this.modelMapper.map(homework1,Homework.class))
                .orElse(null);
        return this.modelMapper.map(homework,HomeworkServiceModel.class);
    }
}
