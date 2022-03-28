import React from "react";
import ShortGameCardList from "./ShortGameCardList.js";

export default {
  title: "organisms/ShortGameCardList",
  component: ShortGameCardList,
};

const Template = (args) => <ShortGameCardList {...args} />;

export const Primary = Template.bind({});
Primary.args = {
  recommendedGameList: [
    {
      gameSeq: "1",
      gameName: "게임1",
      headerImage:
        "https://cdn.akamai.steamstatic.com/steam/apps/10/header.jpg?t=1602535893",
      genres: ["태그1", "태그2", "태그3"], //최대 3개
      price: 10000,
    },
    {
      gameSeq: "2",
      gameName: "게임2",
      headerImage:
        "https://cdn.akamai.steamstatic.com/steam/apps/10/header.jpg?t=1602535893",
      genres: ["태그1", "태그2", "태그3"], //최대 3개
      price: 10000,
    },
    {
      gameSeq: "2",
      gameName: "게임3",
      headerImage:
        "https://cdn.akamai.steamstatic.com/steam/apps/10/header.jpg?t=1602535893",
      genres: ["태그1", "태그2", "태그3"], //최대 3개
      price: 10000,
    },
    {
      gameSeq: "2",
      gameName: "게임4",
      headerImage:
        "https://cdn.akamai.steamstatic.com/steam/apps/10/header.jpg?t=1602535893",
      genres: ["태그1", "태그2", "태그3"], //최대 3개
      price: 10000,
    },
    {
      gameSeq: "2",
      gameName: "게임5",
      headerImage:
        "https://cdn.akamai.steamstatic.com/steam/apps/10/header.jpg?t=1602535893",
      genres: ["태그1", "태그2", "태그3"], //최대 3개
      price: 10000,
    },
  ],
};
