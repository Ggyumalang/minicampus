package com.minicampus.minicampus.member.entity;

public interface MemberCode {

    /**
     * 현재 가입 요청 중인 상태
     */
    String MEMBER_STATUS_REQ = "REQ";


    /**
     * 현재 이용 중인 상태
     */
    String MEMBER_STATUS_ING = "ING";

    /**
     * 현재 정지된 상태
     */
    String MEMBER_STATUS_STOP = "STOP";

    /**
     * 탈퇴한 상태
     */
    String MEMBER_STATUS_WITHDRAW = "WITHDRAW";
}
