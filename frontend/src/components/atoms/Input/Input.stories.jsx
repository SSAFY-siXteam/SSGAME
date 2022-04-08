import React from "react";

import { Input } from "./Input";

export default {
  title: "Atoms/Input",
  component: Input,
  // More on argTypes: https://storybook.js.org/docs/react/api/argtypes
  argTypes: {
    backgroundColor: { control: "color" },
  },
};

const Template = (args) => <Input {...args} />;

export const InputID = Template.bind({});
InputID.args = {
  id: "idInput",
  type: "text",
  minLength: 8,
  maxLength: 15,
  size: 30,
  placeholder: "ID를 입력해주세요",
};

export const InputPassword = Template.bind({});
InputPassword.args = {
  id: "passwordInput",
  type: "password",
  minLength: 8,
  maxLength: 15,
  size: 30,
  placeholder: "Password를 입력해주세요",
};

export const InputEmail = Template.bind({});
InputEmail.args = {
  id: "EmailInput",
  type: "email",
  minLength: 8,
  maxLength: 15,
  size: 30,
  placeholder: "E-mail을 입력해주세요",
};

export const InputUnmodifiable = Template.bind({});
InputUnmodifiable.args = {
  id: "unmodifiable",
  type: "text",
  minLength: 8,
  maxLength: 15,
  size: 30,
  value: "StableText",
};
