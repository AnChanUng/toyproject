package com.example.demo.controller;

import com.example.demo.entity.Board;
import com.example.demo.entity.Comment;
import com.example.demo.service.BoardService;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ModelAndView getAllBoards(Model model) {

        ModelAndView modelAndView = new ModelAndView();

        List<Board> boards = boardService.findAll();
        model.addAttribute("boards", boards);

        modelAndView.setViewName("board");

        return modelAndView;
    }

    @PostMapping
    public ModelAndView createBoard(@RequestParam String title, @RequestParam String content) {

        ModelAndView modelAndView = new ModelAndView();

        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        boardService.save(board);

        modelAndView.setViewName("redirect:/boards");
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getBoardById(@PathVariable Long id, Model model) {

        ModelAndView modelAndView = new ModelAndView();

        boardService.findById(id).ifPresent(board -> {
            model.addAttribute("board", board);

            List<Comment> comments = commentService.getCommentsByBoardId(id);
            model.addAttribute("comments", comments);
        });

        modelAndView.setViewName("board-detail");
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editBoard(@PathVariable Long id, Model model) {

        ModelAndView modelAndView = new ModelAndView();

        boardService.findById(id).ifPresent(board -> model.addAttribute("board", board));
        modelAndView.setViewName("board-edit");

        return modelAndView;
    }

    @PostMapping("/{id}/edit")
    public ModelAndView updateBoard(@PathVariable Long id, @RequestParam String title, @RequestParam String content) {

        ModelAndView modelAndView = new ModelAndView();

        Board board = new Board();
        board.setId(id);
        board.setTitle(title);
        board.setContent(content);
        boardService.save(board);

        modelAndView.setViewName("redirect:/boards");
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteBoard(@PathVariable Long id) {

        ModelAndView modelAndView = new ModelAndView();

        boardService.deleteById(id);

        modelAndView.setViewName("redirect:/boards");
        return modelAndView;

    }

}