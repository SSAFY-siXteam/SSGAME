package com.sixteam.ssgame.api.gameInfo.service;

import com.sixteam.ssgame.api.member.dto.MemberGameDto;
import com.sixteam.ssgame.api.member.dto.response.ResponseMemberGamePageDto;
import com.sixteam.ssgame.api.gameInfo.repository.MemberGameListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
@Service
public class MemberGameListServiceImpl implements MemberGameListService {

    private final MemberGameListRepository memberGameListRepository;

    @Override
    public ResponseMemberGamePageDto getResponseMemberGamePageDto(Long memberSeq, Pageable pageable, boolean filter, String search) {

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
