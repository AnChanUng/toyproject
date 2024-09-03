package com.example.demo.controller;

import com.example.demo.entity.UserBoard;
import com.example.demo.service.UserBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/userboards")
public class UserBoardController {

    @Autowired
    private UserBoardService userBoardService;

    @GetMapping
    public ModelAndView getAllBoards() {
        List<UserBoard> userBoards = userBoardService.findAll();
        ModelAndView mnv = new ModelAndView();

        mnv.addObject("userBoards", userBoards);
        mnv.setViewName("user-board");
        return mnv;
    }

    @PostMapping
    public ModelAndView createBoard(@RequestParam String title, @RequestParam String content) {
        UserBoard userBoard = new UserBoard();
        ModelAndView mnv = new ModelAndView();
        userBoard.setTitle(title);
        userBoard.setContent(content);
        userBoardService.save(userBoard);

        mnv.setViewName("redirect:/userboards");

        return mnv;
    }

    @GetMapping("/{id}")
    public ModelAndView getBoardById(@PathVariable Long id) {
        ModelAndView mnv = new ModelAndView();
        userBoardService.findById(id).ifPresent(userBoard -> {
            mnv.addObject("board", userBoard);
        });
        mnv.setViewName("user-board-detail");
        return mnv;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editBoard(@PathVariable Long id, Model model) {
        ModelAndView mnv = new ModelAndView();
        userBoardService.findById(id).ifPresent(userBoard ->
                mnv.addObject("board", userBoard));
        mnv.setViewName("user-board-edit");  // 추가
        return mnv;
    }

    @PostMapping("/{id}/edit")
    public ModelAndView updateBoard(@PathVariable Long id, @RequestParam String title, @RequestParam String content) {
        UserBoard userBoard = new UserBoard();
        ModelAndView mnv = new ModelAndView();

        userBoard.setTitle(title);
        userBoard.setContent(content);
        userBoardService.save(userBoard);

        mnv.setViewName("redirect:/userboards");
        return mnv;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteBoard(@PathVariable Long id) {
        ModelAndView mnv = new ModelAndView();
        userBoardService.deleteById(id);
        mnv.setViewName("redirect:/userboards");
        return mnv;
    }
}
