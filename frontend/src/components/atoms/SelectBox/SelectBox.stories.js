import React from "react";
import SelectBox from "./SelectBox.js";

export default {
  title: "Atoms/SelectBox",
  component: SelectBox,
};

const Template = (args) => <SelectBox {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  options: [
    { value: "option1", name: "옵션1" },
    { value: "option2", name: "옵션2" },
  ],
};
