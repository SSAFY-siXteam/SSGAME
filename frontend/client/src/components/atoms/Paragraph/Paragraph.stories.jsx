import React from "react";

import { Paragraph } from "./Paragraph";

export default {
  title: "Atoms/Paragraph",
  component: Paragraph,
  argTypes: {
    backgroundColor: { control: "color" },
  },
};

const Template = (args) => <Paragraph {...args} />;

export const ParagraphTest = Template.bind({});
ParagraphTest.args = {
  content: "p 테스트",
  fontSize: 20,
};
