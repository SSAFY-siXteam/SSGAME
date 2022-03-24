import React, { useEffect, useState } from "react";
import { Routes, Route } from "react-router";
import Header from "./components/organisms/Header/Header";
import styled from "styled-components";

import Main from "./components/pages/Main/Main";
import SignUpPage from "./components/pages/SignUpPage/SignUpPage";
import AnalyzePage from "./components/pages/AnalyzePage/AnalyzePage";
import { setCookie, getCookie, removeCookie } from "./utils/cookie";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const onLogIn = async () => {
    // console.log(userId, userPassword, "clicked");
    // const jwtToken = await signIn({ ssgameId: userId, password: userPassword });
    // if (jwtToken.status === 200) {
    //   setCookie("userToken", jwtToken.data.jwtToken, {
    //     path: "/",
    //   });
    // } else {
    //   alert("error");
    // }
    setCookie("userToken", "testtestttttest", {
      path: "/",
    });
    // 성공시
    setIsLoggedIn(true);
  };
  const onLogOut = () => {
    removeCookie("userToken");
    setIsLoggedIn(false);
  };

  useEffect(() => {
    //cookie가 있을 때
    if (getCookie("userToken")) {
      setIsLoggedIn(true);
    }
  }, []);
  useEffect(() => {}, [isLoggedIn]);
  return (
    <>
      <Header logged={isLoggedIn} onLogOut={onLogOut} onLogIn={onLogIn} />
      <Grid>
        <Routes>
          <Route path="/" element={<Main />} />
          <Route path="/analyze" element={<AnalyzePage />} />
          <Route path="/register" element={<SignUpPage />} />
        </Routes>
      </Grid>
    </>
  );
}

const Grid = styled.div`
  width: 70%;
  display: block;
  margin: 0 auto;
`;

export default App;
