package com.sixteam.ssgame.api.member.dto.request;

import com.sixteam.ssgame.api.analyze.enums.CategoryType;
import com.sixteam.ssgame.global.common.validator.EnumListValidator;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@ToString(of = {"ssgameId", "password", "steamID", "email", "preferredCategories"})
@NoArgsConstructor
@Getter
public class RequestMemberDto {

    @Pattern(regexp = "^[a-z]+[0-9a-z]{3,15}$", message = "아이디 형식에 맞지 않습니다.")
    private String ssgameId;

    @Pattern(regexp = "([0-9]+[A-Z]+|[A-Z]+[0-9])+[0-9a-zA-Z!@#$%]*|([0-9]+[a-z]+|[a-z]+[0-9])+[0-9a-zA-Z!@#$%]*|([0-9]+[!@#$%]+|[!@#$%]+[0-9])+[0-9a-zA-Z!@#$%]*|([A-Z]+[a-z]+|[a-z]+[A-Z])+[0-9a-zA-Z!@#$%]*|([A-Z]+[!@#$%]+|[!@#$%]+[A-Z])+[0-9a-zA-Z!@#$%]*|([a-z]+[!@#$%]+|[!@#$%]+[a-z])+[0-9a-zA-Z!@#$%]*", message = "패스워드 규칙에 맞지 않습니다.")
    @Length(min = 8, max = 16, message = "패스워드는 8자 이상 16자 이하로 입력해주세요.")
    private String password;

    @NotBlank(message = "스팀 ID를 입력해주세요.")
    private String steamID;

    @Email(regexp = "^[0-9a-z]+([.-]?[0-9a-z]+)*@[0-9a-z]+([.-]+[0-9a-z]+)*(\\.[0-9a-z]{2,3})+$", message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @EnumListValidator(enumClass = CategoryType.class, ignoreCase = true, message = "카테고리를 확인해주세요.")
    private List<String> preferredCategories;

    @Builder
    public RequestMemberDto(String ssgameId, String password, String steamID, String email, List<String> preferredCategories) {
        this.ssgameId = ssgameId;
        this.password = password;
        this.steamID = steamID;
        this.email = email;
        this.preferredCategories = preferredCategories;
    }
}
