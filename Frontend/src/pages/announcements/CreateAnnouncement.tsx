import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { addNotification } from "../../store/notification/notificationSlice";
import {
  Box,
  Button,
  TextField,
  Typography,
  Modal,
  Select,
  MenuItem,
  FormControl,
  InputLabel,
  useTheme,
} from "@mui/material";

const style = {
  position: "absolute" as "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,
  p: 4,
  borderRadius: "10px",
};

const BasicModal = () => {
  const [open, setOpen] = useState(false);
  const [formData, setFormData] = useState({
    title: "",
    description: "",
    assignTo: "",
  });
  const theme = useTheme();
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const handleOpen = () => setOpen(true);
  const handleClose = () => {
    setOpen(false);
    navigate("/announcements");
  };

  useEffect(() => {
    handleOpen();
  }, []);

  const handleSubmit = () => {
    dispatch(addNotification(formData));
    handleClose();
  };

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | { name?: string; value: unknown }>
  ) => {
    setFormData((prevState) => ({
      ...prevState,
      [e.target.name]: e.target.value,
    }));
  };

  return (
    <>
      <Button onClick={handleOpen}>Open modal</Button>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <Typography
            id="modal-modal-title"
            variant="h6"
            component="h2"
            sx={{
              color:
                theme.palette.mode === "dark"
                  ? theme.palette.primary.main
                  : theme.palette.primary.main,
            }}
          >
            Text in a modal
          </Typography>
          <TextField
            margin="normal"
            label="Title"
            name="title"
            value={formData.title}
            onChange={handleChange}
          />
          <TextField
            margin="normal"
            label="Description"
            name="description"
            value={formData.description}
            onChange={handleChange}
          />
          <FormControl fullWidth margin="normal">
            <InputLabel id="assign-to-label">Assign To</InputLabel>
            <Select
              labelId="assign-to-label"
              id="assign-to-select"
              name="assignTo"
              value={formData.assignTo}
              label="Assign To"
              onChange={handleChange}
            >
              <MenuItem value={"Person 1"}>Person 1</MenuItem>
              <MenuItem value={"Person 2"}>Person 2</MenuItem>
            </Select>
          </FormControl>
          <Button
            onClick={handleSubmit}
            fullWidth
            variant="contained"
            color="primary"
            style={{ marginTop: "20px" }}
          >
            Submit
          </Button>
        </Box>
      </Modal>
    </>
  );
};

export default BasicModal;
