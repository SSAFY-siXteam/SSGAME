import React from "react";
import InfoText from "./InfoText.js";

export default {
  title: "Atoms/InfoText",
  component: InfoText,
};

const Template = (args) => <InfoText {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  primary: true,
  text: "information",
};

export const Large = Template.bind({});
Large.args = {
  text: "information",
  size: "large",
};
