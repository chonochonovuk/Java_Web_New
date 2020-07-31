package uk.co.chonochonov.springfundamentalsworkshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uk.co.chonochonov.springfundamentalsworkshop.model.entity.Comment;
import uk.co.chonochonov.springfundamentalsworkshop.model.service.CommentServiceModel;
import uk.co.chonochonov.springfundamentalsworkshop.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentServiceModel addComment(CommentServiceModel commentServiceModel) {
        Comment comment = this.modelMapper.map(commentServiceModel,Comment.class);
        this.commentRepository.saveAndFlush(comment);
        return this.modelMapper.map(comment,CommentServiceModel.class);
    }

    @Override
    public Double avgScore() {

        return this.commentRepository.findAll()
                .stream()
                .mapToDouble(Comment::getScore)
                .average()
                .orElse(0D);
    }
}
