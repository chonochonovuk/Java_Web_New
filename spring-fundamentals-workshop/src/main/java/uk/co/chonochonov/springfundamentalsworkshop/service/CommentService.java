package uk.co.chonochonov.springfundamentalsworkshop.service;

import uk.co.chonochonov.springfundamentalsworkshop.model.service.CommentServiceModel;

public interface CommentService {
    CommentServiceModel addComment(CommentServiceModel commentServiceModel);

    Double avgScore();
}
