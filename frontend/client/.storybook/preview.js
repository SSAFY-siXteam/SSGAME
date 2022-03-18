import GlobalStyle from "../src/commons/style/GlobalStyle";

import { addDecorator } from "@storybook/react";
import { MemoryRouter } from "react-router";

addDecorator((story) => (
  <MemoryRouter initialEntries={["/"]}>
    <GlobalStyle />
    {story()}
  </MemoryRouter>
));

export const parameters = {
  actions: { argTypesRegex: "^on[A-Z].*" },
  controls: {
    matchers: {
      color: /(background|color)$/i,
      date: /Date$/,
    },
  },
};
