package org.sopt.service;

import org.sopt.dto.request.MemberCreateDto;
import org.sopt.dto.response.MemberInfoDto;

import java.util.List;

public interface MemberService {
    MemberInfoDto join(MemberCreateDto req);
    MemberInfoDto findOne(Long memberId);
    List<MemberInfoDto> findAllMembers();
    void deleteMember(Long memberId);
}
