package vn.techmaster.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.blog.model.Comment;
import vn.techmaster.blog.model.Post;
import vn.techmaster.blog.model.User;
import vn.techmaster.blog.service.IAuthenService;
import vn.techmaster.blog.service.IPostService;
import vn.techmaster.blog.service.PostService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;


@Controller
public class PostController {

  @Autowired
  private IPostService postService;

  @Autowired
  private IAuthenService authenService;

  @GetMapping("/posts")
  public String getAllPosts(Model model, HttpServletRequest request) {
    model.addAttribute("posts", postService.getAllPostOfUser(getUser(request)));
    return "posts.html";
  }

  @GetMapping("/post/edit")
  public String editPage(Model model,@ModelAttribute(name = "post") Post post, @RequestParam(required = false) Long id) {
    if (id != null) {
      post = postService.findById(id);
    }
    model.addAttribute("post", post);
    return "editpost";
  }

  @PostMapping("/post/edit")
  public String editPost(@ModelAttribute(name = "post") Post post, HttpServletRequest request) {
    post.setAuthor(getUser(request));
    postService.save(post);
    return "redirect:/posts";
  }

  @GetMapping("/getAll")
  public String listAll(Model model, HttpServletRequest request){
    model.addAttribute("user",getUser(request));
    model.addAttribute("posts",postService.getAll());
    return "getAll.html";
  }


  @GetMapping("/post/delete")
  public String deletePost(@ModelAttribute(name = "id") Long id, HttpServletRequest request) {
    postService.delete(id);
    return "redirect:/posts";
  }

  @GetMapping("/post/comment")
  public String detailPage(Model model, @RequestParam(name = "id") Long id, HttpServletRequest request) {
    Post post = postService.findById(id);
    model.addAttribute("post", post);
    model.addAttribute("user",getUser(request));
    return "comment";
  }

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
