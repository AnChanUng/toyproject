package com.example.demo.controller;

import com.example.demo.entity.Board;
import com.example.demo.entity.Comment;
import com.example.demo.service.BoardService;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentService commentService;

    @GetMapping
    public String getAllBoards(Model model) {
        List<Board> boards = boardService.findAll();
        model.addAttribute("boards", boards);
        return "board";
    }

    @PostMapping
    public String createBoard(@RequestParam String title, @RequestParam String content) {
        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        boardService.save(board);
        return "redirect:/boards";
    }

    @GetMapping("/{id}")
    public String getBoardById(@PathVariable Long id, Model model) {
        boardService.findById(id).ifPresent(board -> {
            model.addAttribute("board", board);

            List<Comment> comments = commentService.getCommentsByBoardId(id);
            model.addAttribute("comments", comments);
        });
        return "board-detail";
    }

    @GetMapping("/{id}/edit")
    public String editBoard(@PathVariable Long id, Model model) {
        boardService.findById(id).ifPresent(board -> model.addAttribute("board", board));
        return "board-edit";
    }

    @PostMapping("/{id}/edit")
    public String updateBoard(@PathVariable Long id, @RequestParam String title, @RequestParam String content) {
        Board board = new Board();
        board.setId(id);
        board.setTitle(title);
        board.setContent(content);
        boardService.save(board);
        return "redirect:/boards";
    }

    @GetMapping("/{id}/delete")
    public String deleteBoard(@PathVariable Long id) {
        boardService.deleteById(id);
        return "redirect:/boards";
    }
}