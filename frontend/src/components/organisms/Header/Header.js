import React, { useState, useEffect } from "react";
import Nav from "../../atoms/Nav/Nav";
import Logo from "../../atoms/Img/Logo/Logo";
import { Menus, StyledHeader, HeaderGrid } from "./styled";
import Button from "../../atoms/Buttons/Button";
import { menus } from "./menus";
import ModalTemplate from "../../templates/ModalTemplate/ModalTemplate";
import LogIn from "../LogIn/LogIn";
const Header = () => {
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
          <Button text="로그인" onClick={handleOpen} />
        </Menus>
      </HeaderGrid>
      <ModalTemplate
        content={<LogIn />}
        isOpened={open}
        handleClose={handleClose}
      />
    </StyledHeader>
  );
};

export default Header;
