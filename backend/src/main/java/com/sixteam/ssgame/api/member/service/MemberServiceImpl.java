package com.sixteam.ssgame.api.member.service;

import com.sixteam.ssgame.api.analyze.entity.Category;
import com.sixteam.ssgame.api.analyze.enums.CategoryType;
import com.sixteam.ssgame.api.analyze.repository.CategoryRepository;
import com.sixteam.ssgame.api.member.dto.request.RequestMemberDto;
import com.sixteam.ssgame.api.member.dto.response.ResponseLoginMemberDto;
import com.sixteam.ssgame.api.member.entity.Member;
import com.sixteam.ssgame.api.member.entity.MemberPreferredCategory;
import com.sixteam.ssgame.api.member.repository.MemberPreferredCategoryRepository;
import com.sixteam.ssgame.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final MemberPreferredCategoryRepository memberPreferredCategoryRepository;

    private final CategoryRepository categoryRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean hasSsgameId(String ssgameId) {
        return memberRepository.existsBySsgameId(ssgameId);
    }

    @Override
    public boolean hasEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    @Override
    public boolean hasSteamID(String steamID) {
        return memberRepository.existsBySteamID(steamID);
    }

    @Override
    @Transactional
    public void register(RequestMemberDto requestMemberDto) {
        String encryptedPassword = passwordEncoder.encode(requestMemberDto.getPassword());
        log.debug("패스워드 암호화 " + encryptedPassword);

        Member savedMember = memberRepository.save(Member.builder()
                .ssgameId(requestMemberDto.getSsgameId())
                .password(encryptedPassword)
                .email(requestMemberDto.getEmail())
                .steamID(requestMemberDto.getSteamID())
                .steamNickname("steamNickname")
                .avartarUrl("avartarUrl")
                .build());

        List<String> preferredCategories = requestMemberDto.getPreferredCategories();
        for (String preferredCategory : preferredCategories) {
            Long categorySeq = preferredCategoryToCategorySeq(preferredCategory.toUpperCase());
            memberPreferredCategoryRepository.save(MemberPreferredCategory.builder()
                    .member(savedMember)
                    .category(categoryRepository.findByCategorySeq(categorySeq))
                    .build());
        }
    }

    @Override
    public ResponseLoginMemberDto findResponseLoginMemberDto(String ssgameId) {

        Member member = memberRepository.findBySsgameId(ssgameId);
        List<MemberPreferredCategory> categories = memberPreferredCategoryRepository.findMemberPreferredCategoriesByMemberMemberSeq(member.getMemberSeq());

        List<String> preferredCategories = new ArrayList<>();
        for (MemberPreferredCategory category : categories) {
            preferredCategories.add(category.getCategory().getCategoryName());
        }

        return ResponseLoginMemberDto.builder()
                .memberSeq(member.getMemberSeq())
                .ssgameId(member.getSsgameId())
                .password(member.getPassword())
                .email(member.getEmail())
                .steamID(member.getSteamID())
                .steamNickname(member.getSteamNickname())
                .avartarUrl(member.getAvartarUrl())
                .preferredCategories(preferredCategories)
                .build();
    }

    private Long preferredCategoryToCategorySeq(String preferredCategory) {
        if (preferredCategory.equals(CategoryType.AESTHETIC.toString())) {
            return 1L;
        } else if (preferredCategory.equals(CategoryType.THRILLER.toString())) {
            return 2L;
        } else if (preferredCategory.equals(CategoryType.BRAIN.toString())) {
            return 3L;
        } else if (preferredCategory.equals(CategoryType.HEALING.toString())) {
            return 4L;
        } else if (preferredCategory.equals(CategoryType.ACTIVITY.toString())) {
            return 5L;
        } else if (preferredCategory.equals(CategoryType.SF.toString())) {
            return 6L;
        } else {
            // preferredCategory.equals(CategoryType.ADVENTURE.toString())
            return 7L;
        }
    }
}
