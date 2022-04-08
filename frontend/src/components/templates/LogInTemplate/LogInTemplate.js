import React, { useEffect, useState } from "react";
import Box from "@mui/material/Box";

const style = {
  //   position: "absolute",
  //   top: "50%",
  //   left: "50%",
  //   transform: "translate(-50%, -50%)",
  //   width: 400,
  //   // bgcolor: "background.paper",
  //   border: "2px solid #000",
  //   boxShadow: 24,
  //   borderColor: "#A12785",
  //   borderRadius: "10px",
  //   borderWidth: "5px",
  //   p: 4,
};

// checkbox list 객체
export default function LogInTemplate({ content }) {
  return (
    <div>
      <Box sx={style}>{content}</Box>
    </div>
  );
}
