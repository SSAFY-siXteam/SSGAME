import React from "react";

import { Label } from "./Label";

export default {
  title: "Atoms/Label",
  component: Label,
  argTypes: {
    backgroundColor: { control: "color" },
  },
};

const Template = (args) => <Label {...args} />;

export const LabelTest = Template.bind({});
LabelTest.args = {
  content: "레이블 테스트",
};
