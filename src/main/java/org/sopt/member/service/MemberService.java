package org.sopt.member.service;

import org.sopt.member.domain.Member;
import org.sopt.member.dto.request.MemberCreateDto;
import org.sopt.member.dto.response.MemberInfoDto;

import java.util.List;

public interface MemberService {
    MemberInfoDto join(MemberCreateDto req);
    MemberInfoDto findOne(Long memberId);
    List<MemberInfoDto> findAllMembers();
    void deleteMember(Long memberId);
    Member findMemberById(Long memberId);
}
