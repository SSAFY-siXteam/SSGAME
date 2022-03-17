import { UserManager } from "oidc-client";

export const openIDLogIn = () => {
  const settings = {
    authority: "http://localhost:3000/oidc",
    client_id: "A523B13BD02DE21FB0088B9CD46DE699",
    redirect_uri: "https://steamcommunity.com/openid/login",
  };

  const userManager = new UserManager(settings);
  userManager
    .signinPopup()
    .then((data) => {
      console.log("data:", data);
    })
    .catch((e) => {
      console.log("err:", e);
    });
  console.log("userManager:", userManager);
};
