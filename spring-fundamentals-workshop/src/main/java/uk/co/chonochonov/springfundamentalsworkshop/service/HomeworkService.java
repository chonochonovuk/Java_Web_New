package uk.co.chonochonov.springfundamentalsworkshop.service;

import uk.co.chonochonov.springfundamentalsworkshop.model.service.HomeworkServiceModel;

public interface HomeworkService {
    HomeworkServiceModel addHomework(HomeworkServiceModel homeworkServiceModel);

    HomeworkServiceModel findOneToCheck();

    HomeworkServiceModel findHomework(HomeworkServiceModel homework);
}
