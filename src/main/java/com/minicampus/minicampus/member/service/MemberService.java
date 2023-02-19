package com.minicampus.minicampus.member.service;

import com.minicampus.minicampus.admin.dto.LoginHistoryDto;
import com.minicampus.minicampus.admin.dto.MemberDto;
import com.minicampus.minicampus.admin.entity.LoginHistory;
import com.minicampus.minicampus.admin.model.MemberParam;
import com.minicampus.minicampus.course.model.ServiceResult;
import com.minicampus.minicampus.member.model.MemberInput;
import com.minicampus.minicampus.member.model.ResetPasswordInput;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MemberService extends UserDetailsService {

    /**
     * 회원가입
     *
     * @param memberInput
     * @return
     */
    Boolean register(MemberInput memberInput);

    /**
     * uuid에 해당하는 계정을 활성화
     *
     * @param uuid
     * @return
     */
    Boolean emailAuth(String uuid);

    /**
     * 입력한 이메일로 비밀번호 초기화 정보를 전송
     *
     * @param parameter
     * @return
     */
    Boolean sendResetPassword(ResetPasswordInput parameter);

    /**
     * 입력받은 uuid에 대해 password로 초기화
     *
     * @param uuid
     * @param password
     * @return
     */
    Boolean resetPassword(String uuid, String password);

    /**
     * 입력받은 uuid 값이 유효한 지 확인
     *
     * @param uuid
     * @return
     */
    Boolean checkResetPassword(String uuid);

    /**
     * 회원 목록을 return (관리자에서만 사용가능)
     *
     * @return
     */
    List<MemberDto> list(MemberParam memberParam);

    /**
     * 회원 상세 정보 조회
     *
     * @param userId
     * @return
     */
    MemberDto detail(String userId);

    /**
     * 회원 상태 변경
     *
     * @param userId
     * @param userStatus
     * @return
     */
    Boolean updateStatus(String userId, String userStatus);

    /**
     * 회원 비밀번호 초기화
     *
     * @param userId
     * @param password
     * @return
     */
    Boolean updatePassword(String userId, String password);

    /**
     * 회원 정보 페이지 내 비밀번호 변경 기능
     *
     * @param parameter
     * @return
     */
    ServiceResult updateMemberPassword(MemberInput parameter);

    /**
     * 회원 정보 수정(연락처)
     */
    ServiceResult updateMember(MemberInput parameter);

    /**
     * 회원 탈퇴 기능
     * @param userId
     * @return
     */
    ServiceResult withdraw(String userId , String password);

    /**
     * 각 사용자들의 로그인 접속 목록
     * @return
     */
    List<LoginHistoryDto> getLogInfo(String userId);
}
