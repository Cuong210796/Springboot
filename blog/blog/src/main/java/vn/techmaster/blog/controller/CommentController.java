package vn.techmaster.blog.controller;

import javax.servlet.http.HttpServletRequest;

import vn.techmaster.blog.model.Comment;
import vn.techmaster.blog.service.IAuthenService;
import vn.techmaster.blog.service.ICommentService;
import vn.techmaster.blog.service.IPostService;
import vn.techmaster.blog.service.PostException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import vn.techmaster.blog.DTO.UserInfo;
import vn.techmaster.blog.controller.request.CommentRequest;

import java.util.Optional;

@Controller
public class CommentController {
  @Autowired
  private IAuthenService authenService;
  @Autowired
  private IPostService postService;
  @Autowired
  private ICommentService commentService;

  @PostMapping("/comment")
  public String handlePostComment(@ModelAttribute CommentRequest commentRequest, HttpServletRequest request) {
    UserInfo userLogin = authenService.getLoginedUser(request);
    if (userLogin != null) {
      try {
        postService.addComment(commentRequest, userLogin.getId());
      } catch (PostException e) {
        e.printStackTrace();
      }

      return "redirect:/post/" + commentRequest.getPost_id();

    } else {
      return Route.HOME;
    }
  }


  @PostMapping("/deletecomment")
  public String deleteComment(@RequestParam(name = "comment_id", required = false) Long id,
                              @RequestParam(name = "post_id", required = false) Long post_id, HttpServletRequest request) {
    System.out.println(post_id);
    System.out.println(id);
    Optional<Comment> comment = commentService.findById(id);
    commentService.deleteCommentById(id);
    return "redirect:/post/" + post_id;
  }
  
}
