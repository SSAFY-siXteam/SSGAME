import React from "react";
import {
  StyledMyPage,
  CategoryHeader,
  StyledUpdateBtn,
  StyledWithdrawalBtn,
  StyledProfile,
  StyledImg,
  StyledProfileInfo,
  StyledProfileInfoContent,
  StyledButtons,
  StyledInfo,
} from "../styles";
import Img from "../../../atoms/Img/Img/Img";
import { Input } from "../../../atoms/Input/Input";
const MyPageTemplate = ({
  userInfo,
  checkBox,
  updateBtn,
  withdrawalBtn,
  onInputChangePassword,
  onInputChangeNewPassword,
  onInputChangeNewPasswordCheck,
  email,
  onEmailChange,
}) => {
  return (
    <StyledMyPage>
      <StyledProfile>
        <StyledImg>
          <Img path={userInfo.avatarUrl} />
        </StyledImg>
        <StyledProfileInfo>
          <StyledProfileInfoContent>
            아이디: <StyledInfo>{userInfo.ssgameId}</StyledInfo>
          </StyledProfileInfoContent>
          <StyledProfileInfoContent>
            이메일:{" "}
            <StyledInfo>
              <Input value={email} onInputChange={onEmailChange} />
            </StyledInfo>
          </StyledProfileInfoContent>
          <StyledProfileInfoContent>
            SteamID: <StyledInfo>{userInfo.steamID}</StyledInfo>
          </StyledProfileInfoContent>

          <br />
          <StyledProfileInfoContent>
            현재 비밀번호:{" "}
            <StyledInfo>
              <Input
                type="password"
                placeholder="비밀번호를 입력해주세요"
                size="25"
                onInputChange={onInputChangePassword}
              />
            </StyledInfo>
          </StyledProfileInfoContent>
          <StyledProfileInfoContent>
            새로운 비밀번호:{" "}
            <StyledInfo>
              <Input
                type="password"
                placeholder="비밀번호 변경 시 만 입력해주세요"
                size="25"
                onInputChange={onInputChangeNewPassword}
              />
            </StyledInfo>
          </StyledProfileInfoContent>
          <StyledProfileInfoContent>
            비밀번호 재확인:{" "}
            <StyledInfo>
              <Input
                type="password"
                placeholder="비밀번호 변경 시 만 입력해주세요"
                size="25"
                onInputChange={onInputChangeNewPasswordCheck}
              />
            </StyledInfo>
          </StyledProfileInfoContent>
        </StyledProfileInfo>
      </StyledProfile>

      <CategoryHeader>선호 게임 카테고리</CategoryHeader>

      <div>{checkBox}</div>
      <StyledButtons>
        <StyledUpdateBtn>{updateBtn}</StyledUpdateBtn>
        <StyledWithdrawalBtn>{withdrawalBtn}</StyledWithdrawalBtn>
      </StyledButtons>
    </StyledMyPage>
  );
};

export default MyPageTemplate;
