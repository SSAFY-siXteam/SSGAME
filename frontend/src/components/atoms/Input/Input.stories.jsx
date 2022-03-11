import React from "react";

import { Input } from "./Input";

export default {
  title: "Input",
  component: Input,
  // More on argTypes: https://storybook.js.org/docs/react/api/argtypes
  argTypes: {
    backgroundColor: { control: "color" },
  },
};

// More on component templates: https://storybook.js.org/docs/react/writing-stories/introduction#using-args
const Template = (args) => <Input {...args} />;

export const InputID = Template.bind({});
// More on args: https://storybook.js.org/docs/react/writing-stories/args
InputID.args = {
  id: "idInput",
  type: "text",
  minLength: 8,
  maxLength: 15,
  size: 30,
  placeholder: "ID를 입력해주세요",
};

export const InputPassword = Template.bind({});
// More on args: https://storybook.js.org/docs/react/writing-stories/args
InputPassword.args = {
  id: "passwordInput",
  type: "password",
  minLength: 8,
  maxLength: 15,
  size: 30,
  placeholder: "Password를 입력해주세요",
};
