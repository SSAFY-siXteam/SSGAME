import { UserManager } from "oidc-client";

export function openIDLogIn() {
  const onLogin = () => {
    const settings = {
      authority: " https://steamcommunity.com/openid",
      client_id: "A523B13BD02DE21FB0088B9CD46DE699",
      redirect_uri: "http://localhost:3000/",
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
  return onLogin;
}
