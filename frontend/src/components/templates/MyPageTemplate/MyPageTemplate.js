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
  StyledButtons,
  StyledInfo,
} from "./styles";
import Img from "../../atoms/Img/Img/Img";
const MyPageTemplate = ({
  userInfo,
  checkBox,
  profileImg,
  updateBtn,
  withdrawalBtn,
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
            이메일: <StyledInfo>{userInfo.email}</StyledInfo>
          </StyledProfileInfoContent>
          <StyledProfileInfoContent>
            SteamID: <StyledInfo>{userInfo.steamID}</StyledInfo>
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
