import axios from "axios";
// 윈도우 상태값을 보내주자
// 보낸 이후 이 상태값에 따라 (닫혔을 경우) => 서버에 요청을 한다
export const openIDLogIn = () => {
  // const popupWindow = window.open(
  //   "http://localhost:4000" + "/auth/steam",
  //   "_blank",
  //   "width=800, height=600"
  // );
  // if (window.focus) popupWindow.focus();

  // // let userInfo;
  // // const response = await axios("http://localhost:4000" + "/user", {
  // //   user: userInfo,
  // // });

  // return popupWindow;
  window.location.replace("http://localhost:4000" + "/auth/steam");
};
export const openIDLogInCheck = async (id) => {
  // axios.get("http://localhost:4000" + "/user" + "/" + id);

  const requestOptions = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
  };
  const isChecked = await fetch(
    "http://localhost:4000/user/" + id,
    requestOptions
  )
    .then((res) => res.json())
    .then((data) => {
      console.log(data.userLoggedIn);
      return data.userLoggedIn;
    });

  console.log(isChecked);
  return isChecked;
};
