package com.sixteam.ssgame.api.gameInfo.service;

import com.sixteam.ssgame.api.member.dto.MemberGameDto;
import com.sixteam.ssgame.api.member.dto.response.ResponseMemberGamePageDto;
import com.sixteam.ssgame.api.gameInfo.repository.MemberGameListRepository;
import com.sixteam.ssgame.api.member.entity.Member;
import com.sixteam.ssgame.api.member.repository.MemberRepository;
import com.sixteam.ssgame.global.common.auth.CustomUserDetails;
import com.sixteam.ssgame.global.common.util.LogUtil;
import com.sixteam.ssgame.global.error.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.sixteam.ssgame.global.error.dto.ErrorStatus.MEMBER_NOT_FOUND;

@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
@Service
public class MemberGameListServiceImpl implements MemberGameListService {

    private final MemberGameListRepository memberGameListRepository;

    private final MemberRepository memberRepository;

    @Override
    public ResponseMemberGamePageDto getResponseMemberGamePageDto(CustomUserDetails details, Pageable pageable, boolean filter, String search) {


        String ssgameId = details.getUsername();
        Member member = memberRepository.findBySsgameId(ssgameId)
                .orElseThrow(() -> new CustomException(LogUtil.getElement(), MEMBER_NOT_FOUND));
        Long memberSeq = member.getMemberSeq();

        Integer totalCnt = memberGameListRepository.countMemberGameListByFilterAndSearch(memberSeq, filter, search);
        Integer totalPage = (int) Math.ceil((double) totalCnt / pageable.getPageSize());
        List<MemberGameDto> memberGameDtos = memberGameListRepository.findMemberGameDtos(memberSeq, pageable, filter, search);

        return ResponseMemberGamePageDto.builder()
                .totalCnt(totalCnt)
                .totalPage(totalPage)
                .currentPage(pageable.getPageNumber())
                .memberGameDtos(memberGameDtos)
                .build();
    }
}
