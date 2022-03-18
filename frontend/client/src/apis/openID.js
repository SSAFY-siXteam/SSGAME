export const openIDLogIn = () => {
  const popupWindow = window.open(
    "http://localhost:4000" + "/auth/steam",
    "_blank",
    "width=800, height=600"
  );
  if (window.focus) popupWindow.focus();
};
