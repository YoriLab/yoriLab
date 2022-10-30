package com.zolPro.yoriLab.controller;

import com.zolPro.yoriLab.domain.*;
import com.zolPro.yoriLab.dto.RecommendationByDay;
import com.zolPro.yoriLab.dto.RecommendationByWhen;
import com.zolPro.yoriLab.service.FavoringredServiceImpl;
import com.zolPro.yoriLab.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller

public class indexController {
    @Autowired
    MemberServiceImpl memberServiceImpl;
    @Autowired
    FavoringredServiceImpl favoringredServiceImpl;
    /* 인덱스 페이지 */
    @GetMapping("/")
    public String index(Model model,HttpSession session) {
        session.setAttribute("state","0"); return "index";
    }
    /* 메인 화면 */
    @GetMapping("/main")
    public String mainPage(Model model) {
        return "mainPage";
    }
    /* 회원가입 페이지 */
    @GetMapping("/signup")
    public String signUp(Model model) {
        return "Signup";
    }

//    /* 영수증 페이지 */
//    @GetMapping("/receipt")
//    public String receipt(Model model) {
//        // 변경 예정
//        return "/";
//    }
//
//    /* 레시피 상세 페이지 */
//    @GetMapping("/recipe-detail")
//    public String recipeDetail(Model model) {
//        // 변경 예정
//        return "/";
//    }

    @GetMapping("/recipeView")
    public String recipeRecommend(Model model) {
        return "recipeView";
    }

    @GetMapping("/select")
    public String select(Model model) {
        return "selectIngred";
    }



    /* 레시피 캘린더 페이지 */
    @GetMapping("/recipeCalendar")
    public String recipeCalendar(Model model) {
        return "recipecalendar";
    }
    @PostMapping("/join")
    public String printResult(@ModelAttribute Member member ,Model model) {


        System.out.println(member.getEmailID());
        System.out.println(member.getName());
        System.out.println(member.getPW());

        Member findMember;

        findMember = memberServiceImpl.find(member.getEmailID());

        if(findMember == null) {
            System.out.println("success");
            memberServiceImpl.join(member);
            return "mainPage";
        }else {
            System.out.println("fail");
            model.addAttribute("name", "error");
            return "Signup";
        }


    }

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute Member member, HttpSession session) {

        String emailID = member.getEmailID();
        System.out.println(emailID);
        Member findMember = memberServiceImpl.find(emailID);

        if(findMember == null) {
            model.addAttribute("error", "존재하지 않는 회원입니다.");


        }else {
            if(!member.getPW().equals(findMember.getPW())) {
                System.out.println("not same");
                model.addAttribute("error", "비밀번호가 잘못 되었습니다.");
            }else {

                session.setAttribute("member",findMember);
            }

        }

        return "mainPage";
    }

    @GetMapping("/logout")
    public String login(HttpSession session){
        session.removeAttribute("member");
        return "mainPage";
    }

    @PostMapping("/select")
    public String selectFavorIngred(@ModelAttribute favoringredList ingredList){
        System.out.println("ingred : " + ingredList.getIngred());
        favoringredServiceImpl.insert(ingredList);
        return "mainPage";
    }
}
