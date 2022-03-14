import React from "react";

import { CheckBoxModule } from "./CheckBoxModule";

export default {
  title: "CheckBoxModule",
  component: CheckBoxModule,
  argTypes: {
    backgroundColor: { control: "color" },
  },
};

const Template = (args) => <CheckBoxModule {...args} />;

export const CheckBoxModuleTest = Template.bind({});
CheckBoxModuleTest.args = {
  list: [
    { content: "액션", fontSize: 10, label: "action" },
    { content: "어드벤쳐", fontSize: 10, label: "adventure" },
    { content: "가족", fontSize: 10, label: "family" },
  ],
};
