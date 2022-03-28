import React, { useEffect, useState } from "react";
import Box from "@mui/material/Box";
import Button from "../../atoms/Buttons/Button";
import Typography from "@mui/material/Typography";
import Modal from "@mui/material/Modal";
import {} from "./styles";

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  // bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  borderColor: "#A12785",
  borderRadius: "10px",
  borderWidth: "5px",
  p: 4,
};

// checkbox list 객체
export default function ModalTemplate({ content, isOpened, handleClose }) {
  return (
    <div>
      <Modal
        open={isOpened}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
        sx={{ bgcolor: `rgba(0, 0, 0, 0.1)` }}
      >
        <Box sx={style}>{content}</Box>
      </Modal>
    </div>
  );
}
