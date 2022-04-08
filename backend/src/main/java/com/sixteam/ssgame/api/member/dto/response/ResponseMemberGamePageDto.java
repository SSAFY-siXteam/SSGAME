package com.sixteam.ssgame.api.member.dto.response;

import com.sixteam.ssgame.api.member.dto.MemberGameDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString(of = { "totalCnt", "currentPage", "totalPage" })
@Getter
public class ResponseMemberGamePageDto {

    private Integer totalCnt;

    private Integer currentPage;

    private Integer totalPage;

    private List<MemberGameDto> memberGameDtos;

    @Builder
    public ResponseMemberGamePageDto(Integer totalCnt, Integer currentPage, Integer totalPage, List<MemberGameDto> memberGameDtos) {
        this.totalCnt = totalCnt;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.memberGameDtos = memberGameDtos;
    }
}
