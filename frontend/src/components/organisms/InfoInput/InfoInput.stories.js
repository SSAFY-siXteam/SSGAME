import React from "react";

import { InfoInput } from "./InfoInput";

import {
  InputWithLabelEmail,
  InputWithLabelID,
  InputWithLabelPassword,
} from "../../molecules/InputWithLabel/InputWithLabel.stories";
import { IdCheckBtn } from "../../atoms/Buttons/Button.stories";

export default {
  title: "Organisms/InfoInput",
  component: InfoInput,
  argTypes: {
    backgroundColor: { control: "color" },
  },
};

const Template = (args) => <InfoInput {...args} />;

export const InfoInputTest = Template.bind({});
InfoInputTest.args = {
  id: InputWithLabelID.args,
  password: InputWithLabelPassword.args,
  email: InputWithLabelEmail.args,
  idCheckBtn: IdCheckBtn.args,
};
