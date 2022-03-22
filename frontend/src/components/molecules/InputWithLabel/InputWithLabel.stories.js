import React from "react";

import { InputWithLabel } from "./InputWithLabel";

export default {
  title: "Molecules/InputWithLabel",
  component: InputWithLabel,
  argTypes: {
    backgroundColor: { control: "color" },
  },
};

const Template = (args) => <InputWithLabel {...args} />;

export const InputWithLabelID = Template.bind({});
InputWithLabelID.args = {
  label: "id",
  type: "text",
  minLength: 8,
  maxLength: 15,
  size: 30,
  placeholder: "ID를 입력해주세요",
  content: "ID",
};

export const InputWithLabelPassword = Template.bind({});
InputWithLabelPassword.args = {
  label: "password",
  type: "password",
  minLength: 8,
  maxLength: 15,
  size: 30,
  placeholder: "Password를 입력해주세요",
  content: "Password",
};

export const InputWithLabelEmail = Template.bind({});
InputWithLabelEmail.args = {
  label: "email",
  type: "text",
  minLength: 8,
  maxLength: 15,
  size: 30,
  placeholder: "이메일을 입력해주세요",
  content: "E-Mail",
};
