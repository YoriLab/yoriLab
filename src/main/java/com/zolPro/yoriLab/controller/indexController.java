package com.zolPro.yoriLab.controller;

import com.zolPro.yoriLab.domain.*;
import com.zolPro.yoriLab.dto.MemberForm;
import com.zolPro.yoriLab.dto.RecommendationByDay;
import com.zolPro.yoriLab.service.FavoringredServiceImpl;
import com.zolPro.yoriLab.service.MemberServiceImpl;
import com.zolPro.yoriLab.service.RecommService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class indexController {

    @Autowired
    MemberServiceImpl memberServiceImpl;
    @Autowired
    FavoringredServiceImpl favoringredServiceImpl;

    private RecommService recommService;
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


    @GetMapping("/recipeView")
    public String recipeRecommend(Model model) {
        return "recipeView";
    }

    @GetMapping("/select")
    public String select(Model model,HttpSession session) {
        Member member = (Member)session.getAttribute("member");
        List<Object[]> ingredList = favoringredServiceImpl.selectByID(member.getId());



        List<String> ingredString = new ArrayList<String>();

        for(Object[] obj : ingredList) {
         String name = favoringredServiceImpl.getingredName((BigInteger)obj[1]);
           ingredString.add(name);
        }
        model.addAttribute("ingredList",ingredString);
        return "selectIngred";
    }



    /* 레시피 캘린더 페이지 */
    @GetMapping("/recipeCalendar")
    public String recipeCalendar(Model model) {
        return "recipecalendar";
    }

    @PostMapping("/join")
    public String printResult(@ModelAttribute MemberForm memberForm , Model model) {

        Member findMember;

        findMember = memberServiceImpl.find(memberForm.getEmailID());

        if(findMember == null) {
            System.out.println("success");
            Member member = new Member(memberForm.getEmailID(), memberForm.getPW(), memberForm.getName());
            memberServiceImpl.join(member);
            return "mainPage";
        }else {
            System.out.println("fail");
            model.addAttribute("name", "error");
            return "Signup";
        }


    }

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute MemberForm memberForm, HttpSession session) {

        String emailID = memberForm.getEmailID();
        System.out.println(emailID);
        Member findMember = memberServiceImpl.find(emailID);

        if(findMember == null) {
            model.addAttribute("error", "존재하지 않는 회원입니다.");
        }else {
            if(!memberForm.getPW().equals(findMember.getPW())) {
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
    public String selectFavorIngred(@RequestParam String ingred,@RequestParam(value = "day", required = false, defaultValue = "1") Long day,Model model, HttpSession session){
        Member member = (Member)session.getAttribute("member");
        System.out.println("ingred : " + ingred);
        String splitArray[] = ingred.split(" ");
        System.out.println("ingred : " + splitArray.length);
        System.out.println("id : " + member.getId());
        favoringredServiceImpl.insert(member,splitArray);

        List<RecommendationByDay> recommendationFullList = new ArrayList<>();

//        session
        Member sessionMember = (Member) session.getAttribute("member");

        recommendationFullList = recommService.getRecommendationByDays(day, sessionMember);

        // 레시피 일차별 출력 위한 변수
        model.addAttribute("recommendationFullList", recommendationFullList);


        Map<String, String> allIngredientList = recommService.getAllIngredientList(recommendationFullList);

        model.addAttribute("allIngredientList", allIngredientList);

        // 일 수 전달
        model.addAttribute("day", day);
        return "contents/recommendation";


    }
    @PostMapping("/cancel")
    public String cancelFavorIngred(@RequestParam String cancelIngred, HttpSession session,Model model){
        Member member = (Member)session.getAttribute("member");
        System.out.println("cancelIngred : " + cancelIngred);

        String splitArray[] = cancelIngred.split(" ");
        System.out.println("cancelIngred : " + splitArray.length);
        System.out.println("id : " + member.getId());
        favoringredServiceImpl.cancel(member,splitArray);
        List<Object[]> ingredList = favoringredServiceImpl.selectByID(member.getId());

        List<String> ingredString = new ArrayList<String>();

        for(Object[] obj : ingredList) {
            String name = favoringredServiceImpl.getingredName((BigInteger)obj[1]);
            ingredString.add(name);
        }
        model.addAttribute("ingredList",ingredString);
        return "selectIngred";

    }

}
