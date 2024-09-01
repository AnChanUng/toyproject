package com.example.demo.controller;

import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public String addComment(@RequestParam Long boardId, @RequestParam String content) {
        commentService.addComment(boardId, content);
        return "redirect:/boards/" + boardId;
    }

}
