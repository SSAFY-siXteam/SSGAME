import React from "react";
import Nav from "../../atoms/Nav/Nav";
import Logo from "../../atoms/Img/Logo/Logo";
import { Menus, StyledHeader, HeaderGrid } from "./styled";
import Button from "../../atoms/Buttons/Button";
import { menus } from "./menus";

const Header = () => {
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
