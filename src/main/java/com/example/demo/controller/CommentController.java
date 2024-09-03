package com.example.demo.controller;

import com.example.demo.entity.Comment;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public ModelAndView addComment(@RequestParam Long boardId, @RequestParam String content) {

        ModelAndView modelAndView = new ModelAndView();

        commentService.addComment(boardId, content);

        modelAndView.setViewName("redirect:/boards/" + boardId);
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editComment(@PathVariable Long id, Model model) {

        ModelAndView modelAndView = new ModelAndView();

        commentService.findById(id);

        modelAndView.setViewName("comment/edit");
        return modelAndView;
    }

//    @PostMapping("/{id}/edit")
//    public ModelAndView updateComment(@PathVariable Long id, @RequestParam String content) {
//
//        ModelAndView modelAndVIew = new ModelAndView();
//        Optional<Comment> comment = commentService.findById(id);
//
//        comment.setContent(content);
//        commentService.save(comment);
//
//        modelAndVIew.setViewName("redirect:/boards/" + comment.getBoardId());
//        return modelAndVIew;
//    }

    @GetMapping("/{boardId}/{id}/delete")
    public ModelAndView deleteComment(@PathVariable Long id, @PathVariable Long boardId) {

        ModelAndView modelAndView = new ModelAndView();
        commentService.deleteById(id);

        modelAndView.setViewName("redirect:/boards/" + boardId);
        return modelAndView;
    }

}
