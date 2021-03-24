package vn.techmaster.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.blog.model.Comment;
import vn.techmaster.blog.model.Post;
import vn.techmaster.blog.model.User;
import vn.techmaster.blog.service.IAuthenService;
import vn.techmaster.blog.service.ICommentService;
import vn.techmaster.blog.service.IPostService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IPostService postService;

    @Autowired
    private IAuthenService authenService;


    @PostMapping
    public String postComment(Comment comment, @RequestParam(name = "postid", required = false) Long postid,
                              HttpServletRequest request) {
        comment.setPost(postService.findById(postid));
        comment.setCommenter(getUser(request));
        commentService.save(comment);
        return "redirect:/post/comment?id=" + postid;
    }

    @GetMapping("/delete")
    public String deleteComment(@RequestParam(name = "id", required = false) Long id, HttpServletRequest request) {
        Comment comment = commentService.findById(id);
        commentService.delete(id);
        return "redirect:/post/comment?id=" + comment.getPost().getId();
    }

//    @GetMapping("/comment/edit")
//    public String editPage(Model model,@RequestParam(name = "id", required = false) Long id, HttpServletRequest request) {
//        Comment comment = commentService.findById(id);
//        if (id != null) {
//            comment = commentService.findById(id);
//        }
//        model.addAttribute("comment", comment);
//        return "editpost";
//    }

//    @PostMapping("/post/edit")
//    public String editComment(@ModelAttribute(name = "post") Comment comment, HttpServletRequest request) {
//        commentService.save(comment);
//        return "redirect:/getAll";



        public User getUser(HttpServletRequest request) {
        User user = null;
        Cookie[] cookies = request.getCookies();
        for (var item: cookies) {
            if (item.getName().equals("loginsuccess")) {
                user = authenService.findByEmail(item.getValue());
            }
        }
        return user;
    }
}
