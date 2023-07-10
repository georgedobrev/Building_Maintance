import { useState, FC, useEffect } from "react";
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
import apiService from "../../services/apiService";

interface CreateAnnouncementProps {
  open: boolean;
  setOpen: (open: boolean) => void;
}

const CreateAnnouncement: FC<CreateAnnouncementProps> = ({ open, setOpen }) => {
  const [managedBuildings, setManagedBuildings] = useState([]);
  const [formData, setFormData] = useState({
    title: "",
    description: "",
    assignTo: "",
  });
  const theme = useTheme();

  useEffect(() => {
    const fetchManagedBuildings = async () => {
      try {
        const response = await apiService.getManagedBuildings();
        localStorage.setItem("buildingId", response.data[0].buildingId);
        console.log(response);
        const buildingNames = response.data.map(
          (b: { buildingName: string }) => b.buildingName
        );
        setManagedBuildings(buildingNames);
      } catch (error) {}
    };

    fetchManagedBuildings();
  }, []);

  const handleClose = () => {
    setOpen(false);
  };

  const handleSubmit = async () => {
    setFormData((prevState) => ({
      ...prevState,
      date: new Date().toLocaleString(),
    }));

    try {
      console.log(formData);
      const response = await apiService.createAnnouncement(formData);
      console.log(response);
    } catch (error) {
      console.log(error);
    }

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
          options={managedBuildings}
          getOptionLabel={(option) => option}
          renderInput={(params) => (
            <TextField {...params} label="Assign To" margin="normal" />
          )}
          value={formData.assignTo}
          onChange={(event, newValue) => {
            setFormData((prevState) => ({
              ...prevState,
              assignTo: newValue || "",
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
