import React from "react";

import { CheckBoxItem } from "./CheckBoxItem";

export default {
  title: "Molecules/CheckBoxItem",
  component: CheckBoxItem,
  argTypes: {
    backgroundColor: { control: "color" },
  },
};

const Template = (args) => <CheckBoxItem {...args} />;

export const CheckBoxItemTest = Template.bind({});
CheckBoxItemTest.args = {
  list: { content: "test", fontSize: 10, label: "test" },
};
