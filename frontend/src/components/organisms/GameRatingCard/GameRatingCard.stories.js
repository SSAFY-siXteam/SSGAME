import React from "react";
import GameRatingCard from "./GameRatingCard";
//test
import silver from "../../../assets/img/medals/silver.png";
export default {
  title: "organisms/GameRatingCard",
  component: GameRatingCard,
};

const Template = (args) => <GameRatingCard {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  content: "",
  img: silver,
};
