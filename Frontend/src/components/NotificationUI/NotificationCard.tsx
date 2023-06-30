import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  Box,
  Avatar,
  Card,
  CardActions,
  CardContent,
  Button,
  TextField,
  Typography,
  useTheme,
  Tooltip,
  FormHelperText,
} from "@mui/material";
import {
  MessageOutlinedIcon,
  DeleteOutlineOutlinedIcon,
  SendOutlinedIcon,
  ExpandLessIcon,
  ExpandMoreIcon,
  ModeEditOutlineOutlinedIcon,
  CheckCircleOutlineOutlinedIcon,
} from "./notificationIcons";
import {
  addComment,
  deleteComment,
  editComment,
} from "../../store/comment/commentSlice";
import { Comment } from "../../store/comment/commentTypes";
import { RootState } from "../../store/store";
import DisabledTabs from "./NotificationTabs";
import { deleteNotification } from "../../store/notification/notificationSlice";
import { NotificationCardProps } from "./notificationCardProps";
import { getStyles } from "./styles";

const NotificationCard: React.FC<NotificationCardProps> = ({
  id,
  title,
  description,
  currentUser,
  date,
}) => {
  const [showMessageInput, setShowMessageInput] = useState(false);
  const [comment, setComment] = useState("");
  const [showComments, setShowComments] = useState(false);
  const [editCommentId, setEditCommentId] = useState<null | number>(null);
  const [editCommentText, setEditCommentText] = useState("");
  const [commentError, setCommentError] = useState(false);
  const [editCommentError, setEditCommentError] = useState(false);

  const currentComment = useSelector((state: RootState) =>
    state.comment.filter((comment: Comment) => comment.notificationId === id)
  );

  const theme = useTheme();
  const dispatch = useDispatch();
  const styles = getStyles(theme);

  const handleMessageIconClick = () => {
    setShowMessageInput(!showMessageInput);
  };

  const handleExpandClick = () => {
    setShowComments(!showComments);
  };

  const handleDeleteClick = () => {
    dispatch(deleteNotification(id));
  };

  const handleSendClick = () => {
    if (comment.trim() === "") {
      setCommentError(true);
      return;
    }

    dispatch(
      addComment({
        id: Date.now(),
        notificationId: id,
        text: comment,
        commenter: "currentUser",
      })
    );
    setComment("");
    setCommentError(false);
  };

  const handleDeleteComment = (commentId: number) => {
    dispatch(deleteComment(commentId));
  };

  const handleEditClick = (commentId: number, currentText: string) => {
    setEditCommentId(commentId);
    setEditCommentText(currentText);
    setEditCommentError(false);
  };

  const handleEditChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setEditCommentText(e.target.value);
    setEditCommentError(false);
  };

  const handleEditBlur = () => {
    if (editCommentText.trim() === "") {
      setEditCommentError(true);
    } else {
      setEditCommentId(null);
    }
  };

  const handleEditConfirm = () => {
    if (editCommentId !== null) {
      if (editCommentText.trim() === "") {
        setEditCommentError(true);
        return;
      }

      dispatch(editComment({ id: editCommentId, text: editCommentText }));

      setTimeout(() => {
        setEditCommentId(null);
        setEditCommentText("");
      }, 500);
    }
  };

  const handleCommentChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setComment(e.target.value);
    setCommentError(false);
  };

  return (
    <Box sx={styles.outerBox}>
      {currentUser && <DisabledTabs />}
      <Card sx={styles.card}>
        <CardContent>
          <Typography
            variant="subtitle2"
            component="div"
            sx={styles.subtitleTypography}
          >
            {date}
          </Typography>

          <Typography variant="h5" component="div" sx={styles.h5Typography}>
            {title}
          </Typography>

          <Typography variant="body2" sx={styles.body2Typography}>
            {description}
          </Typography>

          {showMessageInput && (
            <Box sx={styles.innerBox}>
              <TextField
                error={commentError}
                helperText={commentError ? "Can't post an empty comment" : ""}
                multiline
                rows={2}
                variant="outlined"
                placeholder="Post a comment..."
                fullWidth
                value={comment}
                onChange={handleCommentChange}
                sx={styles.textField}
              />
              <Button sx={styles.sendButton} onClick={handleSendClick}>
                <SendOutlinedIcon />
              </Button>
            </Box>
          )}
        </CardContent>
        <CardActions>
          {currentComment.length > 0 && (
            <Box sx={styles.commentBox}>
              <Button onClick={handleExpandClick} sx={styles.commentButton}>
                {showComments ? <ExpandLessIcon /> : <ExpandMoreIcon />}
              </Button>
              <Typography variant="body2" sx={styles.commentTypography}>
                {currentComment.length}
                {currentComment.length === 1 ? " Comment" : " Comments"}
              </Typography>
            </Box>
          )}
          <Button
            sx={styles.messageButton}
            size="small"
            onClick={handleMessageIconClick}
            title="Write a comment"
          >
            <MessageOutlinedIcon />
          </Button>
          {!currentUser && (
            <Button
              sx={styles.deleteButton}
              size="small"
              onClick={handleDeleteClick}
              title="Delete notification"
            >
              <DeleteOutlineOutlinedIcon />
            </Button>
          )}
        </CardActions>

        <Box sx={styles.commentSectionBox}>
          {showComments &&
            currentComment.map((comment: Comment) => (
              <Box key={comment.id} sx={styles.commentInnerBox}>
                <Box sx={styles.commentContentBox}>
                  <Avatar
                    alt="Remy Sharp"
                    src="https://images.pexels.com/photos/1851164/pexels-photo-1851164.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
                    sx={styles.avatar}
                  />
                  <Box>
                    {editCommentId === comment.id ? (
                      <TextField
                        error={editCommentError}
                        helperText={
                          editCommentError
                            ? "Can't edit to an empty comment"
                            : ""
                        }
                        multiline
                        rows={2}
                        variant="outlined"
                        placeholder="Edit a comment..."
                        fullWidth
                        defaultValue={editCommentText}
                        onChange={handleEditChange}
                        onBlur={handleEditBlur}
                        sx={styles.editTextField}
                      />
                    ) : (
                      <Typography
                        variant="body2"
                        sx={styles.commentBodyTypography}
                      >
                        {comment.text}
                      </Typography>
                    )}
                  </Box>
                </Box>
                <Button
                  sx={styles.deleteCommentButton}
                  size="small"
                  onClick={() => handleDeleteComment(comment.id)}
                  title="Delete comment"
                >
                  <DeleteOutlineOutlinedIcon />
                </Button>
                <Tooltip title="Edit comment">
                  <Button
                    sx={styles.editButton}
                    size="small"
                    onClick={() => handleEditClick(comment.id, comment.text)}
                  >
                    <ModeEditOutlineOutlinedIcon />
                  </Button>
                </Tooltip>
                {editCommentId === comment.id && (
                  <Tooltip title="Confirm edit">
                    <Button
                      sx={styles.confirmEditButton}
                      size="small"
                      onClick={handleEditConfirm}
                    >
                      <CheckCircleOutlineOutlinedIcon />
                    </Button>
                  </Tooltip>
                )}
              </Box>
            ))}
        </Box>
      </Card>
    </Box>
  );
};

export default NotificationCard;
