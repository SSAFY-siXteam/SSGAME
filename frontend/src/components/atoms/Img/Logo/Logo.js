import React from "react";
import { Link } from "react-router-dom";
import logo from "../../../../assets/img/logo.png";
import { LogoIMG } from "./style";

export default function Logo() {
  return (
    <Link to="/">
      <LogoIMG src={logo} />
    </Link>
  );
}
