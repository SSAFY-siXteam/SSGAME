import React from "react";
import { Routes, Route } from "react-router";
import Header from "./components/organisms/Header/Header";
import styled from "styled-components";

import Main from "./components/pages/Main/Main";
import Analyze from "./components/pages/Analyze/Analyze";

function App() {
  return (
    <>
      <Header />
      <Grid>
        <Routes>
          <Route path="/" element={<Main />} />
          <Route path="/analyze" element={<Analyze />} />
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
