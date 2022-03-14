import React from "react";
import logo from "../../../../assets/img/logo.png";
import { LogoIMG } from "./style";

export default function Logo() {
  return (
    <div>
      <LogoIMG src={logo} />
    </div>
  );
}
