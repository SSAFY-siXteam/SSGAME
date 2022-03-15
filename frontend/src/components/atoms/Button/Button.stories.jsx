import React from "react";

import { Button } from "./Button";
//img import
import steamLogInImg from "../../../assets/img/button/steambutton/sits_small.png";

// More on default export: https://storybook.js.org/docs/react/writing-stories/introduction#default-export
export default {
  title: "Button",
  component: Button,
  argTypes: { onClick: { action: "clicked" } },
};

// More on component templates: https://storybook.js.org/docs/react/writing-stories/introduction#using-args
const Template = (args) => <Button {...args} />;

export const Primary = Template.bind({});
// More on args: https://storybook.js.org/docs/react/writing-stories/args
Primary.args = {
  primary: true,
  label: "Button",
};

export const Secondary = Template.bind({});
Secondary.args = {
  label: "Button",
};

export const Large = Template.bind({});
Large.args = {
  size: "large",
  label: "Button",
};

export const Small = Template.bind({});
Small.args = {
  size: "small",
  label: "Button",
};
// 회원가입 Button
export const SignUpBtn = Template.bind({});
SignUpBtn.args = {
  primary: true,
  label: "회원가입",
};
export const IdCheckBtn = Template.bind({});
// More on args: https://storybook.js.org/docs/react/writing-stories/args
IdCheckBtn.args = {
  primary: true,
  label: "아이디 확인",
};
// Steam LogIn Button
export const SteamLogIn = Template.bind({});
SteamLogIn.args = {
  img: steamLogInImg,
  custom: "STEAM_LOG_IN_BUTTON",
};
