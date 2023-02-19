package com.minicampus.minicampus.member.controller;

import com.minicampus.minicampus.admin.dto.MemberDto;
import com.minicampus.minicampus.course.dto.TakeCourseDto;
import com.minicampus.minicampus.course.model.ServiceResult;
import com.minicampus.minicampus.course.service.TakeCourseService;
import com.minicampus.minicampus.member.model.MemberInput;
import com.minicampus.minicampus.member.model.ResetPasswordInput;
import com.minicampus.minicampus.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final TakeCourseService takeCourseService;

    @RequestMapping("/member/login")
    public String login(HttpServletRequest request) {
        return "member/login";
    }


    @GetMapping("/member/find-password")
    public String findPassword() {
        return "member/find_password";
    }

    @PostMapping("/member/find-password")
    public String findPasswordSubmit(
            ResetPasswordInput parameter
            , Model model
    ) {
        boolean result = false;
        try {
            result = memberService.sendResetPassword(parameter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("result", result);
        //redirect = 주소도 함께 바꿔줌
        //redirect를 쓰지 않으면.. 화면만 바뀜 주소는 그대로가 됨.
        return "member/find_password_result";
    }

    @GetMapping("/member/register")
    public String register() {
        return "member/register";
    }

    @PostMapping("/member/register")
    public String registerSubmit(
            HttpServletRequest request
            , MemberInput memberInput
            , Model model //Model은 Client한테 데이터를 내리기 위해 사용하는 인터페이스
    ) {

        boolean result = memberService.register(memberInput);

        System.out.println("result = " + result);

        model.addAttribute("result", result);

        return "member/register_complete";
    }

    //http의 기본포트는 80이다.
    //https의 기본포트는 433이고
    //해당 포트들은 따로 명시적으로 기록안해도된다.
    //http://www.naver.com/news/list.do?id=123
    //https://
    //프로토콜://도메인(IP)/member/email-auth?쿼리스트링(파라미터)

    @GetMapping("/member/email-auth")
    public String emailAuth(
            @RequestParam("id") String uuid
            , Model model
    ) {
        boolean result = memberService.emailAuth(uuid);
        model.addAttribute("result", result);

        return "member/email_auth";
    }

    @GetMapping("/member/info")
    public String memberInfo(
            Model model
            , Principal principal
    ) {
        MemberDto detail = memberService.detail(principal.getName());
        model.addAttribute("detail", detail);
        return "member/info";
    }

    @PostMapping("/member/info")
    public String memberInfoSubmit(
            Model model
            , MemberInput parameter
            , Principal principal
    ) {
        parameter.setUserId(principal.getName());
        ServiceResult result = memberService.updateMember(parameter);

        if (!result.getResult()) {
            model.addAttribute("errorMessage", result.getMessage());
            return "common/error";
        }

        return "redirect:/member/info";
    }

    @GetMapping("/member/password")
    public String memberPassword() {
        return "member/password";
    }

    @PostMapping("/member/password")
    public String memberPasswordSubmit(
            Model model
            , MemberInput parameter
            , Principal principal
    ) {
        parameter.setUserId(principal.getName());
        ServiceResult result = memberService.updateMemberPassword(parameter);
        if (!result.getResult()) {
            model.addAttribute("errorMessage", result.getMessage());
            return "common/error";
        }

        return "redirect:/member/info";
    }

    @GetMapping("/member/takecourse")
    public String memberTakeCourse(
            Model model
            , Principal principal
    ) {

        List<TakeCourseDto> list = takeCourseService.myCourse(principal.getName());
        model.addAttribute("list", list);
        return "member/takecourse";
    }

    @GetMapping("/member/reset/password")
    public String resetPassword(Model model
            , @RequestParam("id") String id
    ) {
        String uuid = id;
        model.addAttribute("uuid", uuid);
        //이 uuid값이 유효한 지 확인
        boolean result = memberService.checkResetPassword(uuid);
        model.addAttribute("result", result);

        return "member/reset_password";
    }

    @PostMapping("/member/reset/password")
    public String resetPasswordSubmit(
            ResetPasswordInput parameter
            , Model model
    ) {
        boolean result = false;
        try {
            result = memberService.resetPassword(parameter.getId(), parameter.getPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        model.addAttribute("result", result);

        return "member/reset_password_result";
    }

    @GetMapping("/member/withdraw")
    public String memberWithdraw() {
        return "member/withdraw";
    }

    @PostMapping("/member/withdraw")
    public String memberWithdrawSubmit(
            Model model
            , Principal principal
            , MemberInput parameter
    ) {

        String userId = principal.getName();

        ServiceResult result = memberService.withdraw(userId, parameter.getPassword());

        if (!result.getResult()) {
            model.addAttribute("errorMessage", result.getMessage());
            return "common/error";
        }


        return "redirect:/member/logout";
    }
}
