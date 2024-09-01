package com.example.demo.service;

import com.example.demo.entity.Board;
import com.example.demo.entity.Comment;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BoardRepository boardRepository;

    public void addComment(Long boardId, String content) {
        Board board = boardRepository.findById(boardId).
                orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));

        Comment comment = new Comment();
        comment.setBoard(board);
        comment.setContent(content);

        commentRepository.save(comment);
    }

    public List<Comment> getCommentsByBoardId(Long boardId) {
        return commentRepository.findByBoardId(boardId);
    }

    public void deleteCommentByBoardId(Long boardId) {
        List<Comment> comments = commentRepository.findByBoardId(boardId);
        commentRepository.deleteAll(comments);
    }
}
