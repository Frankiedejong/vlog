package com.nowcoder.community.controller;

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/index",method = RequestMethod.GET)
    // 因为响应的是网页，所以就不加ResponseBody了
    public String getIndexPage(Model model, Page page){
        // 方法调用之前，SpringMVC会自动实例化Model和Page，并将Page注入Model
        // 所以，在thymeleaf中可以直接访问Page对象中的数据
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");

        // 这一步找到所有的帖子
        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());
        //创造一个集合
        List<Map<String,Object>> discussPosts=new ArrayList<>();
        if(list!=null){
            /**
             * 先把找到的所有帖子全部放在post里，准备下一步遍历，新建map用来放post和user信息
             * map把遍历后有信息的post放入map中，再通过调用userService调用方法，以post里的UserId来找到所有的user信息，放入map
             * 最后将每一个包含了一个post信息以及对应的user信息的map放入到list（也就是discussPosts）中
             * 将最后的渲染结果添加到model里
             */
            for(DiscussPost post:list){
                Map<String,Object> map = new HashMap<>();
                map.put("post", post);
                User user = userService.findUserById(post.getUserId());
                map.put("user", user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts",discussPosts);
        return "/index";
    }


}
