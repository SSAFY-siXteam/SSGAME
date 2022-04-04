import React from "react";
import Pagination from "./Pagination.js";

export default {
  title: "organisms/Pagination",
  component: Pagination,
};

const Template = (args) => <Pagination {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  total: 20,
  limit: 10,
  page: 1,
};
