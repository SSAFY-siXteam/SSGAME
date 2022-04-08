import React from "react";
import Nav from "./Nav";

export default {
  title: "Atoms/Nav",
  component: Nav,
};

const Template = (args) => <Nav {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  menus: [
    { menu: "메뉴1", path: "/" },
    { menu: "메뉴2", path: "/" },
  ],
};
