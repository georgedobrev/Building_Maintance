import { useState, FC } from "react";
import { useDispatch } from "react-redux";
import {
  Box,
  Button,
  TextField,
  Typography,
  Modal,
  useTheme,
  Autocomplete,
} from "@mui/material";
import { addNotification } from "../../store/notification/notificationSlice";
import "./CreateAnnouncement.scss";
import { style } from "./ModalStyle";
import users from "../users/Users.json";

interface CreateAnnouncementProps {
  open: boolean;
  setOpen: (open: boolean) => void;
}

const CreateAnnouncement: FC<CreateAnnouncementProps> = ({ open, setOpen }) => {
  const [formData, setFormData] = useState({
    id: Date.now(),
    title: "",
    description: "",
    assignTo: "",
    date: new Date().toLocaleString(),
  });
  const theme = useTheme();
  const dispatch = useDispatch();

  const handleClose = () => {
    setOpen(false);
  };

  const handleSubmit = () => {
    setFormData((prevState) => ({
      ...prevState,
      date: new Date().toLocaleString(),
    }));

    dispatch(addNotification(formData));
    setFormData({
      id: Date.now(),
      title: "",
      description: "",
      assignTo: "",
      date: new Date().toLocaleString(),
    });
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
    <Modal
      open={open}
      onClose={handleClose}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <Box sx={{ ...style, display: "flex", flexDirection: "column" }}>
        <Typography
          id="modal-modal-title"
          variant="h6"
          component="h2"
          sx={{
            color: theme.palette.primary.main,
          }}
        >
          Create Notification
        </Typography>
        <TextField
          required
          margin="normal"
          label="Title"
          name="title"
          value={formData.title}
          onChange={handleChange}
        />
        <TextField
          required
          margin="normal"
          label="Description"
          name="description"
          value={formData.description}
          onChange={handleChange}
        />
        <Autocomplete
          id="combo-box-demo"
          options={users}
          getOptionLabel={(option) =>
            `${option.firstName} ${option.lastName} building ${option.buildingID} unit ${option.unitID}`
          }
          renderInput={(params) => (
            <TextField {...params} label="Assign To" margin="normal" />
          )}
          value={users.find((user) => user.id === Number(formData.assignTo))}
          onChange={(event, newValue) => {
            setFormData((prevState) => ({
              ...prevState,
              assignTo: newValue ? newValue.id.toString() : "",
            }));
          }}
        />
        <Button
          onClick={handleSubmit}
          fullWidth
          variant="contained"
          color="primary"
          className="announcement-submit"
          disabled={
            !formData.title || !formData.description || !formData.assignTo
          }
        >
          Submit
        </Button>
      </Box>
    </Modal>
  );
};

export default CreateAnnouncement;
