import React from "react";

import { CheckBox } from "./CheckBox";

export default {
  title: "CheckBox",
  component: CheckBox,
  argTypes: {
    backgroundColor: { control: "color" },
  },
};

// More on component templates: https://storybook.js.org/docs/react/writing-stories/introduction#using-args
const Template = (args) => <CheckBox {...args} />;

// Steam LogIn Button
export const CheckBoxTest = Template.bind({});
CheckBoxTest.args = {
  label: "test",
};
