import React from "react";
import Button from "./Button";

export default {
  title: "Atoms/Button",
  component: Button,
};

const Template = (args) => <Button {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  text: "테스트 버튼",
  img: null,
};

export const IdCheckBtn = Template.bind({});
IdCheckBtn.args = {
  text: "아이디 확인",
  img: null,
};
