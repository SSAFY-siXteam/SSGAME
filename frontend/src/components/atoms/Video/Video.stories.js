import React from "react";
import Video from "./Video.js";

export default {
  title: "Atoms/Video",
  component: Video,
};

const Template = (args) => <Video {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  path: "http://cdn.akamai.steamstatic.com/steam/apps/912/movie480.mp4?t=1512411140",
};
