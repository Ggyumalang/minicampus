package com.minicampus.minicampus.admin.controller;

import com.minicampus.minicampus.admin.dto.LoginHistoryDto;
import com.minicampus.minicampus.admin.dto.MemberDto;
import com.minicampus.minicampus.admin.entity.LoginHistory;
import com.minicampus.minicampus.admin.model.MemberInput;
import com.minicampus.minicampus.admin.model.MemberParam;
import com.minicampus.minicampus.course.controller.BaseController;
import com.minicampus.minicampus.member.service.MemberService;
import com.minicampus.minicampus.util.PageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminMemberController extends BaseController {

    private final MemberService memberService;

    @GetMapping("/admin/member/list.do")
    public String list(Model model, MemberParam memberParam) {

        memberParam.init();

        List<MemberDto> members = memberService.list(memberParam);

        long totalCount = 0;
        if (members != null && members.size() > 0) {
            totalCount = members.get(0).getTotalCount();
        }
        String pagetHtml = getPagerHtml(totalCount, memberParam.getPageSize(),
                memberParam.getPageIndex(), memberParam.getQueryString());

        model.addAttribute("list", members);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagetHtml);

        return "admin/member/list";
    }

    @GetMapping("/admin/member/detail.do")
    public String detail(Model model, MemberParam memberParam) {
        memberParam.init();

        MemberDto memberDto = memberService.detail(memberParam.getUserId());
        model.addAttribute("memberDto", memberDto);

        //회원 상세 - 로그인 접속 목록 표시
        List<LoginHistoryDto> logInfoList =
                memberService.getLogInfo(memberParam.getUserId());

        model.addAttribute("logInfoList" , logInfoList);

        return "admin/member/detail";
    }

    @PostMapping("/admin/member/status.do")
    public String status(Model model , MemberInput parameter){
        boolean result = memberService.updateStatus(parameter.getUserId() , parameter.getUserStatus());
        return "redirect:/admin/member/detail.do?userId=" + parameter.getUserId();
    }

    @PostMapping("/admin/member/password.do")
    public String password(Model model , MemberInput parameter){
        boolean result = memberService.updatePassword(
                parameter.getUserId() , parameter.getPassword()
        );
        return "redirect:/admin/member/detail.do?userId=" + parameter.getUserId();
    }


}
