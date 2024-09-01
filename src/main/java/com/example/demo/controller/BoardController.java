package com.example.demo.controller;

import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
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

    @GetMapping
    public String getAllBoards(Model model) {
        List<Board> boards = boardService.findAll();
        model.addAttribute("boards", boards);
        return "board"; // This should match board.html in src/main/resources/templates
    }

    @PostMapping
    public String createBoard(@RequestParam String title, @RequestParam String content) {
        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        boardService.save(board);
        return "redirect:/boards"; // Redirect to the list of boards
    }

    @GetMapping("/{id}")
    public String getBoardById(@PathVariable Long id, Model model) {
        boardService.findById(id).ifPresent(board -> model.addAttribute("board", board));
        return "board-detail"; // This should match board-detail.html in src/main/resources/templates
    }

    @GetMapping("/{id}/edit")
    public String editBoard(@PathVariable Long id, Model model) {
        boardService.findById(id).ifPresent(board -> model.addAttribute("board", board));
        return "board-edit"; // This should match board-edit.html in src/main/resources/templates
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