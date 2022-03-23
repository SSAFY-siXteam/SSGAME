import React from "react";

import { InfoInput } from "./InfoInput";

import {
  InputWithLabelEmail,
  InputWithLabelID,
  InputWithLabelPassword,
} from "../../molecules/InputWithLabel/InputWithLabel.stories";
import { IdCheckBtn } from "../../atoms/Buttons/Button.stories";
<<<<<<< HEAD

=======
>>>>>>> 5fba1bcc6db91a1e33e441855b1577eba0d9eed9
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
