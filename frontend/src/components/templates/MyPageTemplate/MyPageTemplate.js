import React from "react";
import {
  StyledMyPage,
  StyledContent,
  CategoryHeader,
  StyledUpdateBtn,
  StyledWithdrawalBtn,
  StyledProfile,
  StyledImg,
  StyledProfileInfo,
  StyledProfileInfoContent,
} from "./styles";
const MyPageTemplate = ({ checkBox, profileImg, updateBtn, withdrawalBtn }) => {
  return (
    <StyledMyPage>
      <StyledProfile>
        <StyledImg>{profileImg}</StyledImg>
        <StyledProfileInfo>
          <StyledProfileInfoContent>아이디:</StyledProfileInfoContent>
          <StyledProfileInfoContent>이메일:</StyledProfileInfoContent>
          <StyledProfileInfoContent>SteamID:</StyledProfileInfoContent>
        </StyledProfileInfo>
      </StyledProfile>

      <CategoryHeader>선호 게임 카테고리</CategoryHeader>
      <div>{checkBox}</div>
      <StyledUpdateBtn>{updateBtn}</StyledUpdateBtn>
      <StyledWithdrawalBtn>{withdrawalBtn}</StyledWithdrawalBtn>
    </StyledMyPage>
  );
};

export default MyPageTemplate;
