import React, { useState, useEffect } from "react";
import Nav from "../../atoms/Nav/Nav";
import Logo from "../../atoms/Img/Logo/Logo";
import { Menus, StyledHeader, HeaderGrid } from "./styled";
import Button from "../../atoms/Buttons/Button";
import { menus } from "./menus";
import ModalTemplate from "../../templates/ModalTemplate/ModalTemplate";
import LogIn from "../LogIn/LogIn";
import { removeCookie } from "../../../utils/cookie";
const Header = ({ logged, onLogOut, onLogIn }) => {
  const [open, setOpen] = useState(false);
  const handleOpen = () => {
    console.log("open");
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
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
          {!logged && <Button text="로그인" onClick={handleOpen} />}
          {logged && (
            <Button
              text="로그아웃"
              onClick={() => {
                onLogOut();
                setOpen(false);
              }}
            />
          )}
        </Menus>
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
