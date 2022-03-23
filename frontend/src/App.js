import React from "react";
import { Routes, Route } from "react-router";
import Header from "./components/organisms/Header/Header";
import Main from "./components/pages/Main/Main";
import SignUpPage from "./components/pages/SignUpPage/SignUpPage";
import styled from "styled-components";
import ModalTemplate from "./components/templates/ModalTemplate/ModalTemplate";
function App() {
  return (
    <>
      <Header />
      <Grid>
        <Routes>
          <Route path="/" element={<Main />} />
          <Route path="/register" element={<SignUpPage />} />
          <Route path="/modaltest" element={<ModalTemplate />} />
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
