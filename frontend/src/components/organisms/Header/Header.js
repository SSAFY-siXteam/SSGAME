import React, { useState, useEffect } from "react";
import Nav from "../../atoms/Nav/Nav";
import Logo from "../../atoms/Img/Logo/Logo";
import {
  Menus,
  StyledHeader,
  HeaderGrid,
  StyledLoggedIn,
  StyledMenu,
  StyledButton,
} from "./styled";
import Button from "../../atoms/Buttons/Button";
import { menus } from "./menus";
import ModalTemplate from "../../templates/ModalTemplate/ModalTemplate";
import LogIn from "../LogIn/LogIn";
import { removeCookie } from "../../../utils/cookie";
import Collapse from "@mui/material/Collapse";
import { Paragraph } from "../../atoms/Paragraph/Paragraph";
import { Link } from "react-router-dom";
const Header = ({ logged, onLogOut, onLogIn }) => {
  const [open, setOpen] = useState(false);
  const handleOpen = () => {
    console.log("open");
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };

  const [openHamburger, setHamburgerOpen] = useState(false);

  const handleClick = () => {
    setHamburgerOpen(!openHamburger);
  };

  useEffect(() => {}, [open]);
  return (
    <StyledHeader>
      <HeaderGrid>
        <Logo />
        <Menus>
          {menus.map((menu, index) => (
            <Nav key={index} menu={menu} />
          ))}
        </Menus>
        {logged ? (
          <StyledLoggedIn>
            <StyledMenu onClick={handleClick}>내 정보</StyledMenu>
            <Collapse in={openHamburger} timeout="auto" unmountOnExit>
              <StyledMenu>
                <Link to="/mypage">회원정보 수정</Link>
              </StyledMenu>
              <StyledMenu>
                <Link to="/mygame">내 게임 목록</Link>
              </StyledMenu>
              <StyledMenu
                onClick={() => {
                  onLogOut();
                  setOpen(false);
                  setHamburgerOpen(false);
                }}
              >
                <Link to="/">로그아웃</Link>
              </StyledMenu>
            </Collapse>
          </StyledLoggedIn>
        ) : (
          <StyledLoggedIn>
            <StyledButton>
              <Button text="로그인" onClick={handleOpen} />
            </StyledButton>
          </StyledLoggedIn>
        )}
      </HeaderGrid>
      <ModalTemplate
        content={<LogIn onLogIn={onLogIn} />}
        isOpened={open && !logged}
        handleClose={handleClose}
      />
    </StyledHeader>
  );
};

export default Header;
