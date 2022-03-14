import React from "react";
import Nav from "../../atoms/Nav/Nav";
import Logo from "../../atoms/Img/Logo/Logo";
import { Menus, StyledHeader, HeaderGrid } from "./styled";
import Button from "../../atoms/Buttons/Button";

const Header = () => {
  const menus = [
    { menu: "성향 분석", path: "/" },
    { menu: "맞춤 게임 추천", path: "/" },
  ];
  return (
    <StyledHeader>
      <HeaderGrid>
        <Logo />
        <Menus>
          {menus.map((menu, index) => (
            <Nav key={index} menu={menu} />
          ))}
          <Button
            text="로그인"
            onClick={() => {
              console.log("로그인");
            }}
          />
        </Menus>
      </HeaderGrid>
    </StyledHeader>
  );
};

export default Header;
